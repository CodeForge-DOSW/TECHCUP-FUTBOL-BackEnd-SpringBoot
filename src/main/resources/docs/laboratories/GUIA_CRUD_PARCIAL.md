# Parcial Práctico DOSW — Guía CRUD Completa

Guía paso a paso para conectar un CRUD completo: **Controller → Service → Repository → PostgreSQL**. Con ejemplo y simulacro de 3 horas.

---

## 1. Estructura del proyecto

Esta es la estructura que pide el parcial. Cada paquete tiene un rol específico:

```
src/main/java/edu/eci/dosw/parcial/
├── Application.java          # @SpringBootApplication
├── config/                    # Configuración (Web, CORS, etc.)
├── controller/                # @RestController — endpoints HTTP
├── service/                   # @Service — lógica de negocio
├── repository/                # @Repository — acceso a datos (JPA)
├── entity/                    # @Entity — tablas en BD
├── mapper/                    # MapStruct — Entity ↔ Model
├── model/                     # Objetos de negocio (los que viajan)
├── dto/                       # (Opcional) objetos de transferencia
├── exception/                 # Excepciones personalizadas
└── security/                  # (NO aplica en este parcial)

src/main/resources/
├── application.properties     # Config BD
└── docs/
    ├── uml/                   # Diagramas
    ├── images/
    └── requirements/

src/test/java/                 # Tests (misma estructura de paquetes)
```

> **⚠️ Regla de oro del flujo:** El Controller **NUNCA** toca el Repository directamente. El flujo siempre es:

```
HTTP Request → Controller → Service → Repository → PostgreSQL
```

---

## 2. Dependencias Maven (pom.xml)

Copia esto en tu `pom.xml`. Son las dependencias mínimas que necesitas:

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.4.4</version>
</parent>

<properties>
    <java.version>17</java.version>
    <mapstruct.version>1.6.3</mapstruct.version>
</properties>

<dependencies>
    <!-- Web (incluye Tomcat embebido) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- JPA (Hibernate + Spring Data) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- PostgreSQL Driver -->
    <dependency>
        <groupId>org.postgresql</groupId>
        <artifactId>postgresql</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Validaciones (@NotBlank, @NotNull, etc.) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- MapStruct -->
    <dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>${mapstruct.version}</version>
    </dependency>

    <!-- H2 para tests -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>test</scope>
    </dependency>

    <!-- Testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.13.0</version>
            <configuration>
                <annotationProcessorPaths>
                    <path>
                        <groupId>org.mapstruct</groupId>
                        <artifactId>mapstruct-processor</artifactId>
                        <version>${mapstruct.version}</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
    </plugins>
</build>
```

---

## 3. application.properties

```properties
# PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/parcial_db
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# Puerto
server.port=8080
```

> **💡 Crear BD con Docker (recordatorio del Lab 8):**
> ```bash
> docker run --name postgres-parcial \
>   -e POSTGRES_DB=parcial_db \
>   -e POSTGRES_USER=postgres \
>   -e POSTGRES_PASSWORD=postgres \
>   -p 5432:5432 \
>   -d postgres
> ```

---

## 4. Entity (la tabla en BD)

La Entity mapea directamente a una tabla en PostgreSQL. Sigue este patrón:

```java
package edu.eci.dosw.parcial.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class ProductoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 250)
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    // Constructor vacío OBLIGATORIO para JPA
    public ProductoEntity() {}

    // Constructor con parámetros (opcional pero útil)
    public ProductoEntity(String nombre, String descripcion, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    // Getters y Setters (TODOS, incluyendo id)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String d) { this.descripcion = d; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
}
```

> **⚠️ Checklist Entity:**
> - `@Entity` + `@Table` en la clase
> - `@Id` + `@GeneratedValue(strategy = GenerationType.IDENTITY)` en el id
> - `@Column` con constraints (nullable, unique, length)
> - Constructor vacío SIEMPRE (JPA lo necesita)
> - Getters y setters de TODOS los campos

---

## 5. Repository

```java
package edu.eci.dosw.parcial.repository;

import edu.eci.dosw.parcial.entity.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository
        extends JpaRepository<ProductoEntity, Long> {

    // Spring genera la query automáticamente por el nombre del método:
    List<ProductoEntity> findByNombreContainingIgnoreCase(String nombre);
}
```

**Con eso ya tienes GRATIS:** `save()`, `findById()`, `findAll()`, `deleteById()`, `existsById()`.

---

## 6. Model (objeto de negocio)

El Model es lo que viaja entre Controller y Service. Es igual a la Entity pero SIN anotaciones JPA:

```java
package edu.eci.dosw.parcial.model;

