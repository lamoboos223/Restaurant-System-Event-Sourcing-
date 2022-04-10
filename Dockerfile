# Build JAR file
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
WORKDIR /home/app
# generate avro schema
ENTRYPOINT mvn clean install
# copy avro schema
COPY src /home/app/src
# generate jar
ENTRYPOINT mvn clean package

# Run Jar
#FROM openjdk:11-jre-slim
#COPY --from=build /home/app/target/*.jar /usr/local/lib/app.jar
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]