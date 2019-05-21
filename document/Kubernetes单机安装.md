# Kubernetes单机安装

### kubeadm工具介绍
kubeadm这个工具可以通过简单的kubeadm init和kubeadm join命令来创建一个kubernetes集群，kubeadm提供的其他命令都比较通俗易懂：
* **kubeadm init 启动一个master节点**；
* kubeadm join 启动一个node节点，加入master；
* kubeadm upgrade 更新集群版本；
* kubeadm config 从1.8.0版本开始已经用处不大，可以用来view一下配置；
* kubeadm token 管理kubeadm join的token；
* **kubeadm reset 把kubeadm init或kubeadm join做的更改恢复原状**；
* kubeadm version打印版本信息；
* kubeadm alpha预览一些alpha特性的命令。

### 系统信息
```
Centos 7.5  3.10.0-862.9.1.el7.x86_64
```
### 关闭slinux和firewalld
```shell
# Set SELinux in permissive mode
setenforce 0
sed -i 's/^SELINUX=enforcing$/SELINUX=permissive/' /etc/selinux/config
# Stop and disable firewalld
systemctl disable firewalld --now
```

### 系统参数与内核模块
```shell
# 新建k8s.conf配置
touch /etc/sysctl.d/k8s.conf
# 写入配置项目到k8s.conf
net.bridge.bridge-nf-call-ip6tables = 1
net.bridge.bridge-nf-call-iptables = 1

# 手动加系统配置
sysctl --system

# 加载内核模块
modprobe br_netfilter
lsmod | grep br_netfilter
```

### 配置yum源
```shell
cd /etc/yum.repos.d
# 设置为Base.repo为aliyun yum源
mv CentOS-Base.repo CentOS-Base.repo.bak
curl -o CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
# 设置yum是否检查GPG(GNU Private Guard)，GPG是一种密钥方式签名。
sed -i 's/gpgcheck=1/gpgcheck=0/g' /etc/yum.repos.d/CentOS-Base.repo

# 下载docker yum源（下载方式）
curl -o docker-ce.repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

# 配置kubernetes yum源（手动方式）
touch /etc/yum.repos.d/kubernetes.repo
# 添加配置到 kubernetes.repo
[kubernetes]
name=Kubernetes
baseurl=http://mirrors.aliyun.com/kubernetes/yum/repos/kubernetes-el7-x86_64
enabled=1
gpgcheck=0
repo_gpgcheck=0
gpgkey=http://mirrors.aliyun.com/kubernetes/yum/doc/yum-key.gpg
       http://mirrors.aliyun.com/kubernetes/yum/doc/rpm-package-key.gpg

# update cache
yum clean all  
yum makecache

# 查看yum源列表
yum repolist
```

### 禁用swap
```shell
swapoff -a
echo "vm.swappiness = 0">> /etc/sysctl.conf
sysctl -p
```
### 安装docker
```shell
# 先看可用版本：(showduplicates查看可用安装包)
yum list docker-ce --showduplicates | sort -r
# 选择安装一个版本
yum install docker-ce-<VERSION STRING>
# 启动docker
systemctl enable docker --now
```
### 安装kubeadm、kubelet和kubectl
kubeadm不管kubelet和kubectl，所以我们需要手动安装kubelet和kubectl：
``` shell
yum install -y kubelet kubeadm kubectl --disableexcludes=kubernetes
```

