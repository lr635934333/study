# Git使用方法

## Git基础
### Git工作流程

![](./git工作流程.png)

* Workspace：工作区（idea）

* Index / Stage：暂存区（.git/index文件）
``` shell
# 暂存区会记录git add添加的文件
$ git add . 
```
* Repository：仓库区（或本地仓库）
``` shell
# git commit命令提交的内容会存入本地仓库，方便下一步通过git push同步到远程仓库
$ git commit -m 'message'
```
* Remote：远程仓库
``` shell
# git push 同步本地仓库代码到远程仓库
$ git push origin develop
```

### Git常用命令列表

![](.\git命令.png)




## Git


## Git常见问题
### git fetch 和 git pull 的区别

* git fetch 相当于是从远程获取最新到本地，不会自动merge，如下指令：
``` shell
#将远程仓库的master分支下载到本地当前branch中
$ git fetch orgin master 
#比较本地的master分支和origin/master分支的差别
$ git log -p master  ..origin/master 
# 进行合并
$ git merge origin/master 
```
* git pull：相当于是从远程获取最新版本并merge到本地
``` shell
# fetch 并 merge
$ git pull origin master
```
### git http免登陆
设置记住密码（默认15分钟）：
``` shell
$ git config --global credential.helper cache
```
长期存储密码：
``` shell
git config --global credential.helper store
```
增加远程地址的时候带上密码也是可以的。
``` shell
git remote add origin http://yourname:password@hostname/bright-kitchen.git
```