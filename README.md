# Journal

A small Spring Boot application for tracking trades.

## What this is

This repository contains a simple Spring Boot web application (Java 17, Maven) used as a personal trading journal.

## Prerequisites

- JDK 17
- Git (optional)
- Maven wrapper is included, so Maven installed locally is optional

## Build

Open PowerShell in the project root (where `pom.xml` is located) and run:

```powershell
./mvnw.cmd clean package
```

This will produce `target/journal-0.0.1-SNAPSHOT.jar`.

If you prefer a local Maven installation, use:

```powershell
mvn clean package
```

## Run

Run the packaged JAR with:

```powershell
java -jar target\journal-0.0.1-SNAPSHOT.jar
```

By default the application binds to port 8080. You can override the port by setting `server.port` in `application.properties` or via command-line:

```powershell
java -jar target\journal-0.0.1-SNAPSHOT.jar --server.port=8081
```

## Tests

Run unit tests with:

```powershell
./mvnw.cmd test
```

## Project structure

- `src/main/java` – application source
- `src/main/resources` – configuration and templates/static assets
- `src/test` – tests

## Contributing

Feel free to open issues or submit PRs. Keep changes small and add tests where applicable.
