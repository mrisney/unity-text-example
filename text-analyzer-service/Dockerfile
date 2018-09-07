FROM openjdk:8-jdk-alpine
LABEL maintainer="marc.risney@gmail.com"
VOLUME /tmp
EXPOSE 8888
ARG JAR_FILE=target/text-analyzer-1.0.jar
ADD ${JAR_FILE} text-analyzer.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/text-analyzer.jar"]
