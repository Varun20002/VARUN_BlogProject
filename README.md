
```markdown
# 🚀 logProject
A Spring Boot-based Blog Management Application with REST APIs, OAuth2 authentication, role-based access control, AOP logging, and global exception handling.  
The project demonstrates best practices in layered architecture, exception management, database schema design, and test-driven development.

---

## ✨ Features
- 📖 Blog Management – CRUD operations with ownership validation  
- 👤 User Management – registration, authentication, profile updates, role assignment  
- 🔐 OAuth2 Authentication with JWT tokens  
- 🛡️ Role-based access control (ADMIN, USER)  
- 🔏 Method-level security with `@PreAuthorize`  
- 🔒 Ownership-based permissions for user-blog relationships  
- 🌍 Public read access, authenticated write access  
- 🛡️ Global Exception Handling  
- 🧩 AOP Logging – request & response tracking  
- 🧪 Unit & Integration Tests (with security tests)  
- 📊 Database schema design with entity relationships & integrity rules  
- ⚡ Built with Spring Boot + Gradle  

---

## 🛠️ Tech Stack
- Java 21  
- Spring Boot 3.x  
- Spring Data JPA  
- Spring Security (OAuth2 & JWT)  
- AspectJ (AOP)  
- JUnit & Mockito for testing  
- Gradle (build & dependency management)  
- Database: MySQL (configurable in `application.properties`)  
- Docker for deployment  

---

## 📂 Project Structure
```

src/
└── main/
├── java/com/example/demo/
│    ├── BlogProjectApplication.java      # Main Spring Boot application
│    ├── SecurityConfiguration.java       # Spring Security & JWT config
│    │
│    ├── controller/
│    │    ├── BlogsController.java        # Blog REST API endpoints
│    │    └── UserController.java         # User management APIs
│    │
│    ├── service/
│    │    ├── BlogsService.java           # Blog business logic
│    │    └── UserService.java            # User management logic
│    │
│    ├── repository/
│    │    ├── BlogRepository.java         # Blog JPA repository
│    │    ├── UserRepository.java         # User JPA repository
│    │    └── entities/
│    │         ├── Blog.java              # Blog entity
│    │         ├── User.java              # User entity
│    │         └── Role.java              # Role entity
│    │
│    ├── security/
│    │    ├── JwtTokenProvider.java       # JWT utilities
│    │    ├── JwtAuthenticationFilter.java# Authentication filter
│    │    └── CustomUserDetailsService.java # Loads user details
│    │
│    ├── exceptions/
│    │    ├── GlobalExceptionHandler.java # Handles all exceptions
│    │    ├── ResourceNotFoundException.java
│    │    └── BadRequestException.java
│    │
│    └── aspect/
│         └── LoggingAspect.java          # Logs API calls
│
└── resources/
├── application.properties           # App configuration
└── schema.sql                       # DB schema (if any)

````

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Varun2002/VARUN_BlogProject.git
cd BlogProject
````

### 2️⃣ Configure the Database

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogdb
spring.datasource.username=your-username
spring.datasource.password=your-password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3️⃣ Build the Project

```bash
./gradlew build
```

### 4️⃣ Run the Application

```bash
./gradlew bootRun
```

👉 The app will start at: [http://localhost:8080](http://localhost:8080)

---

## 🔑 API Endpoints

### Authentication & Users

| Method | Endpoint            | Description                  |
| ------ | ------------------- | ---------------------------- |
| POST   | `/auth/register`    | Register a new user          |
| POST   | `/auth/login`       | Authenticate & get JWT token |
| GET    | `/users/{id}`       | Get user profile             |
| PUT    | `/users/{id}`       | Update user profile          |
| POST   | `/users/{id}/roles` | Assign roles to user         |

### Blogs

| Method | Endpoint      | Description                     |
| ------ | ------------- | ------------------------------- |
| GET    | `/blogs`      | Fetch all blogs (public)        |
| GET    | `/blogs/{id}` | Fetch blog by ID                |
| POST   | `/blogs`      | Create new blog (auth required) |
| PUT    | `/blogs/{id}` | Update blog (owner/admin only)  |
| DELETE | `/blogs/{id}` | Delete blog (owner/admin only)  |

---

## 🧪 Testing

Run all tests:

```bash
./gradlew test
```

Includes:

* BlogControllerTest
* BlogRepositoryTest
* UserServiceTest
* ControllerIntegrationTest
* Security & performance tests

---

## 🔒 Security Highlights

* OAuth2 + JWT authentication
* Role-based access control (Admin & User)
* Ownership-based permissions (only blog owner can update/delete)
* Method-level security with `@PreAuthorize`
* Passwords encrypted with BCrypt

---

## 🚀 Deployment

* Run locally with Gradle
* Dockerized for containerized deployment
* Environment-specific configurations supported

