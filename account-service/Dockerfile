FROM openjdk:17
EXPOSE 8081
WORKDIR /account-service/application
COPY /target/account-service-1.0.0-SNAPSHOT.jar /account-service/application
ENTRYPOINT ["java","-jar","account-service-1.0.0-SNAPSHOT.jar"]