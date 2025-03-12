# Account Management REST API

## Overview
This is a Spring Boot REST API for managing users, accounts, and transactions. 

### Features
- User can have multiple accounts.
- Each account can have multiple transactions.
- REST API with versioning.
- Uses **Spring Boot**, **Spring Data JPA**, and **H2 Database**.
- Exception handling with `NoDataFoundException`.
- Swagger documentation enabled.
- Logging using `@Slf4j`.
- Junit test cases

---
## Technologies Used
- Java 8
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- Swagger for API documentation

---
## Project Structure
```
/account-management-api
│── src/main/java/com/api
│   ├── controller          # REST controllers
│   ├── service             # Business logic layer
│   ├── repository          # Database repositories
│   ├── model               # Hibernate entity models
│   ├── dto                 # Data Transfer Objects
│   ├── exception           # Custom exceptions
│   ├── configuration       # Swagger & Logging configurations
│── pom.xml                 # Maven dependencies
│── README.md               # Project documentation
```

---
## Installation & Setup

### Prerequisites
- Install **Java 8**
- Install **Maven**
- Install **use H2 for in-memory database**

### Steps to Run
```sh
# Clone the repository
git clone https://github.com/your-repo/account-management-api.git
cd account-management-api

# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```
---
## API Endpoints
### **1. User APIs**
| Method | Endpoint | Description |
|--------|-------------|-------------|
| GET | `/api/v1/users/{id}` | Get a user by ID |


### **2. Account APIs**
| Method | Endpoint | Description |
|--------|-------------|-------------|
| GET | `/api/v1/accounts/{id}` | Get account details by ID |


### **3. Transaction APIs**
| Method | Endpoint | Description |
|--------|-------------|-------------|
| GET | `/api/v1/accounts/{accountnumber}/transactions` | Get all transactions for an account |

---
## Exception Handling
- `NoDataFoundException` (404) - If no data is found in DB.

---
## Swagger API Documentation
After starting the server, access Swagger UI at:
```
http://localhost:8080/swagger-ui/
```
## Junit Test Cases
Test cases added for unit testing


## Contributors
- **Shailesh Rai** - (mailto:shailesh.rai@synechron.com)

---
## License
This project is licensed under the Synechron License.

