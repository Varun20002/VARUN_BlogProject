# BlogProject

A Spring Boot-based **Blog Management Application** with REST APIs, Spring Security, AOP logging, and global exception handling.  
The project demonstrates best practices in **layered architecture**, **exception management**, and **test-driven development**.

---

## ✨ Features

- 📖 Manage blog posts (CRUD operations)
- 🔒 Role-based access control with **Spring Security**
- 🛡️ Global Exception Handling
- 🧩 Aspect-Oriented Programming (AOP) for request logging
- 🧪 Unit & Integration Tests
- ⚡ Built with **Spring Boot** and **Gradle**

---

## 🛠️ Tech Stack

- **Java 21**  
- **Spring Boot 3.x**  
- **Spring Data JPA**  
- **Spring Security**  
- **AOP (AspectJ)**  
- **JUnit & Mockito** for testing  
- **Gradle** for build and dependency management  
- Database: (configure in `application.properties`, e.g. MySQL)

---

## 📂 Project Structure

```
src/main/java/com/example/demo
│── BlogProjectApplication.java    # Main Spring Boot application
│── SecurityConfiguration.java     # Spring Security config
│
├── Controller/
│   └── BlogsController.java       # REST API endpoints
│
├── Service/
│   └── BlogsService.java          # Business logic
│
├── Repository/
│   ├── Blogs.java                 # Entity
│   └── BlogRepository.java        # JPA repository
│
├── Exceptions/
│   ├── GlobalExceptionHandler.java    # Handles all exceptions
│   ├── ResourceNotFoundException.java
│   └── BadException.java
│
└── Aspect/
    └── LoggingAspect.java         # Logs API calls
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Kiran272003/BlogPorject.git
cd BlogPorject
```

### 2️⃣ Build the Project

### 3️⃣ Run the Application

The app will start on [http://localhost:8080](http://localhost:8080)

---

## 🔑 API Endpoints

| Method | Endpoint         | Description         |
|--------|------------------|--------------------|
| GET    | /blogs           | Fetch all blogs    |
| GET    | /blogs/{id}      | Fetch blog by ID   |
| POST   | /blogs           | Create new blog    |
| PUT    | /blogs/{id}      | Update blog        |
| DELETE | /blogs/{id}      | Delete blog        |

---

## 🧪 Testing

Run all tests:

```bash
./gradlew test
```

Includes:
- BlogControllerTest
- BlogRepositoryTest
- ControllerIntegrationTest

---

## 🔒 Security

Role-based access for Admins & Users is configured in `SecurityConfiguration.java`.

---