public class Producto {
    private Long id;
    private String nombre;
    private String descripcion;
    private Double precio;

    public Producto() {}

    // Getters y Setters (todos)
    // ... mismos que la entity
}
```

---

## 7. Mapper (MapStruct)

```java
package edu.eci.dosw.parcial.mapper;

import edu.eci.dosw.parcial.entity.ProductoEntity;
import edu.eci.dosw.parcial.model.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    Producto toModel(ProductoEntity entity);
    ProductoEntity toEntity(Producto model);
}
```

> **¿Por qué MapStruct?** El Service trabaja con **Model** (sin JPA). El Repository trabaja con **Entity** (con JPA). El Mapper convierte entre ambos. Así las capas están desacopladas.

---

## 8. Service (lógica de negocio)

```java
package edu.eci.dosw.parcial.service;

import edu.eci.dosw.parcial.entity.ProductoEntity;
import edu.eci.dosw.parcial.mapper.ProductoMapper;
import edu.eci.dosw.parcial.model.Producto;
import edu.eci.dosw.parcial.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository repository;
    private final ProductoMapper mapper;

    // Inyección por constructor (NO necesita @Autowired si hay 1 constructor)
    public ProductoService(ProductoRepository repository, ProductoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    // CREATE
    public Producto crear(Producto producto) {
        ProductoEntity entity = mapper.toEntity(producto);
        ProductoEntity saved = repository.save(entity);
        return mapper.toModel(saved);
    }

    // READ ALL
    public List<Producto> obtenerTodos() {
        return repository.findAll()
            .stream()
            .map(mapper::toModel)
            .collect(Collectors.toList());
    }

    // READ BY ID
    public Producto obtenerPorId(Long id) {
        ProductoEntity entity = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return mapper.toModel(entity);
    }

    // UPDATE
    public Producto actualizar(Long id, Producto producto) {
        ProductoEntity existing = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        existing.setNombre(producto.getNombre());
        existing.setDescripcion(producto.getDescripcion());
        existing.setPrecio(producto.getPrecio());
        return mapper.toModel(repository.save(existing));
    }

    // DELETE
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}
```

> **🔴 Error típico en parcial:** En **UPDATE**: primero buscas la entity existente con `findById`, luego actualizas sus campos con los setters, y después haces `save()`. NO crees una entity nueva con el id — JPA necesita la entity manejada.

---

## 9. Controller (endpoints REST)

```java
package edu.eci.dosw.parcial.controller;

