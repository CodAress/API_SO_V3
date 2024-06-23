FROM openjdk:22-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8089
LABEL authors="Aldo"
ENTRYPOINT ["java", "-jar" , "app.jar"]