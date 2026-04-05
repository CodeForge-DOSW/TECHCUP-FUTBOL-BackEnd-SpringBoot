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
# TECHCUP-FUTBOL-Back

# Respuestas sobre la estructura en Spring Boot

## 1. ¿Para qué sirve el paquete Controller en la estructura Spring Boot?

El paquete Controller se encarga de manejar las solicitudes HTTP que llegan a la aplicación (GET, POST, PUT, DELETE). Actúa como intermediario entre el cliente (por ejemplo, un navegador o una API externa) y la lógica de negocio. Recibe las peticiones, procesa los datos de entrada y devuelve una respuesta.

## 2. ¿Para qué sirve el paquete Service en la estructura Spring Boot?

El paquete Service contiene la lógica de negocio de la aplicación. Aquí se implementan las reglas y procesos que definen cómo se manejan los datos. Los servicios actúan como intermediarios entre los controladores y los repositorios.

## 3. ¿Para qué sirve el paquete Repository en la estructura Spring Boot?

El paquete Repository se encarga del acceso a la base de datos. Permite realizar operaciones CRUD (crear, leer, actualizar, eliminar) sobre las entidades, generalmente utilizando Spring Data JPA.


## 4. ¿Para qué sirve el paquete Entity en la estructura Spring Boot?

El paquete Entity contiene las clases que representan las tablas de la base de datos. Cada entidad define los atributos y relaciones que se almacenan en la base de datos mediante anotaciones como `@Entity`.

## 5. ¿Para qué sirve el paquete DTO en la estructura Spring Boot?

El paquete DTO (Data Transfer Object) se utiliza para transferir datos entre diferentes capas de la aplicación. Permite controlar qué información se expone al cliente, evitando enviar directamente las entidades.

## 6. ¿Para qué sirve el paquete Exception en la estructura Spring Boot?

El paquete Exception contiene clases personalizadas para el manejo de errores. Permite centralizar la gestión de excepciones y definir respuestas claras cuando ocurre algún problema en la aplicación.

---

CAPTURA DE PANTALLA SWAGGER 

<img width="1919" height="988" alt="image" src="https://github.com/user-attachments/assets/8fc7d7ab-9bff-4ed7-9a6c-34b5a4ce4060" />


# Bibliografía (Normas APA)

* Walls, C. (2022). *Spring in Action* (6th ed.). Manning Publications.
* Richardson, C. (2018). *Microservices Patterns*. Manning Publications.
* Oracle. (2023). *Java Documentation*. https://docs.oracle.com/
* Spring. (2024). *Spring Boot Reference Documentation*. https://spring.io/projects/spring-boot

# Entidades JPA seleccionadas

Se implementaron 3 entidades principales para cumplir los requisitos del laboratorio: autenticación, gestión de usuarios, torneos y relaciones.

---

### UserEntity (`user`)
Gestiona la autenticación y el CRUD de usuarios.

- `user_id` (PK)
- `email` (único)
- `user_type` (roles)
- `status` (activo/inactivo)

---

### TournamentEntity (`tournament`)
Permite el CRUD de torneos y su ciclo de vida.

- `tournament_id` (PK)
- `name`
- `start_date`, `end_date`
- `status` (draft → finished)
- `number_of_teams`, `team_cost`

---

### TeamEntity (`team`)
Representa equipos y define relaciones entre entidades.

- `team_id` (PK)
- `tournament_id` (FK)
- `captain_id` (FK)
- `status`
- `date_inscription`

**Relaciones:**
- `ManyToOne` → Tournament
- `ManyToOne` → User (capitán)

---

## Relaciones

- **Tournament → Team (1:N):** Un torneo tiene muchos equipos; cada equipo pertenece a un solo torneo.
- **Team → Tournament (N:1):** Muchos equipos se asocian a un mismo torneo (FK `tournament_id`).
- **Team → User (N:1):** Un equipo tiene un capitán (usuario); si se elimina, queda `NULL`.

---

## Funcionalidades cubiertas

- Autenticación → UserEntity  
- Usuarios CRUD → UserEntity  
- Torneos CRUD → TournamentEntity  
- Relaciones entre entidades → TeamEntity  

# Laboratorio-9

## Parte-1

### 1. Postman

![img.png](img.png)



