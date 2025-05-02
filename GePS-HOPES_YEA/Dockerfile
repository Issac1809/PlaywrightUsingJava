# Use the official Playwright base image
FROM mcr.microsoft.com/playwright:v1.51.1-jammy

# Install JDK 21, Maven, Git, and other needed tools
RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    maven \
    dash \
    git \
    wget \
    curl \
    apt-transport-https \
    ca-certificates \
    && apt-get clean

# Install Google Chrome
RUN curl -sSL https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb -o google-chrome-stable_current_amd64.deb \
    && apt-get install -y ./google-chrome-stable_current_amd64.deb \
    && rm google-chrome-stable_current_amd64.deb

# Install Microsoft Edge
RUN curl -sSL https://packages.microsoft.com/keys/microsoft.asc | apt-key add - \
    && curl -sSL https://packages.microsoft.com/stable/microsoft-edge/stable/deb/$(lsb_release -cs) main | tee /etc/apt/sources.list.d/microsoft-edge.list \
    && apt-get update && apt-get install -y microsoft-edge-stable

# Set working directory
WORKDIR /workspace

# Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="${JAVA_HOME}/bin:${PATH}"