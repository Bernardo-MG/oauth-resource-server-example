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

WORKDIR /app

# Copy from build stage
COPY --from=build ./usr/src/app/target/*.war ./app.war

# Exposed ports
EXPOSE 8080
EXPOSE 8000

# Health check
HEALTHCHECK --interval=30s --retries=5 --timeout=10s CMD curl --fail --silent localhost:8080/actuator/health | grep UP || exit 1

# Run with remote debugging
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:8000", "-jar", "app.war"]