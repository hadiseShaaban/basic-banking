# 🏦 Basic Banking API

A simple RESTful banking system built with Java and Spring Boot.  
This project simulates basic banking operations such as account creation, deposits, withdrawals, and balance inquiry.

---

## 🧩 Features

- Create and manage bank accounts  
- Deposit and withdraw money  
- Transfer funds between accounts  
- Get account balance and transaction history  
- In-memory or database-backed storage  
- RESTful API with JSON responses  
- Swagger UI for easy testing

---

## 🛠 Technologies

- Java 17  
- Spring Boot 3.3.x  
- Spring Web, Spring Data JPA  
- H2 (in-memory database)  
- Lombok  
- Swagger / OpenAPI  
- Maven

---

## ⚙️ Getting Started

### Prerequisites

- Java 17  
- Maven

### Clone the Repository

```bash
git clone https://github.com/hadiseShaaban/basic-banking.git
cd basic-banking
Run the App
bash
Copy
Edit
mvn spring-boot:run
By default, the application runs at:
http://localhost:8080

🔍 API Documentation
Access Swagger UI at:
http://localhost:8080/swagger-ui.html

📦 Sample Endpoints
Method	Endpoint	Description
POST	/accounts	Create a new bank account
GET	/accounts/{id}	Get account details
POST	/accounts/{id}/deposit	Deposit money
POST	/accounts/{id}/withdraw	Withdraw money
POST	/accounts/transfer	Transfer funds between accounts
Use Swagger UI or tools like Postman to try these endpoints.

🧪 Running Tests
bash
Copy
Edit
mvn test
📂 Project Structure
controller/ – REST API endpoints

service/ – Business logic

repository/ – Data persistence

model/ – Domain entities (e.g., Account, Transaction)

dto/ – Request/response models

config/ – Swagger, caching, or database configs

🙋‍♀️ Author
Programmed by Hadise Shaaban

