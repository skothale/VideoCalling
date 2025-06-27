#!/bin/bash
# Start script for Render.com deployment
echo "Starting Spring Boot application..."

# Set Java environment if not already set
if [ -z "$JAVA_HOME" ]; then
    export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
    export PATH=$JAVA_HOME/bin:$PATH
fi

# Check if Java is available
if command -v java &> /dev/null; then
    echo "Using Java: $(java -version 2>&1 | head -n 1)"
    java -jar target/*.jar
else
    echo "ERROR: Java not found!"
    exit 1
fi 