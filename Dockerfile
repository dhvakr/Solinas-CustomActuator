FROM openjdk:17

ARG JAR_FILE=lib/*.jar

COPY ${JAR_FILE} solinas-custom-actuator.jar

EXPOSE 4500

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/solinas-custom-actuator.jar"]