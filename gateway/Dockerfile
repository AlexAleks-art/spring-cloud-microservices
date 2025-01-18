FROM openjdk:17
EXPOSE 8989
WORKDIR /gateway/application
COPY /target/gateway.jar /gateway/application
ENTRYPOINT ["java","-jar","gateway.jar"]