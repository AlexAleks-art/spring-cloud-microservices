FROM openjdk:17
EXPOSE 8001
WORKDIR /config-service/application
COPY /target/config-service-1.0.0-SNAPSHOT.jar /config-service/application
ENTRYPOINT ["java","-jar","config-service-1.0.0-SNAPSHOT.jar"]