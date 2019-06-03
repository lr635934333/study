FROM 192.168.110.202/unisinsight/base-openjdk:1.0
WORKDIR /opt
ADD bright-kitchen-*.jar .
ADD startup.sh startup.sh
ADD classes config
ENTRYPOINT ["/bin/sh","-c","sh startup.sh && tail -f nohup.log"]
