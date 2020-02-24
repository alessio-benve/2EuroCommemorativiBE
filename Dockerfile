FROM openjdk:8-jdk-alpine
ARG JAR_FILE=/target/2EuroCommemorativiFinal-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} 2eurocommemorativi
ENTRYPOINT ["java","-jar","/2eurocommemorativi"]
