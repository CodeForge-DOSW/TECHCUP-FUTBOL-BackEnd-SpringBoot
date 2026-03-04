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

### 3. Tareas ( EP-01):

| Campo | Descripción |
|------|-------------|
| **ID** | TR-01 |
| **Título** | Modelar entidad Torneo en base de datos |
| **ID de la Historia de Uso asociada** | HU-01 |
| **Descripción** | Diseñar y crear la estructura de la entidad Torneo con sus atributos (fechas, estado, costo, cantidad de equipos).|
| **Tareas requisito** | Ninguna |

### 3.1 Tareas ( EP-01):

| Campo | Descripción |
|------|-------------|
| **ID** | TR-02 |
| **Título** |Diseñar formulario de creación de torneo en frontend |
| **ID de la Historia de Uso asociada** | HU-01 |
| **Descripción** |Construir la interfaz gráfica que permita al organizador ingresar la información del torneo.|
| **Tareas requisito** | TR-01 |

### 3.2 Tareas ( EP-01):

| Campo | Descripción |
|------|-------------|
| **ID** | TR-03 |
| **Título** |Implementar actualización de reglamento y fechas |
| **ID de la Historia de Uso asociada** | HU-02 |
| **Descripción** |Desarrollar funcionalidad para guardar y modificar reglamento, fechas importantes y cierre de inscripciones.|
| **Tareas requisito** | TR-02 |

### 3.3 Tareas ( EP-01):

| Campo | Descripción |
|------|-------------|
| **ID** | TR-04 |
| **Título** |Modelar entidades Cancha y Horario |
| **ID de la Historia de Uso asociada** | HU-03 |
| **Descripción** |Crear estructuras de datos para representar canchas y horarios asociados a los partidos.|
| **Tareas requisito** | TR-01 |

### 3.4 Tareas ( EP-01):

| Campo | Descripción |
|------|-------------|
| **ID** | TR-05 |
| **Título** |Implementar cambio de estado de inscripción de equipo|
| **ID de la Historia de Uso asociada** | HU-04 |
| **Descripción** |Desarrollar la funcionalidad que permita al organizador aprobar o rechazar equipos inscritos y actualizar su estado en el sistema.|
| **Tareas requisito** | TR-01 |
