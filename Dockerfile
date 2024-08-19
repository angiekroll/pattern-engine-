FROM openjdk:17
COPY build/libs/*.jar pattern-engine.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "pattern-engine.jar"]