## BUILD image
FROM maven:3-jdk-11 as builder

# create app folder for sources
RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build

#Download all required dependencies into one layer
RUN mvn -B dependency:resolve dependency:resolve-plugins

#Copy source code
COPY src /build/src

# Build application
RUN mvn package

# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

#Copy executable jar file from the builder image
COPY --from=builder /build/target/*.jar foodtracker.jar
 
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar foodtracker.jar" ]