#!/bin/bash
# Build script for Render.com deployment
echo "Building Spring Boot application..."

# Check if we're in a Java environment
if command -v java &> /dev/null; then
    echo "Java found: $(java -version 2>&1 | head -n 1)"
else
    echo "Java not found in PATH, trying to set JAVA_HOME..."
    export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
    export PATH=$JAVA_HOME/bin:$PATH
fi

# Make Maven wrapper executable and run build
chmod +x mvnw
./mvnw clean package -DskipTests

echo "Build completed!" 