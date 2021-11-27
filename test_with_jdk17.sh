#!/usr/bin/env bash

echo "**************************************************************"
echo "      Build, package and run using JDK17"
echo "      --> expect this fail with:"
echo "      --> 'attempted duplicate class definition'"
echo "**************************************************************"

# Create the Jar file
./gradlew bootJar

# Build a new image
docker build -t code.generation.issue -f docker/Dockerfile.17 .

# Run the container
docker run -p 9999:8666 --rm code.generation.issue
