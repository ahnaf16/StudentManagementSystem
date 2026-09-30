# Student Management System

A Spring Boot-based backend for managing students, providing a fully functional REST API.

## Features

- **CRUD Operations**: Create, read, update, and delete student records.
- **RESTful API**: Standardized endpoints under `/api/students`.
- **In-Memory Database**: Utilizes an H2 in-memory database for immediate development and testing without prior setup.
- **Automatic Schema Generation**: Managed by Hibernate based on JPA models.

## Tech Stack

- **Java**: 17
- **Framework**: Spring Boot 3.4.0
- **Database**: H2 (In-memory)
- **Data Access**: Spring Data JPA
- **Tools**: Lombok, Maven Wrapper

## Prerequisites

- JDK 17 installed on your machine.
- (Optional) An IDE like IntelliJ IDEA, Eclipse, or VS Code.

## Getting Started

### Running the Application

You can start the application directly using the Maven Wrapper included in the project. There is no need to install Maven on your machine.

**On Windows:**

```bash
.\mvnw.cmd spring-boot:run
```

**On Linux/Mac:**

```bash
./mvnw spring-boot:run
```

The application will start up on `http://localhost:8080`.

### H2 Database Console

To inspect the database tables and data visually:

1. Navigate to: `http://localhost:8080/h2-console`
2. Ensure the **JDBC URL** is set to `jdbc:h2:mem:studentdb`
3. Click **Connect** (Leave the username as `sa` and password as `password`).

## API Endpoints

The API is exposed at `http://localhost:8080/api/students`.

| Method | Endpoint | Description | Request Body |
| -------- | ---------- | ------------- | -------------- |
| `GET` | `/api/students` | Retrieve all students | - |
| `GET` | `/api/students/{id}` | Retrieve a specific student by ID | - |
| `POST` | `/api/students` | Create a new student | `{ "firstName": "John", "lastName": "Doe", "email": "john@example.com" }` |
| `PUT` | `/api/students/{id}` | Update an existing student | `{ "firstName": "Jane", "lastName": "Doe", "email": "jane@example.com" }` |
| `DELETE` | `/api/students/{id}` | Delete a student by ID | - |

### Example Request (Creating a Student)

```bash
curl -X POST http://localhost:8080/api/students \
-H "Content-Type: application/json" \
-d '{"firstName": "Ahnaf", "lastName": "Doe", "email": "ahnaf.doe@example.com"}'
```

## Project Structure

- `model/Student.java`: The JPA entity representing a student.
- `repository/StudentRepository.java`: Interface handling database queries and operations.
- `service/StudentService.java`: The core logic handling operations between the controller and the repository.
- `controller/StudentController.java`: The REST API layer exposing endpoints to the client.
