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

---

## 🔎 Code Quality

### SonarQube

#### Requirements
- **Docker** (recommended) or an existing SonarQube server
- A **SonarQube token** (SonarQube → *My Account → Security*)
- **Java** and **Maven** (already required for this project)

#### 1) Start SonarQube locally (Docker)
```bash
docker run -d --name sonarqube -p 9000:9000 sonarqube:lts
```

Open SonarQube:
- http://localhost:9000

Default credentials:
- user: `admin`
- password: `admin`  
(You will be prompted to change the password on first login.)

### 2) Create a project and generate a token
1. Create a project in SonarQube (Manual setup).
2. Generate a token: **My Account → Security**.
3. Copy:
   - `SONAR_PROJECT_KEY`
   - `SONAR_TOKEN`

#### 3) Run analysis from the project root
```bash
mvn clean verify sonar:sonar \
  -Dsonar.projectKey=YOUR_PROJECT_KEY \
  -Dsonar.host.url=http://localhost:9000 \
  -Dsonar.token=YOUR_SONAR_TOKEN
```

#### 4) View results
Go to:
- http://localhost:9000

Open your project dashboard to see bugs, vulnerabilities, code smells, and coverage (if configured).

## 📚 Swagger / OpenAPI

### API

1) Start the application.

2) Open Swagger UI in your browser:
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`

(Optional) Open the OpenAPI spec (JSON):
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`
