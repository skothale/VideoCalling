#!/bin/bash
# Build script for Render.com deployment
echo "Building Spring Boot application..."
chmod +x mvnw
./mvnw clean package -DskipTests
echo "Build completed!" 