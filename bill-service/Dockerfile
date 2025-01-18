FROM openjdk:17
EXPOSE 8082
WORKDIR /bill-service/application
COPY /target/bill-service.jar /bill-service/application
ENTRYPOINT ["java","-jar","bill-service.jar"]