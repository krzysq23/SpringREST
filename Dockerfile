FROM java:8-jdk-alpine
EXPOSE 8082
ADD /target/SpringREST.jar SpringREST.jar
ENTRYPOINT ["java","-jar","SpringREST.jar"]