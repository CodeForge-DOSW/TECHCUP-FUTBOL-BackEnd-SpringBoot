# 📄 Requerimientos del Sistema

## 1. Lista general de requerimientos

El sistema de TECHCUP FUTBOL tiene los siguientes requerimientos (descripción a alto nivel):

### 1.1 Requerimientos funcionales

El sistema de Bankify debe tener la capacidad de:

1. Gestión del Torneo

2. Registro y Autenticación de Usuarios

3. Gestión de Roles

4. Creación y Administración de Equipos

5. Gestión de Invitaciones

6. Búsqueda de Jugadores

7. Gestión de Inscripción y Pagos

8. Gestión de Alineaciones

9. Registro de Partidos

10. Consulta de Partidos

11. Tabla de Posiciones Automática

12. Generación de Llaves Eliminatorias

13. Estadísticas e Historial

14. Auditoría

### 1.2 Requerimientos NO funcionales

El sistema de TECHCUP FUTBOL debe tener:

1.
2.
3.
4.
5.

### 2.1 Requerimiento Funcional 1

| Campo | Descripción |
|-------|-------------|
| **ID** | RF-01 |
| **Nombre del requerimiento** | Gestión del Torneo |
| **Descripción** | El sistema debe permitir al organizador crear y administrar torneos de fútbol, definiendo toda la información necesaria para que funcione: fechas, equipos, costos, reglamento, canchas, horarios y sanciones. |
| **Precondiciones** | Para que el sistema cumpla con este requerimiento, debe existir al menos un usuario registrado con rol de Organizador en el sistema. |
| **Actor** | Organizador |
| **Flujo principal** | 1. El organizador inicia sesión en la plataforma.<br>2. El organizador crea un nuevo torneo con la información básica fechas, número de equipos, costo y estado.<br>3. El sistema guarda el torneo en estado Borrador.<br>4. El organizador configura los detalles del torneo .<br>5. El organizador inicia el torneo cuando todo esté listo.<br>6. El sistema cambia el estado a Activo.<br>7. Una vez termine, el organizador lo finaliza.<br>8. El sistema cambia el estado a Finalizado y conserva el historial. |
| **Diagrama de caso de uso** | <img width="1243" height="526" alt="image" src="https://github.com/user-attachments/assets/d91112b5-b837-4df6-af71-1f2e1b247796" />
 |
| **Poscondiciones** | El torneo queda registrado en el sistema con toda su configuración, visible para los demás usuarios, y con un estado que refleja en qué fase se encuentra. |

---

### 2.2 Requerimiento Funcional 2

| Campo | Descripción |
|-------|-------------|
| **ID** | RF-02 |
| **Nombre del requerimiento** | Registro y Autenticación de Usuarios |
| **Descripción** | El sistema debe permitir que cualquier persona interesada pueda crear su cuenta, iniciar sesión de forma segura y configurar su perfil deportivo con foto, número dorsal y posiciones de juego. También podrá indicar si está disponible para unirse a un equipo. |
| **Precondiciones** | Para que el sistema cumpla con este requerimiento, el usuario debe contar con un correo institucional estudiante, graduado, profesor o personal administrativo o un correo Gmail si es familiar de alguien de la Escuela. |
| **Actor** | Estudiante, Graduado, Profesor, Personal Administrativo, Familiar |
| **Flujo principal** | 1. El usuario ingresa a la plataforma y selecciona la opción de registro.<br>2. El usuario ingresa su correo y completa los datos del formulario.<br>3. El sistema valida el tipo de correo y asigna el rol correspondiente.<br>4. El usuario completa su perfil deportivo (foto, dorsal, posiciones de juego).<br>5. El sistema guarda la información y activa la cuenta.<br>6. El usuario inicia sesión con sus credenciales.<br>7. El usuario puede marcar su disponibilidad para que los capitanes lo encuentren. |
| **Diagrama de caso de uso** |  |
| **Poscondiciones** | El usuario queda registrado con su perfil deportivo activo y el rol asignado según el tipo de correo con el que se registró. |

---

### 2.3 Requerimiento Funcional 3

| Campo | Descripción |
|-------|-------------|
| **ID** | RF-03 |
| **Nombre del requerimiento** | Gestión de Roles |
| **Descripción** | El sistema debe manejar diferentes tipos de usuarios, donde cada uno tiene acceso únicamente a lo que le corresponde según su rol. No todos pueden realizar las mismas acciones dentro de la plataforma. |
| **Precondiciones** | Para que el sistema cumpla con este requerimiento, el usuario debe estar previamente registrado y autenticado en la plataforma. |
| **Actor** | Administrador, Organizador, Capitán, Árbitro, Estudiante, Graduado, Profesor, Personal Administrativo, Familiar |
| **Flujo principal** | 1. El usuario inicia sesión en la plataforma.<br>2. El sistema identifica el rol asignado a ese usuario.<br>3. El sistema muestra únicamente las opciones que le corresponden según su rol.<br>4. Si el usuario intenta acceder a una función que no le pertenece, el sistema le niega el acceso.<br>5. El administrador puede consultar, crear o modificar los roles cuando sea necesario.<br>6. Cualquier cambio de rol queda registrado en el sistema para auditoría. |
| **Diagrama de caso de uso** |  |
| **Poscondiciones** | Cada usuario accede únicamente a las funciones que le corresponden, garantizando que la información y acciones del torneo estén controladas y organizadas. ||




### 2.7 Requerimiento Funcional 7

| Campo | Descripción |
|------|-------------|
| **ID** | RF-07 |
| **Nombre del requerimiento** | Gestión de Inscripción y Pagos |
| **Descripción** | El sistema debe permitir: Subir comprobante de pago,Cambiar estado del pago (Pendiente, En revisión, Aprobado, Rechazado), Autorizar participación solo a equipos aprobados. |
| **Precondiciones** | 	El equipo debe estar creado y el capitán autenticado. |
| **Actor** | Capitán, Organizador|
| **Flujo principal** | 1. El capitán sube el comprobante. <br>2. El sistema registra el estado como Pendiente.<br>3. El organizador revisa el comprobante.<br>4. El sistema actualiza el estado a Aprobado o Rechazado. |
| **Diagrama de caso de uso** | *imagen y link*|
| **Poscondiciones** | El equipo queda habilitado o rechazado para participar. |


### 2.8 Requerimiento Funcional 8

| Campo | Descripción |
|------|-------------|
| **ID** | RF-08 |
| **Nombre del requerimiento** | Gestión de Alineaciones |
| **Descripción** | 	El sistema debe permitir seleccionar titulares, reservas, formación y consultar alineaciones rivales. |
| **Precondiciones** | 	El equipo debe estar inscrito y aprobado.|
| **Actor** | Capitán |
| **Flujo principal** | 1. El capitán accede a la alineación. <br>2. Selecciona titulares y reservas. <br>3. Define formación. <br>4. El sistema guarda la alineación.|
| **Diagrama de caso de uso** | *imagen y link*|
| **Poscondiciones** | 	La alineación queda registrada para el partido. |

## 3. Preguntas
