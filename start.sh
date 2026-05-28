#!/usr/bin/env bash
set -e

# Build the Java project with Maven
mvn -B clean package -DskipTests

# Start the Spring Boot application from the generated JAR
exec java -jar target/*.jar
