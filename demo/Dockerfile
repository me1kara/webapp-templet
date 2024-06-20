FROM openjdk:17
ARG JAR_FILE=build/libs/demo-server.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]