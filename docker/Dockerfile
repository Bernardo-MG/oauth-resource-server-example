# -----------------------------------------------------------------------------
# BUILD STAGE
# -----------------------------------------------------------------------------
FROM maven:3.9.9-eclipse-temurin-22-alpine AS build

# Create app directory
WORKDIR /app

# Resolve and cache dependencies
COPY ./pom.xml .
RUN mvn dependency:go-offline

# Copy and build
COPY ./src ./src
RUN mvn --batch-mode clean package -DskipTests

# -----------------------------------------------------------------------------
# DEPLOYMENT STAGE
# -----------------------------------------------------------------------------
FROM eclipse-temurin:22-jre-alpine AS deployment

WORKDIR /app

# Exposed ports
EXPOSE 8080
EXPOSE 8000

# Health check
HEALTHCHECK --interval=30s --retries=5 --timeout=10s CMD wget -T5 -qO- http://localhost:8080/actuator/health | grep UP || exit 1

# Create runner user
RUN addgroup -S runners && \
    adduser --disabled-password -S runner -G runners

# Add logs folder and assign to runner user
RUN mkdir ./logs && \
    chown runner ./logs
VOLUME ./logs

# Change to runner user
USER runner

# Copy from build stage
COPY --from=build ./app/target/*.war ./app.war

# Run with remote debugging
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:8000", "-jar", "app.war"]