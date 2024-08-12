
# Slack Message Sender

## Overview

This Spring Boot application provides a REST API to send messages to a predefined Slack channel using Slack's Incoming Webhooks. The application is built using Spring Boot and integrates with Slack via HTTP requests using the `RestTemplate`.

## Table of Contents

- [Features](#features)
- [Project Structure](#project-structure)
- [Requirements](#requirements)
- [Setup and Configuration](#setup-and-configuration)
  - [1. Clone the Repository](#1-clone-the-repository)
  - [2. Set Up Slack Incoming Webhook](#2-set-up-slack-incoming-webhook)
  - [3. Configure Application Properties](#3-configure-application-properties)
  - [4. Build and Run the Application](#4-build-and-run-the-application)
- [API Endpoints](#api-endpoints)
  - [POST /sendSlackMessage](#post-sendslackmessage)
- [Testing](#testing)
- [Running Tests](#running-tests)
- [Troubleshooting](#troubleshooting)
- [License](#license)

## Features

- **Send Messages to Slack**: Provides an API endpoint to send messages to a Slack channel using Incoming Webhooks.
- **REST API**: Exposes a RESTful service to accept POST requests with JSON payloads.
- **Spring Boot**: Built using Spring Boot, leveraging its simplicity and powerful features.
- **Configuration via Properties**: Slack webhook URL is managed via the application properties file.

## Project Structure

```
slackproject/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/slackproject/slackproject/
│   │   │       ├── SlackMessageController.java
│   │   │       ├── SlackprojectApplication.java
│   │   │       └── AppConfig.java
│   │   └── resources/
│   │       ├── application.properties
│   ├── test/
│   │   ├── java/
│   │   │   └── com/slackproject/slackproject/
│   │   │       └── SlackMessageControllerTest.java
├── pom.xml
└── README.md
```

## Requirements

- Java 8 or later
- Maven 3.6.0 or later
- Slack Account (to set up an Incoming Webhook)

## Setup and Configuration

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/slackproject.git
cd slackproject
```

### 2. Set Up Slack Incoming Webhook

1. Go to your Slack workspace.
2. Navigate to **Apps** and search for **Incoming Webhooks**.
3. Set up a new Incoming Webhook by selecting a channel and copying the provided Webhook URL.

### 3. Configure Application Properties

1. Open the `src/main/resources/application.properties` file.
2. Paste the Slack Webhook URL:

```properties
slack.webhook.url=https://hooks.slack.com/services/T00000000/B00000000/XXXXXXXXXXXXXXXXXXXXXXXX
```

### 4. Build and Run the Application

To build and run the application, use the following Maven commands:

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

Alternatively, you can run the application directly from your IDE (e.g., VS Code, IntelliJ, Eclipse).

## API Endpoints

### POST /sendSlackMessage

- **Description**: Sends a message to a Slack channel via the configured Incoming Webhook.
- **URL**: `/sendSlackMessage`
- **Method**: `POST`
- **Content-Type**: `application/json`
- **Request Body**:
  - `message` (String): The message to send to the Slack channel.

**Example Request**:

```bash
curl -X POST http://localhost:8080/sendSlackMessage \
-H "Content-Type: application/json" \
-d '{"message":"Hello, Slack!"}'
```

**Example Response**:

```json
{
  "status": "Message sent to Slack"
}
```

## Testing

### Running Tests

To run the tests, execute:

```bash
mvn test
```

**Test Overview**:

- **Unit Tests**: The `SlackMessageControllerTest` class uses `MockMvc` to test the `/sendSlackMessage` endpoint.
- **Mocking**: The `RestTemplate` used for sending HTTP requests to Slack is mocked during tests to avoid real HTTP calls.

## Troubleshooting

- **Messages not appearing in Slack**: Ensure the correct Slack Webhook URL is set in `application.properties`.
- **Application fails to start**: Double-check the Maven dependencies and ensure the `RestTemplate` bean is configured correctly.
- **Test Issues**: If tests fail, make sure the `@MockBean` annotations are used to mock the `RestTemplate`.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
