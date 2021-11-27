#!/usr/bin/env bash

echo "**************************************************************"
echo "      Build, package and run using JDK11"
echo "      --> expect this to work fine"
echo "**************************************************************"

# Create the Jar file
./gradlew bootJar

# Build a new image
docker build -t code.generation.issue -f docker/Dockerfile.11 .

# Run the container
docker run -p 9999:8666 --rm code.generation.issue
