# 📄 Planeación del Sistema

## Desglose de trabajo: Épicas, Historias de Usuario y Tareas

La implementación de los requerimientos identificados de TECHCUP FUTBOLy se desglosa de la siguiente manera:

### 1. Épicas:

| Campo | Descripción |
|------|-------------|
| **ID** | EP-01 |
| **Título** | Gestión Integral del Torneo |
| **Descripción** | TECHCUP FUTBOL necesita esta épica para permitir la administración completa del torneo, desde su creación hasta su finalización, incluyendo configuración, equipos, calendario, resultados y tabla de posiciones. |
| **Stakeholder** | Organizador del torneo |

| Campo | Descripción |
|------|-------------|
| **ID** | EP-02 |
| **Título** | Gestión de Usuarios y Seguridad |
| **Descripción** | TECHCUP FUTBOL necesita esta épica para garantizar un acceso seguro al sistema, diferenciación de roles y control adecuado de permisos según el tipo de usuario. |
| **Stakeholder** | Administrador del sistema |

| Campo | Descripción |
|------|-------------|
| **ID** | EP-03 |
| **Título** | Gestión de Participación y Competencia |
| **Descripción** | TECHCUP FUTBOL necesita esta épica para permitir la inscripción de equipos, pagos, invitaciones, consulta de partidos y estadísticas del torneo. |
| **Stakeholder** | Capitanes, Jugadores y Árbitros |

### 2. Historias de usuario (EP-01):

| Campo | Descripción |
|------|-------------|
| **ID** | HU-01 |
| **Título** | Crear Torneo |
| **Descripción** | Como organizador quiero crear un torneo con su información básica para iniciar formalmente la gestión del campeonato. |
| **Prioridad** | Alta |
| **Estimación** | 8 puntos |

### 2.1 Historias de usuario (EP-01):

| Campo | Descripción |
|------|-------------|
| **ID** | HU-02 |
| **Título** | Configurar Reglamento y Fechas |
| **Descripción** | Como organizador quiero configurar reglamento y fechas importantes para establecer las reglas oficiales del torneo. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos |


### 2.2 Historias de usuario (EP-01):

| Campo | Descripción |
|------|-------------|
| **ID** | HU-03 |
| **Título** | Definir Canchas y Horarios |
| **Descripción** | Como organizador quiero definir canchas y horarios de los partidos para organizar correctamente el calendario. |
| **Prioridad** | Alta |
| **Estimación** | 8 puntos |

### 2.3 Historias de usuario (EP-01):

| Campo | Descripción |
|------|-------------|
| **ID** | HU-04 |
| **Título** | Administrar Equipos Inscritos |
| **Descripción** | Como organizador quiero visualizar y aprobar equipos inscritos para garantizar que cumplan los requisitos del torneo. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos |

### 2.4 Historias de usuario (EP-02):

| Campo | Descripción |
|------|-------------|
| **ID** | HU-05 |
| **Título** | Registro de Usuario |
| **Descripción** | Como usuario quiero registrarme en la plataforma con mi correo correspondiente para poder acceder al sistema y participar en el torneo. |
| **Prioridad** | Alta |
| **Estimación** | 8 puntos |

### 2.5 Historias de usuario (EP-02):

| Campo | Descripción |
|------|-------------|
| **ID** | HU-06 |
| **Título** | Autenticación de Usuario |
| **Descripción** | Como usuario quiero iniciar sesión de manera segura para poder acceder a las funcionalidades según mi rol. |
| **Prioridad** | Alta |
| **Estimación** | 5 puntos |

### 2.6 Historias de usuario (EP-02):

| Campo | Descripción |
|------|-------------|
| **ID** | HU-07 |
| **Título** | Control de Acceso por Rol |
| **Descripción** | Como administrador quiero que el sistema controle los permisos según el rol del usuario para poder garantizar seguridad y acceso adecuado a la información. |
| **Prioridad** | Alta |
| **Estimación** | 8 puntos |



### 2.7 Historias de usuario (EP-03):

| Campo | Descripción |
|------|-------------|
| **ID** | HU-08 |
| **Título** | Inscripción de equipo al torneo |
| **Descripción** | Como capitán quiero inscribir mi equipo al torneo subiendo el comprobante de pago para que el organizador pueda revisarlo y aprobar nuestra participación. |
| **Prioridad** | Alta |
| **Estimación** | 8 puntos |

### 2.8 Historias de usuario (EP-03):

| Campo | Descripción |
|------|-------------|
| **ID** | HU-09 |
| **Título** | Consulta de partidos por árbitro |
| **Descripción** | Como árbitro quiero poder consultar los partidos que tengo asignados con su fecha, hora, cancha y equipos participantes para organizarme con anticipación. |
| **Prioridad** | Media |
| **Estimación** | 5 puntos |


### 3. Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-01 |
| **Título** | Modelar entidad Torneo en base de datos |
| **ID de la Historia de Uso asociada** | HU-01 |
| **Descripción** | Diseñar y crear la estructura de la entidad Torneo con sus atributos (fechas, estado, costo, cantidad de equipos).|
| **Tareas requisito** | - |

