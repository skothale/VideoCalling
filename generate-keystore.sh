#!/bin/bash

# Generate self-signed certificate for HTTPS development
echo "Generating self-signed certificate for HTTPS..."

# Create keystore directory if it doesn't exist
mkdir -p src/main/resources

# Generate keystore with self-signed certificate
keytool -genkeypair \
  -alias tomcat \
  -storetype PKCS12 \
  -keyalg RSA \
  -keysize 2048 \
  -keystore src/main/resources/keystore.p12 \
  -validity 365 \
  -storepass password \
  -keypass password \
  -dname "CN=localhost, OU=Development, O=VideoCallingApp, L=City, S=State, C=US"

echo "Certificate generated successfully!"
echo "To enable HTTPS, uncomment the SSL configuration in application.properties"
echo "Then restart the backend server."
echo ""
echo "Note: You'll need to accept the self-signed certificate in your browser." 