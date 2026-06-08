# Student Course Management System (SCMS)
### Spring Boot + PostgreSQL + Vanilla JS

A fresher-friendly Java Full Stack project — simple to run, easy to explain in interviews.

---

## How to Run

**Step 1 — Create the database**
```sql
CREATE DATABASE scms_db;
```

**Step 2 — Update credentials**
Open `src/main/resources/application.properties` and set:
```
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**Step 3 — Run the backend**
```bash
mvn spring-boot:run
```

**Step 4 — Open the app**
Go to: **http://localhost:8080**

> The HTML frontend is inside `src/main/resources/static/` — Spring Boot serves it automatically.
> No separate frontend server, no npm, no CORS issues.

---

## Project Structure
```
scms/
├── pom.xml
├── src/main/
│   ├── java/com/example/scms/
│   │   ├── ScmsApplication.java          ← Entry point
│   │   ├── entity/
│   │   │   ├── User.java
│   │   │   ├── Student.java
│   │   │   ├── Course.java
│   │   │   └── Enrollment.java
│   │   ├── repository/
│   │   │   ├── UserRepository.java
│   │   │   ├── StudentRepository.java
│   │   │   ├── CourseRepository.java
│   │   │   └── EnrollmentRepository.java
│   │   ├── service/
│   │   │   ├── AuthService.java
│   │   │   ├── StudentService.java
│   │   │   ├── CourseService.java
│   │   │   └── EnrollmentService.java
│   │   └── controller/
│   │       ├── AuthController.java
│   │       ├── StudentController.java
│   │       ├── CourseController.java
│   │       └── EnrollmentController.java
│   └── resources/
│       ├── application.properties
│       └── static/index.html             ← Full frontend
```

---

## API Endpoints

| Method | URL | Description |
|--------|-----|-------------|
| POST | /api/auth/register | Register a new user |
| POST | /api/auth/login | Login with username + password |
| GET | /api/students | Get all students |
| POST | /api/students | Add a student |
| PUT | /api/students/{id} | Update a student |
| DELETE | /api/students/{id} | Delete a student |
| GET | /api/courses | Get all courses |
| POST | /api/courses | Add a course |
| DELETE | /api/courses/{id} | Delete a course |
| GET | /api/enrollments | All enrollments |
| POST | /api/enrollments/student/{sId}/course/{cId} | Enroll a student |
| DELETE | /api/enrollments/{id} | Cancel enrollment |

---

## Common Interview Questions & Answers

**Q: What is Spring Boot?**
Spring Boot is a framework that simplifies building Java applications. It auto-configures things like database connections, web servers (Tomcat), and security so you don't write boilerplate.

**Q: What is JPA / Hibernate?**
JPA is a Java standard for mapping Java classes to database tables. Hibernate is the most popular implementation. With `@Entity`, a Java class becomes a DB table; with `@Id`, a field becomes the primary key.

**Q: What does JpaRepository give you for free?**
`save()`, `findById()`, `findAll()`, `deleteById()`, and more — no SQL needed. You can also add custom queries just by naming the method (e.g. `findByEmail`).

**Q: What is the difference between @RestController and @Controller?**
`@RestController` = `@Controller` + `@ResponseBody`. It means every method returns JSON data directly, instead of returning a view name.

**Q: What is @CrossOrigin?**
It allows the browser to make API calls from a different origin (port/domain). Without it, browsers block cross-origin requests by default.

**Q: How does BCrypt work?**
BCrypt hashes the password one-way — you can never reverse it. To check a login, you hash the entered password and compare it to the stored hash using `passwordEncoder.matches()`.

**Q: Why no Spring Security here?**
Spring Security adds complexity (filter chains, UserDetailsService, etc.) that isn't needed for a simple login. Here, login is handled directly in `AuthService` using BCrypt — simple and easy to explain.

**Q: What is @Valid?**
It triggers validation annotations like `@NotBlank`, `@Email`, and `@Size` on a method parameter before the method runs. If validation fails, Spring returns a 400 Bad Request automatically.

**Q: What is @ManyToOne?**
It creates a many-to-one relationship. For example: many Enrollments can belong to one Student. `@JoinColumn` creates the foreign key column in the database.
