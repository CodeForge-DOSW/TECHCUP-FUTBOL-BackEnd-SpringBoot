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
| **Poscondiciones** | El torneo queda registrado en el sistema con toda su configuración, visible para los demás usuarios, y con un estado que refleja en qué fase se encuentra. |

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
| **Diagrama de caso de uso** | <img width="434" height="302" alt="image" src="https://github.com/user-attachments/assets/b1b91836-e14b-4462-bf44-d6ce582931af" />
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
| **Diagrama de caso de uso** | <img width="503" height="169" alt="image" src="https://github.com/user-attachments/assets/6db42b8f-c585-4ea9-bee6-aecc7e5d34a6" />
| **Poscondiciones** | 	La alineación queda registrada para el partido. |

### 2.9 Requerimiento Funcional 9

| Campo | Descripción                                                                                                                                                                                                                               |
|------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **ID** | RF-09                                                                                                                                                                                                                                     |
| **Nombre del requerimiento** | Registro de Partidos                                                                                                                                                                                                                      |
| **Descripción** | El sistema debe permitir registrar el resultado de un partido, incluyendo marcador final, goleadores y tarjetas (amarillas y rojas)                                                                                                       |
| **Precondiciones** | Para que el sistema cumpla con este requerimiento, TECHCUP-FUTBOL debe tener previamente programado el partido                                                                                                                            |
| **Actor** | Organizador                                                                                                                                                                                                                               |
| **Flujo principal** | 1. El organizador accede al módulo de partidos.<br>2. Selecciona el partido correspondiente.<br>3. Ingresa el marcador final.<br>4. Registra estadísticas (goleadores y tarjetas).<br>5. El sistema actualiza la información del partido. |
| **Diagrama de caso de uso** | ![Diagrama Registrar Partido](docs/uml/casosDeUso/rf09RegistrarPartido.png)                                                                                                                                                               |
| **Poscondiciones** | Se permite a todos los actores la visualizacion correctamenta de la información del partido                                                                                                                                               |

### 2.10 Requerimiento Funcional 10

| Campo | Descripción |
|------|-------------|
| **ID** | RF-10 |
| **Nombre del requerimiento** | Consulta de Partidos |
| **Descripción** | El sistema debe permitir al árbitro consultar la información de los partidos asignados, incluyendo fecha, hora, cancha y equipos participantes |
| **Precondiciones** | Para que el sistema cumpla con este requerimiento, TECHCUP-FUTBOL debe tener previamente el registro de los equipos que disputarán el partido y canchas disponibles |
| **Actor** | Árbitro |
| **Flujo principal** | 1. El árbitro inicia sesión.<br>2. Accede a la sección de partidos asignados.<br>3. El sistema muestra los detalles correspondientes.|
| **Diagrama de caso de uso** | ![Diagrama Consultar Partidos](docs/uml/casosDeUso/rf10ConsultarPartidos.png)|
| **Poscondiciones** | *Se espera como resultado que el árbitro visualiza correctamente la información del partido asignado |

### 2.11 Requerimiento Funcional 11

| Campo | Descripción |
|------|-------------|
| **ID** | RF-011 |
| **Nombre del requerimiento** | Tabla de Posiciones Automática |
| **Descripción** | *El sistema debe calcular automáticamente la tabla de posiciones del torneo, actualizando estadísticas como puntos, partidos jugados, victorias, empates, derrotas, goles a favor y goles en contra, cada vez que se registre o modifique el resultado de un partido. |
| **Precondiciones** | *Para que el sistema cumpla con este requerimiento, TECHCUP-FUTBOL debe tener previamente registro de partidos |
| **Actor** | Organizador |
| **Flujo principal** | 1. Se registra o actualiza el resultado de un partido.<br>2. El sistema valida la información ingresada.<br>3. El sistema recalcula las estadísticas de los equipos involucrados.<br>4. El sistema actualiza la tabla de posiciones del torneo. |
| **Diagrama de caso de uso** | *imagen y link*|
| **Poscondiciones** | *Se espera como resultado la tabla de posiciones queda actualizada automáticamente y disponible para consulta |

