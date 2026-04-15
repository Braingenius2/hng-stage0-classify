# HNG Stage 0 - Backend Classification API

This is a REST API built with Java and Spring Boot that integrates with the external [Genderize API](https://api.genderize.io). It processes name predictions and returns a structured JSON response indicating the predicted gender, statistical probability, and confidence level.

## Technologies Used
- Java 21
- Spring Boot 3.4.x
- Maven (Build Tool)

## API Endpoint

### `GET /api/classify?name=<name>`

**Parameters:**
- `name` (required): A string representing the name to be classified.

**Success Response (200 OK):**
```json
{
  "status": "success",
  "data": {
    "name": "brain",
    "gender": "male",
    "probability": 1.0,
    "sample_size": 26,
    "is_confident": false,
    "processed_at": "2026-04-14T20:45:00.000Z"
  }
}
```

**Error Handling:**
- Missing `name` parameter returns HTTP `400 Bad Request`.
- Empty `name` parameter returns HTTP `400 Bad Request`.
- Name not found in Genderize API returns HTTP `422 Unprocessable Entity`.

```json
{
  "status": "error",
  "message": "The 'name' parameter is required"
}
```

## Local Setup

1. Clone the repository
2. Navigate to the project root directory
3. Run the application using the Maven Wrapper:
   ```bash
   ./mvnw spring-boot:run
   ```
4. Access the API at `http://localhost:8080/api/classify?name=john`

## Author
Fortune C.
