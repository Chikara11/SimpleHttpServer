
# HTTP Server

## Overview
This project is a simple HTTP server written in Java using Maven. It supports **multithreading** to handle multiple client requests simultaneously and implements **GET** and **HEAD** request methods. The server parses incoming requests, validates them, and returns appropriate responses similar to a standard HTTP server.

## Features
- **Multithreading**: Handles multiple clients concurrently by spawning a new thread for each request.
- **Request Methods Supported**:
  - **GET**: Returns the requested resource.
  - **HEAD**: Returns headers for the requested resource without the body content.
- **Request Parsing**: The server verifies and parses the request line and headers.
- **Custom Exception Handling**: Implements custom exceptions for unsupported methods and other HTTP parsing errors.

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/HttpServer.git
   ```
2. Navigate to the project directory:
   ```bash
   cd HttpServer
   ```
3. Build the project using Maven:
   ```bash
   mvn clean install
   ```

## Usage
1. Run the server:
   ```bash
   java -jar target/HttpServer-1.0.jar
   ```
2. The server listens on `localhost:8080` by default. You can test it by opening a browser and navigating to `http://localhost:8080`.

## Example
- **GET Request**:
  ```bash
  curl -X GET http://localhost:8080/page.html
  ```
- **HEAD Request**:
  ```bash
  curl -I http://localhost:8080/page.html
  ```
  
## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
