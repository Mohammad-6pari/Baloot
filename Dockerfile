FROM eclipse-temurin:17-jdk-alpine
VOLUME /app
COPY /target/Baloot.jar /Baloot.jar
ENTRYPOINT ["java","-jar","/Baloot.jar"]