# BlogProject

A comprehensive Spring Boot-based **Blog Management Application** with REST APIs, OAuth2 authentication, user management, Spring Security, AOP logging, and global exception handling.  
The project demonstrates best practices in **layered architecture**, **security implementation**, **user authentication**, and **test-driven development**.

---

## ✨ Features

- 📖 **Blog Management** - Complete CRUD operations for blog posts
- 👤 **User Management** - User registration, authentication, and profile management
- 🔐 **OAuth2 Authentication** - Secure login with JWT token support
- 🔒 **Role-based & Ownership-based Access Control** - Admin and user permissions
- 🛡️ **Global Exception Handling** - Comprehensive error management
- 🧩 **Aspect-Oriented Programming (AOP)** - Request logging and monitoring
- 🧪 **Unit & Integration Tests** - Comprehensive test coverage
- ⚡ **Modern Architecture** - Built with **Spring Boot** and **Gradle**

---

## 🛠️ Tech Stack

- **Java 17**  
- **Spring Boot 3.x**  
- **Spring Data JPA**  
- **Spring Security** with OAuth2
- **JWT (JSON Web Tokens)**  
- **AOP (AspectJ)**  
- **JUnit & Mockito** for testing  
- **Gradle** for build and dependency management  
- **H2 Database** (in-memory for testing)

---

## 📂 Project Structure

```
src/main/java/com/example/demo
├── BlogProjectApplication.java    # Main Spring Boot application
├── SecurityConfiguration.java     # Spring Security & OAuth2 config
│
├── Controller/
│   ├── BlogsController.java       # Blog REST API endpoints
│   └── UserController.java        # User management endpoints
│
├── Service/
│   ├── BlogsService.java          # Blog business logic
│   └── UserService.java           # User business logic
│
├── Repository/
│   ├── Blogs.java                 # Blog entity with user relationship
│   ├── Users.java                 # User entity with role management
│   ├── BlogRepository.java        # Blog JPA repository
│   └── UserRepository.java        # User JPA repository
│
├── Exceptions/
│   ├── GlobalExceptionHandler.java    # Handles all exceptions
│   ├── ResourceNotFoundException.java
│   └── BadException.java
│
└── Aspect/
    └── LoggingAspect.java         # Logs API calls and performance
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/Varun20002/VARUN_BlogProject.git
cd VARUN_BlogProject
```

### 2️⃣ Build the Project
```bash
./gradlew build
```

### 3️⃣ Run the Application
```bash
./gradlew bootRun
```

