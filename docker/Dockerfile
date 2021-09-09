# -----------------------------------------------------------------------------
# BUILD STAGE
# -----------------------------------------------------------------------------
FROM maven:3.8.2-jdk-11-slim as build

# Create app directory
WORKDIR /usr/src/app

# Copy and build
COPY . .

RUN mvn clean package -DskipTests

# -----------------------------------------------------------------------------
# DEPLOYMENT STAGE
# -----------------------------------------------------------------------------
FROM openjdk:14-alpine as deployment

WORKDIR /app

# Copy from build stage
COPY --from=build ./usr/src/app/target/resource-server.war ./resource-server.war

EXPOSE 8080

#Compile and set profile
CMD java -jar resource-server.war