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