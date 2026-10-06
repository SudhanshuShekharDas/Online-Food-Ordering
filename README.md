# Online Food Ordering System

A beginner-friendly Java Spring Boot web application for browsing food, filtering by category, managing a cart, logging in, and placing orders.

## Tech Stack
- Java 17+
- Spring Boot 3.4 / Spring Data JPA
- H2 embedded database (default) or MySQL (optional)
- HTML, CSS, JavaScript
- REST APIs

## Main Features
- Food browsing and search
- Category filtering
- Cart management
- Basic customer registration/login
- REST APIs for food and orders
- Order total calculation and order-item storage
- Sample menu and a demo user are created automatically on first run

## Quick Start (no database setup needed)

**Only requirement: Java 17 or newer.** Maven is not required; the included Maven Wrapper downloads it automatically (internet needed on the first run).

Linux / macOS:
```bash
./run-linux.sh
```

Windows: double-click `run-windows.bat`

Or manually from the project folder:
```bash
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

Then open **http://localhost:8081**.

Demo login: `user@example.com` / `123456`

The data is stored in a local `data/` folder (H2 database). Delete that folder to reset everything to the sample data. You can inspect the database at `http://localhost:8081/h2-console` (JDBC URL `jdbc:h2:file:./data/food_ordering;AUTO_SERVER=TRUE`, user `sa`, empty password).

## Optional: Run with MySQL
MySQL is no longer required. If you still want to use it:

1. Make sure MySQL is running (the database is created automatically; `database/schema.sql` is optional reference/manual setup).
2. Start with the `mysql` profile, passing your credentials as environment variables:

```bash
DB_USERNAME=root DB_PASSWORD=your_password ./mvnw spring-boot:run -Dspring-boot.run.profiles=mysql
```

On Windows (PowerShell):
```powershell
$env:DB_PASSWORD="your_password"; .\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=mysql"
```

Defaults: `DB_URL=jdbc:mysql://localhost:3306/food_ordering?createDatabaseIfNotExist=true`, `DB_USERNAME=root`, empty password. Settings live in `src/main/resources/application-mysql.properties`, so no passwords need to be written into project files.

## API Overview
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/foods` | List all food items |
| GET | `/api/foods/{id}` | Get one food item |
| POST | `/api/foods` | Add a food item |
| POST | `/api/auth/register` | Register a customer |
| POST | `/api/auth/login` | Log in |
| GET/POST | `/api/orders` | List / place orders |
| GET | `/api/orders/{id}` and `/api/orders/{id}/items` | Order details and its items |

Login example (`POST /api/auth/login`):

```json
{
  "email": "user@example.com",
  "password": "123456"
}
```

Place order example (`POST /api/orders`):

```json
{
  "customerName": "Demo User",
  "customerEmail": "user@example.com",
  "items": [{ "foodId": 1, "quantity": 2 }]
}
```

Invalid requests (empty cart, unknown food, bad quantity) return a JSON `{"message": "..."}` with a 4xx status.

## Troubleshooting
- **`Java 17+ is not installed`**: install a JDK 17 or newer and make sure `java -version` works.
- **Port 8081 already in use**: change `server.port` in `src/main/resources/application.properties`.
- **Dependency download fails**: check your internet connection/proxy; the first run needs it.

## Important
The authentication in this student project is intentionally basic (plain-text passwords). For a production application, hash passwords and use Spring Security/BCrypt or another secure mechanism. Never commit real database passwords, AWS keys, or other secrets to GitHub.
