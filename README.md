# spring-ai-greeting-generator

Spring Boot application using Spring AI to generate role-based greeting messages and product descriptions.

## Features

- Role-based AI greeting generator
- Product description generator by category
- Bonus support for `MANAGER` and `MOBILE_PHONE`
- Spring Boot 3 + Spring AI integration

## Tech stack

- Java 17
- Spring Boot 3.3.4
- Spring AI 1.0.0-M6
- Maven

## Prerequisites

- Java 17+
- Maven 3.9+
- OpenAI API key

## Setup

1. Export your OpenAI API key:

```bash
export OPENAI_API_KEY=your_api_key_here
```

2. Run the application:

```bash
mvn spring-boot:run
```

## API endpoints

### Greeting

POST `/api/greeting`

Request body:

```json
{
  "role": "STUDENT",
  "name": "Rahul"
}
```

Response:

```text
Hello Rahul! Keep learning and achieving your goals.
```

### Product description

POST `/api/product`

Request body:

```json
{
  "category": "ELECTRONICS",
  "productName": "Wireless Bluetooth Headphones"
}
```

Response:

```text
Wireless Bluetooth Headphones deliver crystal-clear sound, long battery life, and seamless connectivity for work and entertainment.
```

## Bonus roles/categories

- `MANAGER`
- `MOBILE_PHONE`

## Notes

The application uses Spring AI's OpenAI integration. The prompts are tuned to produce short, natural text responses consistent with the problem statement.
