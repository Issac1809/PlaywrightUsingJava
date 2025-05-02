# Use the official Playwright base image
FROM mcr.microsoft.com/playwright:v1.51.1-jammy

# Install JDK 21, Maven, Git, and other tools
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

# Install Google Chrome (optional if you use it)
RUN curl -sSL https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb -o google-chrome-stable_current_amd64.deb \
    && apt-get install -y ./google-chrome-stable_current_amd64.deb \
    && rm google-chrome-stable_current_amd64.deb

# Install Microsoft Edge via apt (optional but not enough for Playwright!)
RUN curl -sSL https://packages.microsoft.com/keys/microsoft.asc | apt-key add - \
    && echo "deb [arch=amd64] https://packages.microsoft.com/repos/edge stable main" | tee /etc/apt/sources.list.d/microsoft-edge.list \
    && apt-get update && apt-get install -y microsoft-edge-stable

# === 👇 KEY PART: Install Playwright-managed browsers (including msedge) ===
RUN mvn exec:java -e -Dexec.mainClass=com.microsoft.playwright.CLI -Dexec.args="install msedge"

# Set working directory
WORKDIR /workspace

# Set environment variables
ENV JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
ENV PATH="${JAVA_HOME}/bin:${PATH}"