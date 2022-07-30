FROM openjdk:8-jdk-alpine
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/eurekaserver
ADD @project.build.finalName@.jar /usr/local/eurekaserver/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
