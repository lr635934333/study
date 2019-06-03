#!/bin/bash
project=$1

imagePath=installPackage/images
configPath=installPackage/configFile/${project}
configPodPath=installPackage/configPod
version=$3
oldVersion=$2

rm -f ${imagePath}/${project}-${oldVersion}.tar
cd ${project}
echo current path: `pwd`
./build.sh

cd ..
if [ $4 == "java" ]
then
    /bin/cp -f ${project}/target/classes/*properties ${project}/target/classes/*conf ${project}/target/classes/*yaml ${project}/target/classes/*yml ${project}/target/classes/*zip ${configPath}
fi
docker tag  ${project}:latest ${project}:${version}
docker save -o ${imagePath}/${project}-${version}.tar ${project}:${version}

sed -i s/${project}:${oldVersion}/${project}:${version}/g ${configPodPath}/container.yaml

exit