最后启动kubelet：
``` shell
systemctl enable --now kubelet
```
### 镜像准备
为了解决国内普遍访问不到k8s.gcr.io的问题，我们从mirrorgooglecontainers下载image，然后打个tag来绕过网络限制：
``` shell
docker pull docker.io/mirrorgooglecontainers/kube-apiserver-amd64:v1.14.1
docker tag docker.io/mirrorgooglecontainers/kube-apiserver-amd64:v1.14.1 k8s.gcr.io/kube-apiserver:v1.14.1
docker pull docker.io/mirrorgooglecontainers/kube-controller-manager-amd64:v1.14.1
docker tag docker.io/mirrorgooglecontainers/kube-controller-manager-amd64:v1.14.1 k8s.gcr.io/kube-controller-manager:v1.14.1
docker pull docker.io/mirrorgooglecontainers/kube-scheduler-amd64:v1.14.1
docker tag docker.io/mirrorgooglecontainers/kube-scheduler-amd64:v1.14.1 k8s.gcr.io/kube-scheduler:v1.14.1
docker pull docker.io/mirrorgooglecontainers/kube-proxy-amd64:v1.14.1
docker tag docker.io/mirrorgooglecontainers/kube-proxy-amd64:v1.14.1 k8s.gcr.io/kube-proxy:v1.14.1
docker pull docker.io/mirrorgooglecontainers/pause-amd64:3.1
docker tag docker.io/mirrorgooglecontainers/pause-amd64:3.1 k8s.gcr.io/pause:3.1
docker pull docker.io/mirrorgooglecontainers/etcd-amd64:3.3.10
docker tag docker.io/mirrorgooglecontainers/etcd-amd64:3.3.10 k8s.gcr.io/etcd:3.3.10
docker pull docker.io/coredns/coredns:1.3.1
docker tag docker.io/coredns/coredns:1.3.1 k8s.gcr.io/coredns:1.3.1
```

**根据实际kubeadm版本情况下载对应版本镜像**

### 安装k8s master
```shell
kubeadm init --pod-network-cidr=10.100.0.0/16
```
跑完上面的init命令后，会看到类似如下的输出：
``` shell
Your Kubernetes master has initialized successfully!

To start using your cluster, you need to run the following as a regular user:

  mkdir -p $HOME/.kube
  sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
  sudo chown $(id -u):$(id -g) $HOME/.kube/config

You should now deploy a pod network to the cluster.
Run "kubectl apply -f [podnetwork].yaml" with one of the options listed at:
  https://kubernetes.io/docs/concepts/cluster-administration/addons/

You can now join any number of machines by running the following on each node
as root:

  kubeadm join 192.168.19.100:6443 --token i472cq.tr9a81qxnyqc5zj2 --discovery-token-ca-cert-hash sha256:acba957db29e0efbffe2cf4e484521b3b7e0f9d5c2ab7f9db68a5e31565d0d66
```
上面输出告诉我们还需要做一些工作：
```shell
mkdir -p $HOME/.kube
sudo cp -i /etc/kubernetes/admin.conf $HOME/.kube/config
sudo chown $(id -u):$(id -g) $HOME/.kube/config

#创建网络
kubectl apply -f https://raw.githubusercontent.com/coreos/flannel/bc79dd1505b0c8681ece4de4c0d86c5cd2643275/Documentation/kube-flannel.yml
```
稍等一会，应该可以看到node状态变成ready：
``` shell
kubectl get node
#NAME          STATUS   ROLES    AGE     VERSION
#kube-master   Ready    master   8m30s   v1.14.1
```
### 配置运行master节点运行任务

最后注意到kube-master这个node上有一个Taint：

``` shell
kubectl describe node kube-master | grep Taints
#Taints: node-role.kubernetes.io/master:NoSchedule
```
**默认master节点是不跑业务pod的，我们暂时只有一个node，所以先去掉这个Taint：**

``` shell 
kubectl taint node kube-master node-role.kubernetes.io/master-
#node/kube-master untainted

kubectl describe node kube-master | grep Taints
#Taints: <none>
```

### 环境验证
写一个yaml：
``` yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: mytomcat
spec:
  replicas: 1
  selector:
    matchLabels:
      app: mytomcat
  template:
    metadata:
      name: mytomcat
      labels:
        app: mytomcat
    spec:
      containers:
      - name: mytomcat
        image: tomcat:8
        ports:
        - containerPort: 8080
```
如上内容保存为tomcat-deploy.yaml，执行
```shell
kubectl create -f tomcat-deploy.yaml
```
查看pod状态
```shell
kubectl  get pod
```

### 重装master节点
执行kubeadm reset 把kubeadm init或kubeadm join做的更改恢复原状

```
kubeadm reset 
```

执行安装master之后的所有操作

```
kubeadm init --pod-network-cidr=10.100.0.0/16
...
```

### 常用命令集合

### 参考文档
https://www.kubernetes.org.cn/5077.html