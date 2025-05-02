# Use the official Playwright base image
FROM mcr.microsoft.com/playwright:v1.51.1-jammy

# Install JDK 21, Maven, Git, and other needed tools
RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    maven \
    dash \
    git \
    && apt-get clean

# Set working directory
WORKDIR /workspace

# Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="${JAVA_HOME}/bin:${PATH}"