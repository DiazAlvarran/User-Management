FROM openjdk:17-jdk-alpine
VOLUME /tmp
EXPOSE 8081
ADD target/api-users-0.0.1-SNAPSHOT.jar api-users.jar
ENTRYPOINT ["java","-jar","/api-users.jar"]