# -----------------------------------------------------------------------------
# BUILD STAGE
# -----------------------------------------------------------------------------
FROM maven:3.8.2-jdk-11-slim as build

# Create app directory
WORKDIR /usr/src/app

# Resolve and cache dependencies
COPY ./pom.xml .
RUN mvn dependency:resolve

# Copy and build
COPY . .
RUN mvn clean package -DskipTests

# -----------------------------------------------------------------------------
# DEPLOYMENT STAGE
# -----------------------------------------------------------------------------
FROM openjdk:14-alpine as deployment

ARG WAR_NAME

WORKDIR /app

# Copy from build stage
COPY --from=build ./usr/src/app/target/${WAR_NAME}.war ./app.war

EXPOSE 8080

# Run
CMD java -jar app.war