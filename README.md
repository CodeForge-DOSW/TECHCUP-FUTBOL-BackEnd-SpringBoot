# TECHCUP FUTBOL

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

# Laboratorio-9

## Parte 1

1. Postman

![img.png](src/main/resources/docs/images/lab10/img.png)

## Parte 2

4. Request de Users en Postman solicitando usuario y contraseña
![img_1.png](src/main/resources/docs/images/lab10/img_1.png)

7. Resultado de la solicitud de autenticación con el token JWT
![img_2.png](src/main/resources/docs/images/lab10/img_2.png)

12. Resultado de la solicitud de autenticación con el token JWT configurado usuario y contraseña
![img_3.png](src/main/resources/docs/images/lab10/img_3.png)

# Laboratorio JWT Filter

## a. ¿Qué es un filtro JWT?

Un filtro JWT es un componente del pipeline de seguridad (por ejemplo, en Spring Security) que intercepta cada solicitud HTTP para leer y procesar un token JWT enviado por el cliente.

## b. ¿Para qué sirven los filtros JWT?

Sirven para validar la autenticidad e integridad del token, extraer la identidad del usuario y registrar su autenticación en el contexto de seguridad. Gracias a esto, los endpoints protegidos pueden autorizar o rechazar solicitudes sin manejar sesiones tradicionales en servidor.

## c. Bibliografía en formato APA

- Jones, M., Bradley, J., & Sakimura, N. (2015). *JSON Web Token (JWT)* (RFC 7519). Internet Engineering Task Force. https://doi.org/10.17487/RFC7519
- Spring. (2024). *Spring Security Reference Documentation*. https://docs.spring.io/spring-security/reference/
- Walls, C. (2022). *Spring in Action* (6th ed.). Manning Publications.
- Oracle. (2024). *Java Platform, Standard Edition Documentation*. https://docs.oracle.com/en/java/
