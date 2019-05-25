# Linux常用命令

### docker 常用命令
* Linux安装docker
``` shell
# 查看可安装的docker版本
yum list docker-ce --showduplicates | sort -r
# 安装docker
yum install docker-ce-<VERSION STRING>
# 启动docker
systemctl start docker
```

* docker批量删除容器
``` shell
  docker ps | grep redis |awk '{print $1}' |xargs docker rm -f 
  docker rm -f `docker ps | grep redis | awk '{print $1}'`
```
*   docker批量删除镜像
``` shell
	docker images | grep kube | awk '{print ($1":"$2)}' | xargs docker rmi
```
*  docker设置远程镜像厂库
``` shell
# 配置文件路径：/etc/docker/daemon.json
{
  "registry-mirrors": [
    "https://registry.docker-cn.com",
    "https://ambuwpg2.mirror.aliyuncs.com"
  ]
}
```

### Linux系统常用操作
* Linux关闭selinux
``` shell
# 查看selinux状态
/sbin/sestatus
# 永久关闭selinux
vim /etc/sysconfig/selinux
修改 SELINUX=disabled
```
* Linux关闭防火墙
``` shell
# 关闭防火墙
systemctl stop firewalld
# 禁止开机重启
systemctl disable firewalld
```
* Linux systemctl常用命令
``` shell
# 启动服务
systemctl start service
# 重启服务
systemctl restart service
# 停止服务 
systemctl stop service
# 禁止开启重启
systemctl disable service
# 允许开机重启
systemctl enable service
# 查看服务状态
systemctl status service
```
* 查看Centos版本
``` shell 
cat /etc/redhat-release
```
* ssh免密登录(指定用户免密登录)
``` shell
# 本机生成公钥(默认生成路~/.ssh/id_rsa.pub)
ssh-keygen -t ras 
# 追加id_rsa.pub到远程主机~/.ssh/authorized_keys
scp ~/.ssh/id_rsa.pub root@hostname:~
cat id_rsa.pub >> ~/.ssh/authorized_keys
# 注意authorized_keys权限设置为644
```
* 设置网卡IP并重启网卡
``` shell
# 配置路径：/etc/sysconfig/network-scripts
TYPE="Ethernet"
PROXY_METHOD="none"
BROWSER_ONLY="no"
BOOTPROTO="none"
DEFROUTE="yes"
IPV4_FAILURE_FATAL="no"
ONBOOT="yes" //是否卡机启动
IPADDR="192.168.0.212" //IP地址
PREFIX="24" //子网掩码，255.255.255.0
GATEWAY="192.168.0.1" //网关
DNS1="114.114.114.114" //DNS

# 网卡重启
systemctl restart network 
```

### kubernetes 常用操作
* 修改kubernetes外部端口映射范围
``` shell
# 配置文件路径：etc/kubernetes/manifests/kube-apiserver.yaml
# 配置项：service-node-port-range
spec:
  containers:
  - command:
    - kube-apiserver
    - --service-node-port-range=80-32767
# 配置完后 apiserver会自动进行重启
```

### yum 常用操作
* 设置yum源
``` shell
# Base repos
cd /etc/yum.repos.d
mv CentOS-Base.repo CentOS-Base.repo.bak
curl -o CentOS-Base.repo http://mirrors.aliyun.com/repo/Centos-7.repo
sed -i 's/gpgcheck=1/gpgcheck=0/g' /etc/yum.repos.d/CentOS-Base.repo

# docker repo
curl -o docker-ce.repo http://mirrors.aliyun.com/docker-ce/linux/centos/docker-ce.repo

# k8s repo
vim /etc/yum.repos.d/kubernetes.repo
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
yum repolist
```
* 获取可安装列表
``` shell 
yum list java* --showduplicates | sort -r
```
