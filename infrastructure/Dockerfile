# Base image with JDK 21
FROM eclipse-temurin:21-jdk

# Install Docker CLI and Git
RUN apt-get update && apt-get install -y \
    docker.io \
    git \
    && apt-get clean && rm -rf /var/lib/apt/lists/*

# Set up environment variables for Docker
ENV DOCKER_HOST=tcp://docker:2375
ENV DOCKER_TLS_CERTDIR=""
