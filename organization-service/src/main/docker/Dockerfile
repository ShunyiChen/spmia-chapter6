FROM openjdk:8-jdk-alpine
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories
RUN  apk update && apk upgrade && apk add netcat-openbsd
RUN mkdir -p /usr/local/organizationservice

ADD organization-service-0.0.1-SNAPSHOT.jar /usr/local/organizationservice/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