### 2.12 Requerimiento Funcional 12
| Campo | Descripción |
|-------|-------------|
| **ID** | RF-12 |
| **Nombre del requerimiento** | Generación de Llaves Eliminatorias |
| **Descripción** | El sistema debe generar automáticamente la fase eliminatoria del torneo una vez finalizada la fase de grupos. Esto incluye: generar los partidos iniciales de la fase eliminatoria de forma aleatoria, y generar automáticamente los cruces de cuartos de final, semifinal y final a medida que avanzan los resultados. |
| **Precondiciones** | Para que el sistema cumpla con este requerimiento, TECHCUP FÚTBOL debe tener previamente: un torneo en estado *En progreso*, la fase de grupos completamente finalizada con todos los resultados registrados, y al menos cuatro equipos clasificados para la fase eliminatoria. |
| **Actor** | Organizador (disparador) / Sistema (ejecutor automático) |
| **Flujo principal** | 1. El Organizador indica al sistema que la fase de grupos ha finalizado.<br>2. El sistema valida que todos los partidos de la fase de grupos tengan resultado registrado.<br>3. El sistema selecciona aleatoriamente los cruces de la primera ronda eliminatoria.<br>4. El sistema genera y publica la llave eliminatoria visible para todos los actores.<br>5. El Organizador registra el resultado de cada partido eliminatorio.<br>6. El sistema avanza automáticamente a la siguiente ronda (cuartos → semifinal → final).<br>7. El sistema publica al campeón al finalizar la final. |
| **Diagrama de caso de uso** | <img width="492" height="463" alt="imagen" src="https://github.com/user-attachments/assets/27736b28-b64a-4ba6-a7b9-8529ceeebdc5" />
| **Poscondiciones** | Se espera como resultado: la llave eliminatoria generada y visible para todos los usuarios, los partidos de la ronda inicial creados en el sistema con fecha y cancha pendiente de asignar, y el torneo avanzando automáticamente de ronda en ronda conforme se registran resultados. |

### 2.13 Requerimiento Funcional 13

| Campo | Descripción |
|-------|-------------|
| **ID** | RF-13 |
| **Nombre del requerimiento** | Estadísticas e Historial |
| **Descripción** | El sistema debe permitir consultar información estadística e histórica del torneo, incluyendo: máximos goleadores del torneo en curso e histórico, historial completo de partidos con resultados y detalles, resultados por equipo (partidos ganados, empatados, perdidos, goles a favor y en contra), e historial de torneos anteriores con sus respectivos campeones y estadísticas. |
| **Precondiciones** | Para que el sistema cumpla con este requerimiento, TECHCUP FÚTBOL debe tener previamente: al menos un torneo creado (en progreso o finalizado), al menos un partido con resultado registrado, y el usuario autenticado con una sesión activa válida. |
| **Actor** | Usuario (cualquier rol: Estudiante, Capitán, Organizador, Árbitro o Administrador) |
| **Flujo principal** | 1. El usuario accede a la sección de Estadísticas del sistema.<br>2. El sistema muestra las estadísticas del torneo activo: tabla de goleadores, resultados y tabla de posiciones.<br>3. El usuario aplica filtros opcionales por torneo, equipo o jugador.<br>4. El sistema actualiza la vista con los datos filtrados.<br>5. El usuario accede a la sección de Historial para consultar torneos anteriores.<br>6. El sistema muestra los torneos pasados con campeón, resultados finales y estadísticas agregadas.<br>7. El usuario navega entre torneos para comparar datos históricos. |
| **Diagrama de caso de uso** | <img width="618" height="603" alt="imagen" src="https://github.com/user-attachments/assets/a1d3377f-95a9-4344-9c29-47e4bdf0f384" />
| **Poscondiciones** | Se espera como resultado: el usuario visualiza las estadísticas solicitadas de forma organizada y actualizada, los datos son calculados automáticamente a partir de los resultados registrados por el Organizador, y el historial de torneos anteriores queda persistido y accesible en cualquier momento. |

### 2.14 Requerimiento Funcional 14
| Campo | Descripción |
|-------|-------------|
| **ID** | RF-14 |
| **Nombre del requerimiento** | Auditoría del Sistema |
| **Descripción** | El sistema debe registrar automáticamente las acciones relevantes realizadas por los usuarios. Cada entrada del log debe incluir: identificación del usuario que realizó la acción, tipo de acción ejecutada (creación, modificación, eliminación o consulta sensible), fecha y hora exacta, entidad o recurso afectado, y estado anterior y posterior del recurso cuando aplique. |
| **Precondiciones** | Para que el sistema cumpla con este requerimiento, TECHCUP FÚTBOL debe tener previamente: el sistema en estado operacional, el módulo de auditoría habilitado en la configuración del sistema, y el usuario con una sesión activa válida al momento de ejecutar la acción. |
| **Actor** | Sistema (ejecutor automático del registro) / Administrador (consultor del log de auditoría) |
| **Flujo principal** | 1. El usuario (cualquier rol) ejecuta una acción relevante dentro del sistema.<br>2. El sistema intercepta la acción de forma transparente al usuario.<br>3. El sistema registra en el log: usuario, tipo de acción, fecha y hora, recurso afectado y resultado de la operación.<br>4. El registro queda persistido de forma inmutable en la base de datos de auditoría.<br>5. El Administrador accede al módulo de auditoría.<br>6. El sistema muestra el log completo con opciones de filtrado por usuario, fecha o tipo de acción.<br>7. El Administrador aplica los filtros deseados y el sistema actualiza la vista del log. |
| **Diagrama de caso de uso** | <img width="526" height="484" alt="imagen" src="https://github.com/user-attachments/assets/2112cddc-39ca-4468-ad3b-fee60562141e" />
| **Poscondiciones** | Se espera como resultado: cada acción relevante queda registrada de forma automática, completa e inmutable, el Administrador puede consultar y filtrar el historial de auditoría en cualquier momento, y el sistema garantiza trazabilidad completa de las operaciones para efectos de seguridad y cumplimiento. |
