# Simple Java HTTP Server

A basic HTTP server written in Java for educational purposes. This project demonstrates core server concepts including socket programming, multi-threading, and configuration management, using SLF4J for logging and JSON for server configuration.

## Features

- Serves static HTML content
- Multi-threaded request handling
- Basic logging using SLF4J + Logback
- Configurable via JSON file

## Project Structure
simplehttpserver/ ├── src/ │ └── main/ │ └── java/ │ └── com/ │ └── coderfromscratch/ │ └── httpserver/ │ ├── HttpServer.java │ └── core/ │ ├── ServerListenerThread.java │ └── HttpConnectionWorkerThread.java │ └── resources/ │ └── http.json

## Getting Started

### Prerequisites

- Java 21+
- Maven 3.6+

### Run
bash
mvn exec:java -Dexec.mainClass="com.coderfromscratch.httpserver.HttpServer"
Configuration
The server reads its configuration from a JSON file (src/main/resources/http.json).

### json
{
  "port": 8080,
  "webroot": "www"
}
Logging
This project uses SLF4J with Logback for logging. Logs include startup information and connection details.

License
This project is licensed under the MIT License.
