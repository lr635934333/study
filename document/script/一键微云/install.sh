#! /bin/bash
remoteIp=192.168.111.79
remoteDir=bright-kitchen

if [ ! -f "version" ];
then
   echo error: version file not exist
   echo ----example----
   echo base=ISSSV100R001B02D001T
   echo server=ISSSV100R001B02D001T201905231550
   echo web=ISSSV100R001B02D001T201905231550
   exit 0
fi

baseVersion=`cat version | awk -F [=] '/^base/ {print $2}'`
# 使用时间戳作为递增版本号
currentVersion=`date -d today +"%Y%m%d%H%M"`
newVersion=${baseVersion}${currentVersion}
echo newVersion: $newVersion

# 个性化打包
if [[ $1 == "server" ]] || [[ $2 == "server" ]]
then    
    oldVersion=`cat version | awk -F [=] '/^server/ {print $2}'`
    sed -i s/server=$oldVersion/server=$newVersion/g version
    echo lastVersion: $oldVersion
    ./package.sh 'bright-kitchen' $oldVersion $newVersion java
fi

# 个性化打包
if [[ $1 == "web" ]] || [[ $2 == "web" ]]
then 
    oldVersion=`cat version | awk -F [=] '/^web/ {print $2}'`
    sed -i s/web=$oldVersion/web=$newVersion/g version
    echo lastVersion: $oldVersion

    echo web install
    ./package.sh 'bright-kitchen-web' $oldVersion $newVersion 
fi


#清理多余镜像
echo clear docker images
docker images | awk '/<none>/ {print $3} ' | xargs docker rmi

#远程服务器部署
echo packaging
rm -rf installPackage.tar
tar -cvf installPackage.tar installPackage

echo push install package to ${remoteIp}
scp installPackage.tar root@${remoteIp}:/var/lib/restful/common/product

echo installing
ssh root@${remoteIp} "cd /var/lib/restful/common/product; rm -rf ${remoteDir}; tar -xvf installPackage.tar;rm -rf installPackage.tar;mv installPackage ${remoteDir} ; cd ${remoteDir}; ./restart.sh"
#远程服务器部署退出

