# TECHCUP-FUTBOL

> [!IMPORTANT]
> This repository contains the **Backend service** for the **TECHCUP-FUTBOL** project.

---

## 🧰 Technologies

| Technology | Description |
| ---------- | ----------- |
| **Java 17+** | Main programming language used to develop the backend application |
| **Spring Boot** | Framework that simplifies the development of Java applications and RESTful APIs |
| **Maven** | Dependency management and build automation tool used to build and manage the project |
| **Swagger UI** | Provides interactive API documentation and allows developers to test endpoints directly from the browser |
| **JaCoCo** | Tool used to measure test coverage within the codebase |
| **SonarQube** | Static code analysis platform used to detect bugs, vulnerabilities, and maintain code quality |
| **Swagger (OpenAPI)** | Interactive API documentation used to explore and test REST endpoints directly from the browser (Swagger UI) |

## 📁 Project structure

```
📦 TECHCUP-FUTBOL-BackEnd-SpringBoot/
├── 📂 src/
│   ├── 📂 main/
│   │   ├── 📂 java/
│   │   │   └── 📂 edu/eci/dosw/project-name/
│   │   │       ├── 📄 Application.java        # Main class with @SpringBootApplication
│   │   │       ├── 📂 config/                 # Configuration (Security, Web, etc.)
│   │   │       ├── 📂 controller/             # REST controllers (@RestController)
│   │   │       ├── 📂 service/                # Business logic (@Service)
│   │   │       ├── 📂 repository/             # Data access (@Repository / JPA)
│   │   │       ├── 📂 entity/                 # JPA entities (database layer)
│   │   │       ├── 📂 model/                  # Core domain models
│   │   │       ├── 📂 dto/                    # Data Transfer Objects
│   │   │       └── 📂 exception/              # Exception handling (@ControllerAdvice)
│   │   └── 📂 resources/
│   │       ├── 📄 application.properties     # or application.yml
│   │       └── 📂 docs/
│   │           ├── 📂 uml/
│   │           ├── 📂 images/
│   │           └── 📂 requirements/
│   └── 📂 test/
│       └── 📂 java/                          # Tests (same package structure)
├── 📄 pom.xml                                # Maven configuration
└── 📄 README.md
```


## ✅ Installation & prerequisites

### Requirements
- **JDK** (recommended: 17+)
- **Maven** (3.8+ recommended)
- **Database** (if applicable) or **Docker** to run it

Verify:
```bash
java -version
mvn -version
```

### Install & run locally
1) Clone the repository:
```bash
git clone https://github.com/CodeForge-DOSW/TECHCUP-FUTBOL-BackEnd-SpringBoot.git
cd TECHCUP-FUTBOL-BackEnd-SpringBoot
```

2) Build:
```bash
mvn clean install
```

3) Run:
```bash
mvn spring-boot:run
```

Default URL:
- [http://localhost:8080](http://localhost:8080)

---

## 🧪 Testing
Run tests:
```bash
mvn test
```

----

## 🧪 JaCoCo (Test Coverage)

### Requirements
- **JDK** installed (recommended: 17+)
- **Maven** installed *(or Maven Wrapper `./mvnw` if available)*
- The project must include the **JaCoCo Maven plugin** in `pom.xml` *(if not, add it first)*

### Step-by-step
1) Run tests and generate the coverage report:
```bash
mvn clean test jacoco:report
```

2) Open the HTML report in your browser:
- `target/site/jacoco/index.html`

3) (Optional) Generate report during `verify`:
```bash
mvn clean verify
```

---

## 🔎 Code Quality

### SonarQube

#### Requirements
- **Docker** installed (recommended) *or* access to an existing SonarQube server
- The backend dependencies must compile (Java + Maven installed)
- A **SonarQube token** (SonarQube → *My Account → Security*)
- `SONAR_PROJECT_KEY` created in SonarQube
- SonarQube URL (local default): `http://localhost:9000`

#### Step-by-step
1) Start SonarQube locally (Docker):
```bash
docker run -d --name sonarqube -p 9000:9000 sonarqube:lts
```

2) Open SonarQube in your browser:
- `http://localhost:9000`

Default credentials:
- user: `admin`
- password: `admin`
(You will be prompted to change the password on first login.)

3) Create a project in SonarQube (Manual setup) and copy:
- `SONAR_PROJECT_KEY`

4) Generate a token:
- SonarQube → **My Account → Security** → Generate Token
- Copy: `SONAR_TOKEN`

5) Run Sonar analysis from the repository root:
```bash
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=YOUR_PROJECT_KEY \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=YOUR_SONAR_TOKEN
```

6) View results:
- Go to `http://localhost:9000`
- Open your project dashboard to see issues, security hotspots, and code smells.

---

## 📚 API 

### Endpoints (summary)

| Resource | Method | Endpoint | Description |
|---------|--------|----------|-------------|
| Auth    | POST   | `/api/auth/login` | Sign in |
| Users   | GET    | `/api/users` | List users |
| Teams   | GET    | `/api/teams` | List teams |

### Swagger / OpenAPI (Documentation)

#### Requirements
- The backend must be **running** (Spring Boot started successfully).
- You must know the **port** where it is running (default: `8080`).

#### Step-by-step
1) Start the application:
```bash
mvn spring-boot:run
