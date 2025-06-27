#!/bin/bash
# Start script for Render.com deployment
echo "Starting Spring Boot application..."

# Try different Java paths that Render might use
JAVA_PATHS=(
    "/usr/lib/jvm/java-17-openjdk-amd64"
    "/usr/lib/jvm/java-17-openjdk"
    "/usr/lib/jvm/java-11-openjdk-amd64"
    "/usr/lib/jvm/java-11-openjdk"
    "/usr/lib/jvm/java-8-openjdk-amd64"
    "/usr/lib/jvm/java-8-openjdk"
)

for java_path in "${JAVA_PATHS[@]}"; do
    if [ -d "$java_path" ]; then
        echo "Found Java at: $java_path"
        export JAVA_HOME="$java_path"
        export PATH="$java_path/bin:$PATH"
        break
    fi
done

# Check if Java is available
if command -v java &> /dev/null; then
    echo "Using Java: $(java -version 2>&1 | head -n 1)"
    java -jar target/*.jar
else
    echo "ERROR: Java not found! Available paths:"
    ls -la /usr/lib/jvm/ 2>/dev/null || echo "No /usr/lib/jvm/ directory"
    exit 1
fi 