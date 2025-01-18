FROM openjdk:17
EXPOSE 8761
WORKDIR /registry/application
COPY /target/registry-1.0.0-SNAPSHOT.jar /registry/application
ENTRYPOINT ["java","-jar","registry-1.0.0-SNAPSHOT.jar"]