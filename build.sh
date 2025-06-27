#!/bin/bash
# Build script for Render.com deployment
echo "Building Spring Boot application..."

# Make Maven wrapper executable
chmod +x mvnw

# Run Maven build
./mvnw clean package -DskipTests

echo "Build completed!" 