import edu.eci.dosw.parcial.model.Producto;
import edu.eci.dosw.parcial.service.ProductoService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    // POST /api/productos
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto producto) {
        Producto creado = service.crear(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    // GET /api/productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    // GET /api/productos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    // PUT /api/productos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizar(@PathVariable Long id,
                                               @RequestBody Producto producto) {
        return ResponseEntity.ok(service.actualizar(id, producto));
    }

    // DELETE /api/productos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
```

### Tabla resumen de endpoints

| Verbo  | URL                  | Body               | Response Code |
|--------|----------------------|---------------------|---------------|
| POST   | /api/productos       | JSON del producto   | 201 Created   |
| GET    | /api/productos       | —                   | 200 OK        |
| GET    | /api/productos/{id}  | —                   | 200 OK        |
| PUT    | /api/productos/{id}  | JSON actualizado    | 200 OK        |
| DELETE | /api/productos/{id}  | —                   | 204 No Content|

---

## 10. Exception Handler (opcional pero suma puntos)

```java
package edu.eci.dosw.parcial.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
```

---

## 11. Tests con H2

### src/test/resources/application-test.properties

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
```

### Test del Repository

```java
@DataJpaTest
@ActiveProfiles("test")
class ProductoRepositoryTest {
    @Autowired
    private ProductoRepository repo;

    @Test
    void deberiaGuardarYBuscar() {
        ProductoEntity p = new ProductoEntity("Laptop", "16GB RAM", 3500000.0);
        ProductoEntity saved = repo.save(p);
        assertNotNull(saved.getId());
        assertEquals("Laptop", saved.getNombre());
    }
}
```

---

## 12. Probar con Postman

| Paso | Request                    | Body / Esperado                                                                  |
|------|----------------------------|----------------------------------------------------------------------------------|
| 1    | `POST /api/productos`      | `{"nombre":"Laptop","descripcion":"16GB","precio":3500000}` → 201                |
| 2    | `GET /api/productos`       | → 200 + array con el producto creado                                             |
| 3    | `GET /api/productos/1`     | → 200 + el producto con id 1                                                    |
| 4    | `PUT /api/productos/1`     | `{"nombre":"Laptop Pro","descripcion":"32GB","precio":5000000}` → 200            |
| 5    | `DELETE /api/productos/1`  | → 204 No Content                                                                 |
| 6    | `GET /api/productos/1`     | → 404 (ya fue eliminado)                                                         |

---

## ★ Simulacro de Parcial Práctico

> **Duración: 3 horas**
> Puedes tener disponible el repositorio de tu proyecto como referencia.

### Enunciado: Sistema de Gestión de Biblioteca

Se requiere construir el backend de un sistema de gestión de una biblioteca universitaria usando Spring Boot, JPA y PostgreSQL. El sistema debe gestionar **Libros** y **Autores**.

### Entidades del dominio

**Autor**
- `id` (Long, autoincremental)
- `nombre` (String, obligatorio, máx 100 caracteres)
- `nacionalidad` (String, máx 50 caracteres)

**Libro**
- `id` (Long, autoincremental)
- `titulo` (String, obligatorio, máx 200 caracteres)
- `isbn` (String, obligatorio, único, máx 20 caracteres)
- `anioPublicacion` (Integer)
- `autor` (relación ManyToOne con Autor — un autor puede tener muchos libros)

### Requerimientos funcionales

1. **CRUD completo de Autor:** crear, listar todos, buscar por id, actualizar y eliminar.
2. **CRUD completo de Libro:** crear (recibiendo el id del autor), listar todos (incluyendo datos del autor), buscar por id, actualizar y eliminar.
3. **Reglas de negocio:**
   - No se puede crear un libro sin un autor válido existente.
   - No se puede eliminar un autor que tenga libros asociados.
   - El ISBN debe ser único — no se permite duplicado.

### Entregables (en 3 horas)

1. **Diagrama ER** en `docs/uml/`
2. **Entities** con relación `@ManyToOne`
3. **Repositories** con al menos 1 query derivada cada uno
4. **Models** + **Mappers** (MapStruct)
5. **Services** con toda la lógica de negocio
6. **Controllers** con endpoints REST y códigos HTTP correctos
7. **Al menos 2 pruebas** de repositorio con H2
8. **Captura de Postman** mostrando al menos 3 requests exitosos en el README

### Endpoints esperados

| Verbo  | URL                | Descripción                      |
|--------|--------------------|----------------------------------|
| POST   | /api/autores       | Crear autor                      |
| GET    | /api/autores       | Listar todos                     |
| GET    | /api/autores/{id}  | Buscar por id                    |
| PUT    | /api/autores/{id}  | Actualizar                       |
| DELETE | /api/autores/{id}  | Eliminar (validar sin libros)    |
| POST   | /api/libros        | Crear libro (con autorId)        |
| GET    | /api/libros        | Listar todos (con datos del autor)|
| GET    | /api/libros/{id}   | Buscar por id                    |
| PUT    | /api/libros/{id}   | Actualizar                       |
| DELETE | /api/libros/{id}   | Eliminar                         |

---

## ✓ Checklist de entrega

Antes de entregar, verifica:

1. ✅ `mvn clean compile` — compila sin errores
2. ✅ `mvn test` — tests pasan con H2
3. ✅ La app arranca y se conecta a PostgreSQL
4. ✅ POST crea registros en la BD
5. ✅ GET retorna los datos correctos
6. ✅ PUT actualiza sin crear duplicados
7. ✅ DELETE elimina y retorna 204
8. ✅ Las reglas de negocio se cumplen (errores retornan 400/404)
9. ✅ Captura de Postman en el README
10. ✅ Diagrama ER en docs/uml/
11. ✅ Código en rama develop, PR a main

### Distribución sugerida del tiempo (3 horas)

| Tiempo        | Tarea                                          |
|---------------|------------------------------------------------|
| 0:00 – 0:15   | Diagrama ER + crear BD PostgreSQL              |
| 0:15 – 0:45   | Entities + Repositories + Models + Mappers     |
| 0:45 – 1:30   | Services con lógica de negocio completa        |
| 1:30 – 2:15   | Controllers + probar con Postman               |
| 2:15 – 2:45   | Tests con H2 + capturas de Postman en README   |
| 2:45 – 3:00   | Revisar, commit final, PR a develop            |

Revisar los archivos markdown para mas informacion de cada laboratorio 
