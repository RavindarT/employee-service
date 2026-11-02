# Employee Service — Spring Boot + Maven

A small REST API that demonstrates a clean Spring Boot project, ready to commit to GitHub.

## Technology

- Java 21
- Spring Boot 4.1.2 (stable release)
- Maven (3.6.3+)
- Spring Web MVC, Bean Validation, Actuator
- JUnit 5 unit tests
- GitHub Actions continuous integration

## Features

- Create, list, retrieve, update and delete employees
- Validates required name, email and department
- `404 Not Found` for missing employee IDs
- `400 Bad Request` with field validation messages
- Health endpoint at `/actuator/health`
- Thread-safe, **in-memory storage for demonstration**; data resets on restart

## Requirements

Install JDK 21 and Maven 3.6.3 or later. Confirm with:

```bash
java -version
mvn -version
```

## Run the application

From inside the `employee-service` directory:

```bash
mvn clean test
mvn spring-boot:run
```

The API listens on **http://localhost:8080**. You can instead build and run a JAR:

```bash
mvn clean package
java -jar target/employee-service-0.0.1-SNAPSHOT.jar
```

To change the listening port, set an environment variable named `PORT`, e.g. `PORT=9090 mvn spring-boot:run` on macOS/Linux or `$env:PORT=9090; mvn spring-boot:run` in PowerShell.

## API endpoints

| Method | Route | Purpose | Success status |
|---|---|---|---|
| GET | `/api/v1/employees` | List employees | 200 |
| GET | `/api/v1/employees/{id}` | Get employee | 200 |
| POST | `/api/v1/employees` | Create employee | 201 |
| PUT | `/api/v1/employees/{id}` | Update employee | 200 |
| DELETE | `/api/v1/employees/{id}` | Delete employee | 204 |
| GET | `/actuator/health` | Application health | 200 |

### Create an employee

```bash
curl -i -X POST http://localhost:8080/api/v1/employees \
  -H 'Content-Type: application/json' \
  -d '{"name":"Asha","email":"asha@example.com","department":"Engineering"}'
```

Response example:

```json
{
  "id": 1,
  "name": "Asha",
  "email": "asha@example.com",
  "department": "Engineering"
}
```

### List employees

```bash
curl http://localhost:8080/api/v1/employees
```

### Update an employee

```bash
curl -X PUT http://localhost:8080/api/v1/employees/1 \
  -H 'Content-Type: application/json' \
  -d '{"name":"Asha R","email":"asha.r@example.com","department":"Platform"}'
```

### Delete an employee

```bash
curl -i -X DELETE http://localhost:8080/api/v1/employees/1
```

Use `api-examples.http` in IntelliJ IDEA to send example requests directly.

## Upload to GitHub

1. Create a **new empty** repository named `employee-service` at https://github.com/new. Do not initialize it with a README, license, or `.gitignore`, as these are already included here.
2. Open a terminal in the extracted `employee-service` directory.
3. Run the following commands, replacing `YOUR_USERNAME` with your GitHub username:

```bash
git init
git add .
git commit -m "Initial Spring Boot Maven employee API"
git branch -M main
git remote add origin https://github.com/YOUR_USERNAME/employee-service.git
git push -u origin main
```

You must authenticate with GitHub when prompted (for HTTPS use the browser/credential helper or an approved personal access token, **not** your account password). If the repository uses an SSH remote, substitute that remote URL. 

Each push to `main` and each pull request targeting `main` triggers the Maven test workflow in `.github/workflows/maven.yml`.

## Optional Docker image

After running `mvn clean package`:

```bash
docker build -t employee-service:local .
docker run --rm -p 8080:8080 employee-service:local
```

## Project layout

```text
employee-service/
├── .github/workflows/maven.yml   # Build on GitHub
├── src/main/java/com/example/employeeservice/
│   ├── EmployeeServiceApplication.java
│   └── employee/
│       ├── Employee.java
│       ├── EmployeeRequest.java
│       ├── EmployeeController.java
│       ├── EmployeeService.java
│       ├── EmployeeNotFoundException.java
│       └── ApiExceptionHandler.java
├── src/main/resources/application.yml
├── src/test/java/com/example/employeeservice/employee/EmployeeServiceTest.java
├── pom.xml
├── api-examples.http
├── Dockerfile
└── README.md
```

## Next steps for production

This is an educational starter, not a production-ready deployment. Before production, add a real database (such as PostgreSQL), authentication/authorization, database migrations, API integration tests and environment-specific configuration. Do not put passwords or secrets into GitHub.
