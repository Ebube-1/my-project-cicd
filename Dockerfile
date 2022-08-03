#Start with a base image containing Java runtime
FROM openjdk:17 as build

#Information around who maintains the image
MAINTAINER ebube-1

# Add the application's jar to the container
COPY target/dbLocking-0.0.1-SNAPSHOT.jar dbLocking-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT ["java","-jar","/dbLocking-0.0.1-SNAPSHOT.jar"]