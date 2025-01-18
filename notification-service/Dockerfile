FROM openjdk:17
WORKDIR /notification-service/application
COPY /target/notification-service.jar /notification-service/application
ENTRYPOINT ["java","-jar","notification-service.jar"]