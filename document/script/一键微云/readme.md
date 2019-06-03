#### 目录结构
``` shell
# 目录结构
dir
installPackage
  	|-configFile
		|-{projectDir}		//配置文件目录
	|-configPod
		|-container.yaml 	//镜像版本
		|-pods.yaml
		|-serviceConfig.yaml
	|-images	//镜像
	|-yaml		//可忽略
	|-restart.sh	//微云项目重启脚本
{projectDir}
	|-build.sh		//自定义打包docker镜像
	|-Dockerfile	//自定义
	|-startup.sh	//自定义
	|-others
install.sh
package.sh
version

# 脚本调用关系
install.sh --> package.sh --> {projectDir}/build.sh
           --> ssh:installPackage/restart.sh
```

#### version文件中维护项目最新的版本号

``` shell
# 基础版本号
base=ISSSV100R001B02D001T
# 全版本号（base + 递增版本）
server=ISSSV100R001B02D001T201906031032
web=ISSSV100R001B02D001T201905281715
```

