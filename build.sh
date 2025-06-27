#!/bin/bash
# Build script for Render.com deployment
echo "Building Spring Boot application..."

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
    echo "Java found: $(java -version 2>&1 | head -n 1)"
else
    echo "Java not found, trying to install..."
    # Try to install Java if not found
    apt-get update && apt-get install -y openjdk-17-jdk || yum install -y java-17-openjdk || echo "Could not install Java"
fi

# Make Maven wrapper executable and run build
chmod +x mvnw
./mvnw clean package -DskipTests

echo "Build completed!" 