### 3.1 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-02 |
| **Título** |Diseñar formulario de creación de torneo en frontend |
| **ID de la Historia de Uso asociada** | HU-01 |
| **Descripción** |Construir la interfaz gráfica que permita al organizador ingresar la información del torneo.|
| **Tareas requisito** | TR-01 |

### 3.2 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-03 |
| **Título** |Implementar actualización de reglamento y fechas |
| **ID de la Historia de Uso asociada** | HU-02 |
| **Descripción** |Desarrollar funcionalidad para guardar y modificar reglamento, fechas importantes y cierre de inscripciones.|
| **Tareas requisito** | TR-02 |

### 3.3 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-04 |
| **Título** |Modelar entidades Cancha y Horario |
| **ID de la Historia de Uso asociada** | HU-03 |
| **Descripción** |Crear estructuras de datos para representar canchas y horarios asociados a los partidos.|
| **Tareas requisito** | TR-01 |

### 3.4 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-05 |
| **Título** |Implementar cambio de estado de inscripción de equipo|
| **ID de la Historia de Uso asociada** | HU-04 |
| **Descripción** |Desarrollar la funcionalidad que permita al organizador aprobar o rechazar equipos inscritos y actualizar su estado en el sistema.|
| **Tareas requisito** | TR-01 |

### 3.5 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-06 |
| **Título** | Diseñar estructura de Usuario |
| **ID de la Historia de Usuario asociada** | HU-05 |
| **Descripción** | Definir los datos necesarios del usuario en base de datos y backend. |
| **Tareas requisito** | TR-01 |

### 3.6 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-07 |
| **Título** | Implementar registro en backend |
| **ID de la Historia de Usuario asociada** | HU-05 |
| **Descripción** | Desarrollar la funcionalidad que permita guardar usuarios en el sistema. |
| **Tareas requisito** | TR-06 |

### 3.7 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-08 |
| **Título** | Diseñar interfaz de registro |
| **ID de la Historia de Usuario asociada** | HU-05 |
| **Descripción** | Crear la pantalla de registro en el frontend. |
| **Tareas requisito** | - |

### 3.8 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-09 |
| **Título** | Conectar registro frontend-backend |
| **ID de la Historia de Usuario asociada** | HU-05 |
| **Descripción** | Permitir que el formulario envíe la información al backend. |
| **Tareas requisito** | TR-07, TR-08 |

### 3.9 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-10 |
| **Título** | Implementar autenticación básica |
| **ID de la Historia de Usuario asociada** | HU-06 |
| **Descripción** | Permitir que el usuario inicie sesión validando sus datos. |
| **Tareas requisito** | TR-07 |

### 3.10 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-11 |
| **Título** | Diseñar interfaz de inicio de sesión |
| **ID de la Historia de Usuario asociada** | HU-06 |
| **Descripción** | Crear la pantalla de login en el frontend. |
| **Tareas requisito** | - |

### 3.11 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-12 |
| **Título** | Conectar login frontend-backend |
| **ID de la Historia de Usuario asociada** | HU-06 |
| **Descripción** | Permitir la comunicación entre frontend y backend para validar usuario. |
| **Tareas requisito** | TR-10, TR-11 |

### 3.12 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-13 |
| **Título** | Manejar sesión de usuario |
| **ID de la Historia de Usuario asociada** | HU-06 |
| **Descripción** | Mantener la sesión activa durante el uso del sistema. |
| **Tareas requisito** | TR-12 |

### 3.13 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-14 |
| **Título** | Definir tipos de rol |
| **ID de la Historia de Usuario asociada** | HU-07 |
| **Descripción** | Establecer los roles que tendrá el sistema. |
| **Tareas requisito** | TR-06 |

### 3.14 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-15 |
| **Título** | Implementar validación de permisos |
| **ID de la Historia de Usuario asociada** | HU-07 |
| **Descripción** | Controlar qué acciones puede realizar cada rol. |
| **Tareas requisito** | TR-14 |

### 3.15 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-16 |
| **Título** | Adaptar interfaz según rol |
| **ID de la Historia de Usuario asociada** | HU-07 |
| **Descripción** | Mostrar opciones en el sistema según el tipo de usuario. |
| **Tareas requisito** | TR-13 |

### 3.16 Tareas:

| Campo | Descripción |
|------|-------------|
| **ID** | TR-17 |
| **Título** | Implementar carga de comprobante de pago |
| **ID de la Historia de Usuario asociada** | HU-08 |
| **Descripción** | Desarrollar la funcionalidad que permita al capitán subir el comprobante de pago a la plataforma para que quede registrado con estado Pendiente. |
| **Tareas requisito** | - |

### 3.17 Tareas :

| Campo | Descripción |
|------|-------------|
| **ID** | TR-18 |
| **Título** | Implementar vista de partidos para el árbitro |
| **ID de la Historia de Usuario asociada** | HU-09 |
| **Descripción** | Desarrollar la pantalla donde el árbitro pueda ver los partidos que tiene asignados, mostrando fecha, hora, cancha y los dos equipos que se enfrentarán. |
| **Tareas requisito** | TR-01 |

