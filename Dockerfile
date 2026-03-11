FROM eclipse-temurin:25-jdk-alpine AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./
COPY src src

RUN chmod +x ./gradlew
RUN ./gradlew build -x test

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

COPY --from=build /app/build/libs/*.jar api-alumnos.jar

EXPOSE 8080

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar opentelemetry-javaagent.jar

ENTRYPOINT ["java", "-javaagent:opentelemetry-javaagent.jar", "-Dotel.service.name=api-post-alumnos", "-Dotel.exporter.otlp.endpoint=http://172.31.11.48:4317", "-jar", "api-alumnos.jar"]