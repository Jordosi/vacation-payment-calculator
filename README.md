# 🏖️ Vacation Payment Calculator Microservice

[![Java](https://img.shields.io/badge/Java-21%2B-blue?logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4.4-brightgreen?logo=spring)](https://spring.io/projects/spring-boot)

A microservice for calculating vacation payments, taking holidays and weekends into account.

## 📋 Funсtionality

- [x] Vacation payment calculation using average salary
- [x] Holidays and weekends accounting (if vacation start date is provided)
- [x] Input parameters validation
- [x] Test coverage (integration tests)
## 🚀 Fast start

### Requirements
- Java 17+
- Maven 3.9+

### Setup
```bash
git clone https://github.com/Jordosi/vacation-payment-calculator.git
cd vacation-payment-calculator
mvn package
```
### Launch
```bash
java -jar target/vacation-payment-calculator-*.jar
```
## 📌 API Endpoints

### Main method
```
GET /calculate
```

**Parameters:**

| Parameter            | Type    | Obligatory | Description                      |
| -------------------- | ------- | ---------- | -------------------------------- |
| averageSalaryPerYear | Decimal | Yes        | Average annual salary (in RUB)   |
| vacationDays         | Integer | Yes        | Vacation length in days          |
| startVacationDate    | Date    | No         | Start vacation date (yyyy-MM-dd) |

**Request example:**

```bash
curl "http://localhost:8080/calculate?averageSalaryPerYear=100000&vacationDays=14"
```

**Response example:**

```json
{
  "vacationPaymentAmount": 47781.57
}
```
## 🧪 Testing
```bash
# All integration tests
mvn test
```

Test coverage:
- Integration tests: 85%+

## 🛠 Technology stack

- **Backend**: Java 21, Spring Boot 3.4.4

- Libraries: Lombok

- Testing: JUnit 5, Mockito

- Build: Maven


## 📂 Project structure
```
src/
├── main/
│   ├── java/
│   │   └── org/jordosi/
│   │       ├── advice/        # Unified controller exception handle
│   │       ├── controller/    # API endpoints
│   │       ├── common/        # Help classes
│   │       ├── service/       # Business-logic
│   │       └── model/         # DTO
│   │
│   └── resources/            # Properties
└── test/                     # Tests
```