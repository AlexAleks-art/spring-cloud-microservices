FROM openjdk:17
EXPOSE 8083
WORKDIR /deposit-service/application
COPY /target/deposit-service.jar /deposit-service/application
ENTRYPOINT ["java","-jar","deposit-service.jar"]