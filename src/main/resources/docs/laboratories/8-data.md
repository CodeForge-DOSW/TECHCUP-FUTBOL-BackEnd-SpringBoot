# LABORATORIO 9 – DATA

### Entidades JPA seleccionadas

Se implementaron 3 entidades principales para cumplir los requisitos del laboratorio: autenticación, gestión de usuarios, torneos y relaciones.

---

#### UserEntity (`user`)
Gestiona la autenticación y el CRUD de usuarios.

- `user_id` (PK)
- `email` (único)
- `user_type` (roles)
- `status` (activo/inactivo)

---

#### TournamentEntity (`tournament`)
Permite el CRUD de torneos y su ciclo de vida.

- `tournament_id` (PK)
- `name`
- `start_date`, `end_date`
- `status` (draft → finished)
- `number_of_teams`, `team_cost`

---

#### TeamEntity (`team`)
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

### Relaciones

- **Tournament → Team (1:N):** Un torneo tiene muchos equipos; cada equipo pertenece a un solo torneo.
- **Team → Tournament (N:1):** Muchos equipos se asocian a un mismo torneo (FK `tournament_id`).
- **Team → User (N:1):** Un equipo tiene un capitán (usuario); si se elimina, queda `NULL`.

---

### Funcionalidades cubiertas

- Autenticación → UserEntity
- Usuarios CRUD → UserEntity
- Torneos CRUD → TournamentEntity
- Relaciones entre entidades → TeamEntity

## Procedimientos

Basado en los temas del laboratorio

---

## CÓMO CONFIGURAR PERSISTENCIA (JPA)

### Dependencias necesarias

* JPA
* Driver de BD (PostgreSQL)
* H2 (tests)

---

### Configuración básica (application.properties)

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/db
spring.datasource.username=usuario
spring.datasource.password=clave

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

### Clave del parcial

* `ddl-auto=update` → crea tablas automáticamente
* Si no conecta → revisa URL, user, password

---

## CÓMO CREAR ENTIDADES JPA

### Estructura mínima

```java
@Entity
@Table(name = "tabla")
public class Entidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
```

---

### Campos

```java
@Column(nullable = false)
private String nombre;
```

---

### Validaciones

```java
@NotBlank
private String nombre;
```

---

### Checklist mental

```text
@Entity ✔
@Table ✔
@Id ✔
@GeneratedValue ✔
@Column ✔
Constructor vacío ✔
Getters/Setters ✔
```

---

## 3. CÓMO HACER RELACIONES

### Tipos

```java
@ManyToOne
@OneToMany
@OneToOne
@ManyToMany
```

---

### Ejemplo típico

```java
@ManyToOne
@JoinColumn(name = "categoria_id")
private Categoria categoria;
```

---

### Clave del parcial

* Siempre definir FK con `@JoinColumn`
* Pensar cardinalidad antes de escribir código

---

## CÓMO CREAR REPOSITORIOS (SPRING DATA)

### Estructura

```java
public interface MiRepository extends JpaRepository<Entidad, Long> {
}
```

---

### Query derivada

```java
List<Entidad> findByNombreContainingIgnoreCase(String nombre);
```

---

### Qué puedes hacer sin código extra

* save()
* findAll()
* findById()
* deleteById()

---

## CÓMO USAR SERVICE CON JPA

### Regla clave

```text
Controller → Service → Repository
```

---

### Flujo real

1. Recibes DTO
2. Mapper → Entity
3. Guardas en repo
4. Mapper → Model
5. Retornas

---

### En parcial

* NO usar repo directo en controller
* TODO pasa por service

---

## CÓMO USAR MAPPERS (MAPSTRUCT)

### Interfaz

```java
@Mapper(componentModel = "spring")
public interface Mapper {
    Model toModel(Entity entity);
    Entity toEntity(Model model);
}
```

---

### Para qué sirve

* Separar Entity y Model
* Evitar exponer BD

---

## CÓMO CONECTAR CONTROLADORES A BD

### Antes

```text
List en memoria ❌
```

### Después

```text
Controller → Service → DB ✔
```

---

### En parcial

* Si usas listas → MAL
* Debe persistir en BD

---

## CÓMO CONFIGURAR H2 (TESTING)

### Archivo

```text
src/test/resources/application-test.properties
```

---

### Configuración

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
```

---

### Clave

* Tests NO usan PostgreSQL
* Usan BD en memoria

---

## CÓMO HACER PRUEBAS DE REPOSITORIO

### Anotaciones

```java
@DataJpaTest
@ActiveProfiles("test")
```

---

### Qué probar

```text
✔ Guardar
✔ Consultar
✔ Relaciones
✔ Actualizar / eliminar
```

---

### Ejemplo mental

* guardar → id no null
* buscar → devuelve datos
* query → filtra correctamente

---

## CÓMO SABER SI TODO FUNCIONA

✔ Tablas creadas automáticamente
✔ Datos persisten
✔ Tests pasan con H2
✔ No errores de conexión

---

## CÓMO CREAR MICROSERVICIO (SPRING BOOT)

## 📌 Idea

```text
Proyecto independiente
```

---

## 📦 Estructura

```text
controller
service
repository
model
config
```

---

### Clave

* Microservicio ≠ mismo proyecto
* Es otro backend

---

## CÓMO USAR MONGODB

### Configuración

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/db
```

---

### Documento

```java
@Document(collection = "imagenes")
public class Imagen {
    @Id
    private String id;
}
```

---

### Diferencia con JPA

```text
JPA → tablas
Mongo → documentos
```

---

## 13. CÓMO MANEJAR ARCHIVOS (IMÁGENES)

### Recibir archivo

```java
@RequestParam("archivo") MultipartFile archivo
```

---

### Guardar

```java
archivo.getBytes()
```

---

### Retornar imagen

```java
ResponseEntity<byte[]>
```

---

### En parcial

* Guardas bytes
* Retornas con content-type

---

## 14. ENDPOINTS EN MICROSERVICIO

### CRUD típico

```text
POST → subir imagen
GET → listar
GET/id → obtener
DELETE → eliminar
```

---

### Extra

```text
GET por referencia externa
```

---

## RESUMEN ULTRA RÁPIDO

* JPA = persistencia relacional
* Entity = tabla
* Repository = acceso BD
* Service = lógica
* H2 = tests
* PostgreSQL = producción
* MongoDB = documentos
* MultipartFile = archivos
* Microservicio = proyecto aparte

