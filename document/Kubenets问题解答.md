##Kubenets问题解答

### node节点pod无法启动/节点删除网络重置

node1之前反复添加过,添加之前需要清除下网络

```
root@master1:/var/lib/kubelet# kubectl get po -o wide
NAME                   READY     STATUS              RESTARTS   AGE       IP           NODE
nginx-8586cf59-6zw9k   1/1       Running             0          9m        10.244.3.3   node2
nginx-8586cf59-jk5pc   0/1       ContainerCreating   0          9m        <none>       node1
nginx-8586cf59-vm9h4   0/1       ContainerCreating   0          9m        <none>       node1
nginx-8586cf59-zjb84   1/1       Running             0          9m        10.244.3.2   node2
root@node1:~# journalctl -u kubelet
 failed: rpc error: code = Unknown desc = NetworkPlugin cni failed to set up pod "nginx-8586cf59-rm4sh_default" network: failed to set bridge addr: "cni0" already has an IP address different from 10.244.2.1/24
12252 cni.go:227] Error while adding to cni network: failed to set bridge addr: "cni0" already
```

重置kubernetes服务，重置网络。删除网络配置，link

```
kubeadm reset
systemctl stop kubelet
systemctl stop docker
rm -rf /var/lib/cni/
rm -rf /var/lib/kubelet/*
rm -rf /etc/cni/
ifconfig cni0 down
ifconfig flannel.1 down
ifconfig docker0 down
ip link delete cni0
ip link delete flannel.1
systemctl start docker
```