FROM openjdk:11-jre
ARG JAR_FILE=target/storingms-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
