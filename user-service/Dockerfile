FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/user-service-*.jar user-service.jar
ENTRYPOINT ["java","-jar","user-service.jar"]