👉 The app will start at: [http://localhost:8080](http://localhost:8080)

---

## 🔑 API Endpoints

### Blog Management
| Method | Endpoint         | Description         | Access Level |
|--------|------------------|--------------------|--------------|
| GET    | /blogs           | Fetch all blogs    | Public |
| GET    | /blogs/{id}      | Fetch blog by ID   | Public |
| POST   | /blogs           | Create new blog    | Public |
| DELETE | /blogs/{id}      | Delete blog        | Public |

### User Management
| Method | Endpoint         | Description         | Access Level |
|--------|------------------|--------------------|--------------|
| GET    | /users           | Fetch all users    | Public |
| GET    | /users/{id}      | Fetch user by ID   | Public |
| POST   | /users           | Create new user    | Public |
| PUT    | /users/{id}      | Update user        | Public |
| DELETE | /users/{id}      | Delete user        | Public |

---

## 🔒 Security Features

### Authentication
- **OAuth2 Login** with JWT tokens
- **User Registration** and profile management
- **Principal-based** user identification
- **Session Management** with secure tokens

### Authorization
- **Role-based Access**: ADMIN, USER roles
- **Ownership-based Access**: Users can only delete their own blogs
- **Method-level Security**: `@PreAuthorize` annotations
- **Endpoint-level Security**: Different access levels for different operations

### Security Configuration
- **Public Access**: Read blogs and user registration
- **Authenticated Access**: Create blogs, manage users
- **Authorized Access**: Delete blogs (admin or owner only)

---

## 🗄️ Database Schema

### Users Table
```sql
CREATE TABLE Users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role VARCHAR(50) NOT NULL
);
```

### Blogs Table
```sql
CREATE TABLE Blogs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    date TIMESTAMP NOT NULL,
    topic VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(id)
);
```

### Relationships
- **Users** → **Blogs**: One-to-Many (one user can have many blogs)
- **Blogs** → **Users**: Many-to-One (many blogs belong to one user)
- **Foreign Key**: `user_id` in Blogs table references Users

---

## 🚀 Key Features Implemented

### User Management
- **User Registration**: Create accounts with username, email, and role
- **User Authentication**: OAuth2 login with JWT tokens
- **Role Management**: ADMIN and USER roles with different permissions
- **Profile Management**: Update user information

### Blog Management
- **Create Blogs**: Authenticated users can create blogs (automatically assigned as author)
- **Read Blogs**: Public access to view all blogs
- **Delete Blogs**: Only admins or blog owners can delete
- **Ownership Validation**: `isOwner()` method for security checks

### Security Implementation
- **OAuth2 Authentication**: Secure login with JWT support
- **Method-level Security**: `@PreAuthorize` annotations
- **Principal-based User Identification**: Automatic user association
- **Data Relationships**: Bidirectional User-Blog relationships

---

## 📝 Usage Examples

### Creating a Blog (Authenticated User)
```bash
POST /blogs
Authorization: Bearer <JWT_TOKEN>
Content-Type: application/json

{
  "name": "My Blog Post",
  "topic": "Spring Security",
  "content": "This is about Spring Security implementation..."
}
```

### Deleting a Blog (Owner or Admin)
```bash
DELETE /blogs/1
Authorization: Bearer <JWT_TOKEN>
```

### User Registration
```bash
POST /users
Content-Type: application/json

{
  "username": "john_doe",
  "email": "john@example.com",
  "role": "USER"
}
```

---

## 🔧 Configuration

### Application Properties
Configure your database connection in `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/blogdb
spring.datasource.username=your_username
spring.datasource.password=your_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# OAuth2 Configuration
spring.security.oauth2.client.registration.google.client-id=your-client-id
spring.security.oauth2.client.registration.google.client-secret=your-client-secret
```

### OAuth2 Configuration
The application is configured for OAuth2 authentication. Configure your OAuth2 provider settings as needed.
=======
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

### Test Coverage Includes:
- **BlogControllerTest** - Controller layer testing
- **BlogRepositoryTest** - Repository layer testing
- **BlogServiceTest** - Service layer testing
- **ControllerIntegrationTest** - End-to-end testing

### Test Features:
- Unit tests for all service methods
- Integration tests for API endpoints
- Mock testing for external dependencies
- Security testing for authentication and authorization

---

## 🏗️ Architecture Patterns

### Layered Architecture
- **Controller Layer**: REST API endpoints and request handling
- **Service Layer**: Business logic and transaction management
- **Repository Layer**: Data access and persistence
- **Entity Layer**: Domain models and relationships

### Security Patterns
- **Authentication**: OAuth2 with JWT tokens
- **Authorization**: Role-based and ownership-based access control
- **Method Security**: `@PreAuthorize` for fine-grained control
- **Data Security**: Encrypted passwords and secure sessions

### Design Patterns
- **Repository Pattern**: Data access abstraction
- **Service Pattern**: Business logic encapsulation
- **AOP Pattern**: Cross-cutting concerns (logging, security)
- **Exception Handling**: Global exception management

---

## 🚀 Deployment

### Local Development
```bash
./gradlew bootRun
```

### Production Build
```bash
./gradlew build
java -jar build/libs/BlogProject-0.0.1-SNAPSHOT.jar
```

### Docker Deployment
```dockerfile
FROM openjdk:17-jdk-slim
COPY build/libs/BlogProject-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

---

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

### Development Guidelines
- Follow Spring Boot best practices
- Write comprehensive tests
- Update documentation
- Follow security guidelines

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👥 Authors

- **Varun** - *Initial work* - [Varun20002](https://github.com/Varun20002)

---

## 🙏 Acknowledgments

- Spring Boot community for excellent documentation
- OAuth2 and JWT for secure authentication
- JUnit and Mockito for comprehensive testing
- All contributors and testers

---

## 📞 Support

For support, email support@blogproject.com or create an issue in the repository.

---

## 🔮 Future Enhancements

- [ ] Comment system for blogs
- [ ] File upload for blog images
- [ ] Advanced search functionality
- [ ] Blog categories and tags
- [ ] Email notifications
- [ ] API rate limiting
- [ ] Caching implementation
- [ ] Microservices architecture
