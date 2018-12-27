FROM openjdk:8-jdk-alpine

VOLUME /tmp

EXPOSE 8181

ARG JAR_FILE=target/WorkoutApplication-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} WorkoutApplication-0.0.1-SNAPSHOT.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","WorkoutApplication-0.0.1-SNAPSHOT.jar"]
