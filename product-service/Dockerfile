# ---- Step 1: Build ----
FROM gradle:9.5-jdk25-alpine AS build
WORKDIR /app
COPY gradle.properties build.gradle.kts settings.gradle.kts ./
RUN gradle dependencies --no-daemon
COPY src ./src
RUN gradle build -x test --no-daemon

# ---- Step 2: Run ----
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/build/quarkus-app /app/quarkus-app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/quarkus-app/quarkus-run.jar"]
