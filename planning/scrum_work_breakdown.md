# 📄 System Planning

## Work Breakdown: Epics, User Stories, and Tasks

The implementation of the identified requirements of TECHCUP FUTBOL is broken down as follows:

### 1. Epics:

#### Epic 1

| Field | Description |
| ----- | ----------- |
| **ID**| [SCRUM-14](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-14)|
| **Title** | Comprehensive Tournament Management |
| **Description** | TECHCUP FOOTBALL needs this epic to allow the complete administration of the tournament, from its creation to its completion, including the overall tournament setup, status, dates, regulations and control of the tournament lifecycle |
| **Stakeholder** | Tournament Organizer |

#### Epic 2

| Field | Description |
| ----- | ----------- |
| **ID** | [SCRUM-37](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-37) |
| **Title** | User Management and Authentication |
| **Description** | TECHCUP FOOTBALL needs this epic to allow users to register on the platform, log in securely, and set up their sports profile with relevant information to participate in the tournament|
| **Stakeholder** | Student, Graduate, Professor, Administrative Staff, Family Member |

#### Epic 3

| Field | Description |
| ----- | ----------- |
| **ID** | [SCRUM-38](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-38) |
| **Title** | Role Management and Access Control |
| **Description** | TECHCUP FUTBOL needs this epic to manage the different roles of the system and control access to the platform's functionalities according to the type of user, guaranteeing security and organization in the use of the system |
| **Stakeholder** | System Administrator |

#### Epic 4

| Field | Description |
| ----- | ----------- |
| **ID** | [SCRUM-39](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-39) |
| **Title** | Equipment Management |
| **Description** | TECHCUP FOOTBALL needs this epic to allow captains to create and manage teams, configure their characteristics, invite players, and ensure that the established rules for team formation in the tournament are followed |
| **Stakeholder** | Team Captain |

#### Epic 5

| Field | Description |
| ----- | ----------- |
| **ID** | [SCRUM-40](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-40)|
| **Title** | Player Management and Invitations |
| **Description** | TECHCUP FUTBOL needs this epic to allow the search for available players and manage the sending, acceptance or rejection of invitations to be part of a team within the tournament |
| **Stakeholder** | Team Captain, Student, Graduate, Professor, Administrative Staff, Family Member |

#### Epic 6

| Field | Description |
| ----- | ----------- |
| **ID** | [SCRUM-41](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-41) |
| **Title** | Registration and Payment Management |
| **Description** | TECHCUP FUTBOL needs this epic to manage the team registration process for the tournament, including the registration of payment receipts and the approval or rejection of the registration by the organizer |
| **Stakeholder** | Team Captain, Tournament Organizer |

#### Epic 7

| Field | Description |
| ----- | ----------- |
| **ID** | [SCRUM-42](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-42) |
| **Title** | Alignment Management |
| **Description** | TECHCUP FOOTBALL needs this epic to allow captains to configure their team's lineup before each match, defining starters, reserves, and tactical formation |
| **Stakeholder** | Team Captains |

#### Epic 8

| Field | Description |
| ----- | ----------- |
| **ID** | [SCRUM-43](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-43) |
| **Title** | Match Management |
| **Description** | TECHCUP FUTBOL needs this epic to manage the tournament matches, allowing it to record results, game statistics and keep the information of each match up to date. |
| **Stakeholder** | Tournament Organizer, Referee |

#### Epic 9

| Field | Description |
| ----- | ----------- |
**ID** | [SCRUM-44](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-44) |
| **Title** | Classification and Standings |
| **Description** | TECHCUP FUTBOL needs this epic to automatically calculate and display the tournament standings from the results of the recorded matches |
| **Stakeholder** | Student, Graduate, Professor, Administrative Staff, Family Member, Team Captain, Tournament Organizer |

#### Epic 10

| Field | Description |
| ----- | ----------- |
|**ID** | [SCRUM-45](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-45) |
|**Title** | Elimination Phase Management |
|**Description** | TECHCUP FUTBOL needs this epic to automatically generate the knockout brackets once the group stage is over and manage the tournament's progress until the champion is determined |
|**Stakeholder** | Tournament Organizer |

#### Epic 11

| Field | Description |
| ----- | ----------- |
| **ID** | [SCRUM-46](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-46) |
| **Title** | Tournament Statistics and History |
| **Description** | TECHCUP FUTBOL needs this epic to allow the consultation of current and historical tournament statistics, including top scorers, results, team performance and previous tournaments |
| **Stakeholder** | Student, Graduate, Professor, Administrative Staff, Family Member, Team Captain, Tournament Organizer |

#### Epic 12

| Field | Description |
| ----- | ----------- |
| **ID** | [SCRUM-47](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-47) |
| **Title** | System Audit and Traceability |
| **Description** |TECHCUP FUTBOL needs this epic to automatically record relevant actions performed by users, allowing consultation of an audit history that guarantees traceability and security of the system |
| **Stakeholder** | System Administrator |

###  User stories

#### EP-01

##### HU-01

| Field | Description |
| ----- | ----------- |
| **ID**| [SCRUM-26](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-26) |
| **Title** | Create tournament |
| **Description** | As the organizer, I want to create a tournament to define the basic characteristics of the competition. |
| **Priority** | High |
| **Estimate** | 5 points |

##### HU-02

| Campo | Descripción |
| ----- | ----------- |
| **ID** | [SCRUM-27](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-27) |
| **Title** | Configure rules and dates |
| **Description** | As the organizer, I want to configure the tournament rules and dates to establish the competition's regulations and schedule |
| **Priority** | High |
| **Estimate** | 5 points |

##### HU-03

| Campo | Descripción |
| ----- | ----------- |
| **ID** | [SCRUM-28](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-28) |
| **Title** | Change tournament status |
| **Description** | As the organizer, I want to start or end (Draft, Active, In Progress, Completed) the tournament to control the competition's lifecycle |
| **Priority** | High |
| **Estimate** | 3 points |

##### HU-04

| Field | Description |
| ----- | ----------- |
| **ID** | [SCRUM-29](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-29) |
| **Title** | View tournament information |
| **Description** | As the organizer, I would like to review the tournament information to verify its configuration and status |
| **Priority** | Medium |
| **Estimate** | 3 points |

#### EP-02

##### HU-05

| Field | Description | 
| ----- | ----------- |
| **ID** | [SCRUM-48](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-48) |
| **Title** | User Registration |
| **Description** | As a user, I want to register on the platform to participate in the tournament |
| **Priority** | High |
| **Estimate** | 5 points |

##### HU-06


| Field | Description |
| ----- | ----------- |
| **ID** | [SCRUM-49](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-49) |
| **Title** | Log in |
| **Description** | As a user, I want to log in to the system to access my account and the available features |
| **Priority** | High |
| **Estimate** | 3 points |

##### HU-07

| Field | Description |
| ----- | ----------- |
| **ID** | [SCRUM-50](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-50) |
| **Title** | Configure sports profile |
| **Description** | As a player, I want to configure my sports profile with a photo, jersey number, and playing positions so that the captains can learn about my abilities |
| **Priority** | Medium |
| **Estimate** | 5 points |

#### EP-03

##### HU-08

| Campo | Descripción |
| ----- | ----------- |
| **ID** | [SCRUM-51](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-51) |
| **Title** | Assigning roles to users |
| **Description** | As an administrator, I want to assign roles to users to control permissions within the system |
| **Priority** | High |
| **Estimate** | 5 points |

##### HU-09


| Campo | Descripción |
| ----- | ----------- |
| **ID** | [SCRUM-52](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-52) |
| **Title** | Restrict access by role |
| **Description** | As a system, I want to restrict access to functionalities based on the user's role to ensure security on the platform |
| **Priority** | High |
| **Estimate** | 5 points |

#### EP-04

##### HU-10

| Campo | Descripción |
| ----- | ----------- |
| **ID** | [SCRUM-53](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-53) |
| **Title** | Create a team |
| **Description** | As captain, I want to create a team with a name, crest, and colors to participate in tournament 2 |
| **Priority** | High |
| **Estimate** | 5 points ||

##### HU-11

| Campo | Descripción |
| ----- | ----------- |
| **ID** | [SCRUM-54](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-54) |
| **Title** | Manage Team |
| **Description** | As captain, I want to manage my team's players to comply with the tournament rules |
| **Priority** | High |
| **Estimate** | 5 points |

#### EP-05

##### HU-12

| Campo | Descripción |
| ----- | ----------- |
| **ID** | [SCRUM-55](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-55) |
| **Title** | Invite players |
| **Description** | As captain, I want to send invitations to players to join my team |
| **Priority** | High |
| **Estimate** | 5 points |

##### HU-13

| Campo | Descripción |
| ----- | ----------- |
| **ID** | [SCRUM-56](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-56) |
| **Title** | Respond to invitation |
| **Description** | As a player, I want to accept or decline an invitation to decide if I want to join a team |
| **Priority** | High |
| **Estimate** | 3 points |

##### HU-14

| Campo | Descripción |
| ----- | ----------- |
| **ID** | [SCRUM-57](https://codeforge-squad-dosw.atlassian.net/browse/SCRUM-57) |
| **Title** | Search for players |
| **Description** | As captain, I want to search for players using filters to find suitable players for my team |
| **Priority** | Medium |
| **Estimate** | 5 points |

#### EP-06

##### HU-15

| Campo           | Descripción                                                                                               |
| --------------- | --------------------------------------------------------------------------------------------------------- |
| **ID**          | HU-14                                                                                                     |
| **Título**      | Subir comprobante de pago                                                                                 |
| **Descripción** | Como capitán quiero subir el comprobante de pago para completar la inscripción de mi equipo en el torneo. |
| **Prioridad**   | Alta                                                                                                      |
| **Estimación**  | 5 puntos                                                                                                  |

##### HU-16

| Campo           | Descripción                                                                                                     |
| --------------- | --------------------------------------------------------------------------------------------------------------- |
| **ID**          | HU-15                                                                                                           |
| **Título**      | Revisar pago de inscripción                                                                                     |
| **Descripción** | Como organizador quiero revisar los comprobantes de pago para aprobar o rechazar la inscripción de los equipos. |
| **Prioridad**   | Alta                                                                                                            |
| **Estimación**  | 5 puntos                                                                                                        |

#### EP-07

##### HU-17

| Campo           | Descripción                                                                      |
| --------------- | -------------------------------------------------------------------------------- |
| **ID**          | HU-16                                                                            |
| **Título**      | Configurar alineación                                                            |
| **Descripción** | Como capitán quiero definir la alineación de mi equipo para preparar el partido. |
| **Prioridad**   | Media                                                                            |
| **Estimación**  | 5 puntos                                                                         |

##### HU-18

| Campo           | Descripción                                                                                           |
| --------------- | ----------------------------------------------------------------------------------------------------- |
| **ID**          | HU-17                                                                                                 |
| **Título**      | Consultar alineación rival                                                                            |
| **Descripción** | Como capitán quiero consultar la alineación del equipo rival para analizar la estrategia del partido. |
| **Prioridad**   | Media                                                                                                 |
| **Estimación**  | 3 puntos                                                                                              |

#### EP-08

##### HU-19

| Campo           | Descripción                                                                                                  |
| --------------- | ------------------------------------------------------------------------------------------------------------ |
| **ID**          | HU-18                                                                                                        |
| **Título**      | Registrar resultados de partidos                                                                             |
| **Descripción** | Como organizador quiero registrar el marcador final de un partido para actualizar la información del torneo. |
| **Prioridad**   | Alta                                                                                                         |
| **Estimación**  | 5 puntos                                                                                                     |

##### HU-20

| Campo           | Descripción                                                                                                            |
| --------------- | ---------------------------------------------------------------------------------------------------------------------- |
| **ID**          | HU-19                                                                                                                  |
| **Título**      | Registrar estadísticas del partido                                                                                     |
| **Descripción** | Como árbitro quiero registrar estadísticas como goleadores y tarjetas para mantener información detallada del partido. |
| **Prioridad**   | Media                                                                                                                  |
| **Estimación**  | 5 puntos                                                                                                               |

#### EP-09

##### HU-21

| Campo           | Descripción                                                                                           |
| --------------- | ----------------------------------------------------------------------------------------------------- |
| **ID**          | HU-20                                                                                                 |
| **Título**      | Consultar tabla de posiciones                                                                         |
| **Descripción** | Como usuario quiero consultar la tabla de posiciones para conocer la clasificación actual del torneo. |
| **Prioridad**   | Alta                                                                                                  |
| **Estimación**  | 3 puntos                                                                                              |

#### EP-10

##### HU-22

| Campo           | Descripción                                                                                                                     |
| --------------- | ------------------------------------------------------------------------------------------------------------------------------- |
| **ID**          | HU-21                                                                                                                           |
| **Título**      | Generar llaves eliminatorias                                                                                                    |
| **Descripción** | Como organizador quiero generar automáticamente las llaves eliminatorias para continuar el torneo después de la fase de grupos. |
| **Prioridad**   | Alta                                                                                                                            |
| **Estimación**  | 8 puntos                                                                                                                        |

##### HU-23

| Campo           | Descripción                                                                                                       |
| --------------- | ----------------------------------------------------------------------------------------------------------------- |
| **ID**          | HU-22                                                                                                             |
| **Título**      | Avanzar rondas eliminatorias                                                                                      |
| **Descripción** | Como sistema quiero avanzar automáticamente a la siguiente ronda eliminatoria cuando se registren los resultados. |
| **Prioridad**   | Alta                                                                                                              |
| **Estimación**  | 5 puntos                                                                                                          |

#### EP-11

##### HU-24

| Campo           | Descripción                                                                                                |
| --------------- | ---------------------------------------------------------------------------------------------------------- |
| **ID**          | HU-23                                                                                                      |
| **Título**      | Consultar estadísticas del torneo                                                                          |
| **Descripción** | Como usuario quiero consultar estadísticas del torneo para analizar el rendimiento de equipos y jugadores. |
| **Prioridad**   | Media                                                                                                      |
| **Estimación**  | 5 puntos                                                                                                   |

##### HU-25

| Campo           | Descripción                                                                                                     |
| --------------- | --------------------------------------------------------------------------------------------------------------- |
| **ID**          | HU-24                                                                                                           |
| **Título**      | Consultar historial de torneos                                                                                  |
| **Descripción** | Como usuario quiero consultar el historial de torneos anteriores para revisar campeones y estadísticas pasadas. |
| **Prioridad**   | Media                                                                                                           |
| **Estimación**  | 5 puntos                                                                                                        |

#### EP-12

##### HU-26

| Campo           | Descripción                                                                                                     |
| --------------- | --------------------------------------------------------------------------------------------------------------- |
| **ID**          | HU-25                                                                                                           |
| **Título**      | Registrar acciones del sistema                                                                                  |
| **Descripción** | Como sistema quiero registrar las acciones realizadas por los usuarios para mantener un historial de auditoría. |
| **Prioridad**   | Media                                                                                                           |
| **Estimación**  | 5 puntos                                                                                                        |

##### HU-27

| Campo           | Descripción                                                                                                           |
| --------------- | --------------------------------------------------------------------------------------------------------------------- |
| **ID**          | HU-26                                                                                                                 |
| **Título**      | Consultar registros de auditoría                                                                                      |
| **Descripción** | Como administrador quiero consultar los registros de auditoría para supervisar las acciones realizadas en el sistema. |
| **Prioridad**   | Media                                                                                                                 |
| **Estimación**  | 5 puntos                                                                                                              |

### 3. Tareas:

| Campo                                 | Descripción                                                                                                                                                                    |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-01                                                                                                                                                                          |
| **Título**                            | Modelar entidad Torneo en base de datos                                                                                                                                        |
| **ID de la Historia de Uso asociada** | HU-01                                                                                                                                                                          |
| **Descripción**                       | Diseñar y crear la estructura de la entidad Torneo con atributos como nombre, fecha de inicio, fecha de finalización, estado, costo de inscripción y número máximo de equipos. |
| **Tareas requisito**                  | -                                                                                                                                                                              |


| Campo                                 | Descripción                                                                                          |
| ------------------------------------- | ---------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-02                                                                                                |
| **Título**                            | Crear repositorio para gestión de torneos                                                            |
| **ID de la Historia de Uso asociada** | HU-01                                                                                                |
| **Descripción**                       | Implementar la capa de acceso a datos que permita almacenar y consultar torneos en la base de datos. |
| **Tareas requisito**                  | TR-01                                                                                                |



| Campo                                 | Descripción                                                                               |
| ------------------------------------- | ----------------------------------------------------------------------------------------- |
| **ID**                                | TR-03                                                                                     |
| **Título**                            | Implementar servicio de creación de torneos                                               |
| **ID de la Historia de Uso asociada** | HU-01                                                                                     |
| **Descripción**                       | Desarrollar la lógica de negocio que permita registrar nuevos torneos dentro del sistema. |
| **Tareas requisito**                  | TR-02                                                                                     |


| Campo                                 | Descripción                                                                                              |
| ------------------------------------- | -------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-04                                                                                                    |
| **Título**                            | Crear formulario de creación de torneo                                                                   |
| **ID de la Historia de Uso asociada** | HU-01                                                                                                    |
| **Descripción**                       | Diseñar la interfaz donde el organizador pueda ingresar los datos necesarios para crear un nuevo torneo. |
| **Tareas requisito**                  | -                                                                                                        |


| Campo                                 | Descripción                                                                                                                  |
| ------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-05                                                                                                                        |
| **Título**                            | Validar datos ingresados del torneo                                                                                          |
| **ID de la Historia de Uso asociada** | HU-01                                                                                                                        |
| **Descripción**                       | Implementar validaciones para verificar que los datos ingresados por el organizador sean correctos antes de crear el torneo. |
| **Tareas requisito**                  | TR-03, TR-04                                                                                                                 |


| Campo                                 | Descripción                                                                                 |
| ------------------------------------- | ------------------------------------------------------------------------------------------- |
| **ID**                                | TR-06                                                                                       |
| **Título**                            | Guardar torneo en el sistema                                                                |
| **ID de la Historia de Uso asociada** | HU-01                                                                                       |
| **Descripción**                       | Registrar el torneo creado en la base de datos para que esté disponible dentro del sistema. |
| **Tareas requisito**                  | TR-03, TR-05                                                                                |


| Campo                                 | Descripción                                                                                                                                      |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-07                                                                                                                                            |
| **Título**                            | Diseñar estructura de reglamento del torneo                                                                                                      |
| **ID de la Historia de Uso asociada** | HU-02                                                                                                                                            |
| **Descripción**                       | Definir los atributos necesarios para almacenar el reglamento del torneo, incluyendo reglas generales, sanciones y condiciones de participación. |
| **Tareas requisito**                  | -                                                                                                                                                |


| Campo                                 | Descripción                                                                                  |
| ------------------------------------- | -------------------------------------------------------------------------------------------- |
| **ID**                                | TR-08                                                                                        |
| **Título**                            | Implementar entidad de reglamento en base de datos                                           |
| **ID de la Historia de Uso asociada** | HU-02                                                                                        |
| **Descripción**                       | Crear la estructura en la base de datos para almacenar el reglamento asociado a cada torneo. |
| **Tareas requisito**                  | TR-07                                                                                        |



| Campo                                 | Descripción                                                                                                                                     |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-09                                                                                                                                           |
| **Título**                            | Implementar funcionalidad para definir fechas del torneo                                                                                        |
| **ID de la Historia de Uso asociada** | HU-02                                                                                                                                           |
| **Descripción**                       | Desarrollar la lógica que permita registrar y actualizar las fechas importantes del torneo como inicio, cierre de inscripciones y finalización. |
| **Tareas requisito**                  | TR-01                                                                                                                                           |


| Campo                                 | Descripción                                                                                         |
| ------------------------------------- | --------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-10                                                                                               |
| **Título**                            | Crear interfaz para configurar reglamento y fechas                                                  |
| **ID de la Historia de Uso asociada** | HU-02                                                                                               |
| **Descripción**                       | Diseñar la interfaz donde el organizador pueda ingresar y modificar las reglas y fechas del torneo. |
| **Tareas requisito**                  | -                                                                                                   |

| Campo                                 | Descripción                                                                                       |
| ------------------------------------- | ------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-11                                                                                             |
| **Título**                            | Guardar configuración del reglamento del torneo                                                   |
| **ID de la Historia de Uso asociada** | HU-02                                                                                             |
| **Descripción**                       | Implementar el almacenamiento de la configuración del reglamento y las fechas dentro del sistema. |
| **Tareas requisito**                  | TR-08, TR-09, TR-10                                                                               |


| Campo                                 | Descripción                                                                                                    |
| ------------------------------------- | -------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-12                                                                                                          |
| **Título**                            | Definir estados del torneo                                                                                     |
| **ID de la Historia de Uso asociada** | HU-03                                                                                                          |
| **Descripción**                       | Definir los posibles estados del torneo dentro del sistema como creado, en inscripción, en curso y finalizado. |
| **Tareas requisito**                  | TR-01                                                                                                          |


| Campo                                 | Descripción                                                                                          |
| ------------------------------------- | ---------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-13                                                                                                |
| **Título**                            | Implementar lógica para actualizar estado del torneo                                                 |
| **ID de la Historia de Uso asociada** | HU-03                                                                                                |
| **Descripción**                       | Desarrollar la lógica que permita modificar el estado del torneo según las acciones del organizador. |
| **Tareas requisito**                  | TR-12                                                                                                |


| Campo                                 | Descripción                                                                                                   |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-14                                                                                                         |
| **Título**                            | Crear opción en la interfaz para cambiar estado del torneo                                                    |
| **ID de la Historia de Uso asociada** | HU-03                                                                                                         |
| **Descripción**                       | Implementar en la interfaz del sistema la opción para que el organizador pueda iniciar o finalizar el torneo. |
| **Tareas requisito**                  | -                                                                                                             |


| Campo                                 | Descripción                                                                                                                                              |
| ------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-15                                                                                                                                                    |
| **Título**                            | Validar condiciones para iniciar o finalizar torneo                                                                                                      |
| **ID de la Historia de Uso asociada** | HU-03                                                                                                                                                    |
| **Descripción**                       | Implementar validaciones para verificar que el torneo cumpla condiciones necesarias antes de cambiar su estado, como número mínimo de equipos inscritos. |
| **Tareas requisito**                  | TR-13                                                                                                                                                    |

| Campo                                 | Descripción                                                                                                                       |
| ------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-16                                                                                                                             |
| **Título**                            | Modelar entidad Usuario en base de datos                                                                                          |
| **ID de la Historia de Uso asociada** | HU-04                                                                                                                             |
| **Descripción**                       | Diseñar la estructura de la entidad Usuario con atributos como nombre, correo electrónico, contraseña, rol y estado de la cuenta. |
| **Tareas requisito**                  | -                                                                                                                                 |

| Campo                                 | Descripción                                                                                                       |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-17                                                                                                             |
| **Título**                            | Crear repositorio para gestión de usuarios                                                                        |
| **ID de la Historia de Uso asociada** | HU-04                                                                                                             |
| **Descripción**                       | Implementar la capa de acceso a datos que permita registrar, consultar y actualizar usuarios en la base de datos. |
| **Tareas requisito**                  | TR-16                                                                                                             |

| Campo                                 | Descripción                                                                                |
| ------------------------------------- | ------------------------------------------------------------------------------------------ |
| **ID**                                | TR-18                                                                                      |
| **Título**                            | Implementar servicio de registro de usuarios                                               |
| **ID de la Historia de Uso asociada** | HU-04                                                                                      |
| **Descripción**                       | Desarrollar la lógica de negocio que permita registrar nuevos usuarios dentro del sistema. |
| **Tareas requisito**                  | TR-17                                                                                      |

| Campo                                 | Descripción                                                                                      |
| ------------------------------------- | ------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-19                                                                                            |
| **Título**                            | Crear formulario de registro de usuario                                                          |
| **ID de la Historia de Uso asociada** | HU-04                                                                                            |
| **Descripción**                       | Diseñar la interfaz donde los usuarios puedan ingresar sus datos para registrarse en el sistema. |
| **Tareas requisito**                  | -                                                                                                |

| Campo                                 | Descripción                                                                                                                                       |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-20                                                                                                                                             |
| **Título**                            | Implementar validación de datos del registro                                                                                                      |
| **ID de la Historia de Uso asociada** | HU-04                                                                                                                                             |
| **Descripción**                       | Implementar validaciones para verificar que los datos ingresados por el usuario sean correctos, como formato de correo y seguridad de contraseña. |
| **Tareas requisito**                  | TR-18, TR-19                                                                                                                                      |


| Campo                                 | Descripción                                                                                                                                             |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-21                                                                                                                                                   |
| **Título**                            | Implementar verificación de credenciales                                                                                                                |
| **ID de la Historia de Uso asociada** | HU-05                                                                                                                                                   |
| **Descripción**                       | Desarrollar la lógica que permita verificar el correo electrónico y la contraseña ingresados por el usuario contra los datos almacenados en el sistema. |
| **Tareas requisito**                  | TR-17                                                                                                                                                   |

| Campo                                 | Descripción                                                                                                                          |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-22                                                                                                                                |
| **Título**                            | Implementar servicio de autenticación                                                                                                |
| **ID de la Historia de Uso asociada** | HU-05                                                                                                                                |
| **Descripción**                       | Desarrollar el servicio encargado de autenticar a los usuarios y permitir el acceso al sistema cuando las credenciales sean válidas. |
| **Tareas requisito**                  | TR-21                                                                                                                                |

| Campo                                 | Descripción                                                                                                 |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-23                                                                                                       |
| **Título**                            | Crear interfaz de inicio de sesión                                                                          |
| **ID de la Historia de Uso asociada** | HU-05                                                                                                       |
| **Descripción**                       | Diseñar la interfaz donde los usuarios puedan ingresar su correo y contraseña para acceder a la plataforma. |
| **Tareas requisito**                  | -                                                                                                           |

| Campo                                 | Descripción                                                                                                             |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-24                                                                                                                   |
| **Título**                            | Manejar errores de autenticación                                                                                        |
| **ID de la Historia de Uso asociada** | HU-05                                                                                                                   |
| **Descripción**                       | Implementar mensajes de error cuando las credenciales ingresadas por el usuario sean incorrectas o la cuenta no exista. |
| **Tareas requisito**                  | TR-22, TR-23                                                                                                            |

| Campo                                 | Descripción                                                                                                                                     |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-25                                                                                                                                           |
| **Título**                            | Diseñar estructura de perfil deportivo                                                                                                          |
| **ID de la Historia de Uso asociada** | HU-06                                                                                                                                           |
| **Descripción**                       | Definir los atributos necesarios para el perfil deportivo del jugador como posición, número de camiseta, foto de perfil y estadísticas básicas. |
| **Tareas requisito**                  | TR-16                                                                                                                                           |

| Campo                                 | Descripción                                                                                                 |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-26                                                                                                       |
| **Título**                            | Implementar entidad PerfilDeportivo en base de datos                                                        |
| **ID de la Historia de Uso asociada** | HU-06                                                                                                       |
| **Descripción**                       | Crear la estructura en la base de datos para almacenar la información del perfil deportivo de cada jugador. |
| **Tareas requisito**                  | TR-25                                                                                                       |

| Campo                                 | Descripción                                                                                             |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-27                                                                                                   |
| **Título**                            | Implementar servicio de actualización de perfil deportivo                                               |
| **ID de la Historia de Uso asociada** | HU-06                                                                                                   |
| **Descripción**                       | Desarrollar la lógica que permita a los jugadores crear o actualizar su perfil deportivo en el sistema. |
| **Tareas requisito**                  | TR-26                                                                                                   |

| Campo                                 | Descripción                                                                                            |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-28                                                                                                  |
| **Título**                            | Crear interfaz para editar perfil deportivo                                                            |
| **ID de la Historia de Uso asociada** | HU-06                                                                                                  |
| **Descripción**                       | Diseñar la interfaz donde el jugador pueda ingresar o modificar la información de su perfil deportivo. |
| **Tareas requisito**                  | -                                                                                                      |

| Campo                                 | Descripción                                                                                                                          |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-29                                                                                                                                |
| **Título**                            | Validar información del perfil deportivo                                                                                             |
| **ID de la Historia de Uso asociada** | HU-06                                                                                                                                |
| **Descripción**                       | Implementar validaciones para asegurar que los datos ingresados como posición, dorsal y foto cumplan con los requisitos del sistema. |
| **Tareas requisito**                  | TR-27, TR-28                                                                                                                         |

| Campo                                 | Descripción                                                                                           |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-30                                                                                                 |
| **Título**                            | Definir estructura de roles del sistema                                                               |
| **ID de la Historia de Uso asociada** | HU-07                                                                                                 |
| **Descripción**                       | Definir los diferentes roles del sistema como administrador, organizador, capitán, jugador y árbitro. |
| **Tareas requisito**                  | -                                                                                                     |

| Campo                                 | Descripción                                                                                                |
| ------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-31                                                                                                      |
| **Título**                            | Implementar entidad Rol en base de datos                                                                   |
| **ID de la Historia de Uso asociada** | HU-07                                                                                                      |
| **Descripción**                       | Crear la estructura en la base de datos para almacenar los diferentes roles que pueden tener los usuarios. |
| **Tareas requisito**                  | TR-30                                                                                                      |

| Campo                                 | Descripción                                                                                                                |
| ------------------------------------- | -------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-32                                                                                                                      |
| **Título**                            | Asociar roles a usuarios                                                                                                   |
| **ID de la Historia de Uso asociada** | HU-07                                                                                                                      |
| **Descripción**                       | Implementar la relación entre usuarios y roles para permitir que cada usuario tenga uno o varios roles dentro del sistema. |
| **Tareas requisito**                  | TR-31                                                                                                                      |

| Campo                                 | Descripción                                                                                         |
| ------------------------------------- | --------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-33                                                                                               |
| **Título**                            | Implementar servicio de asignación de roles                                                         |
| **ID de la Historia de Uso asociada** | HU-07                                                                                               |
| **Descripción**                       | Desarrollar la lógica que permita a un administrador asignar o modificar los roles de los usuarios. |
| **Tareas requisito**                  | TR-32                                                                                               |

| Campo                                 | Descripción                                                                                                 |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-34                                                                                                       |
| **Título**                            | Crear interfaz para gestión de roles                                                                        |
| **ID de la Historia de Uso asociada** | HU-07                                                                                                       |
| **Descripción**                       | Diseñar la interfaz que permita al administrador visualizar usuarios y asignarles roles dentro del sistema. |
| **Tareas requisito**                  | TR-33                                                                                                       |

| Campo                                 | Descripción                                                                                                           |
| ------------------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-35                                                                                                                 |
| **Título**                            | Definir permisos por rol                                                                                              |
| **ID de la Historia de Uso asociada** | HU-08                                                                                                                 |
| **Descripción**                       | Definir qué acciones puede realizar cada rol del sistema como administrador, organizador, capitán, jugador y árbitro. |
| **Tareas requisito**                  | TR-30                                                                                                                 |

| Campo                                 | Descripción                                                                                                                 |
| ------------------------------------- | --------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-36                                                                                                                       |
| **Título**                            | Implementar sistema de control de acceso                                                                                    |
| **ID de la Historia de Uso asociada** | HU-08                                                                                                                       |
| **Descripción**                       | Desarrollar la lógica que verifique los permisos de los usuarios antes de permitir el acceso a funcionalidades del sistema. |
| **Tareas requisito**                  | TR-35                                                                                                                       |

| Campo                                 | Descripción                                                                                                                               |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-37                                                                                                                                     |
| **Título**                            | Integrar control de acceso en servicios del sistema                                                                                       |
| **ID de la Historia de Uso asociada** | HU-08                                                                                                                                     |
| **Descripción**                       | Implementar verificaciones de permisos dentro de los servicios principales del sistema para restringir acciones según el rol del usuario. |
| **Tareas requisito**                  | TR-36                                                                                                                                     |

| Campo                                 | Descripción                                                                                                               |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-38                                                                                                                     |
| **Título**                            | Mostrar mensajes de acceso restringido                                                                                    |
| **ID de la Historia de Uso asociada** | HU-08                                                                                                                     |
| **Descripción**                       | Implementar mensajes o notificaciones cuando un usuario intente acceder a funcionalidades para las que no tiene permisos. |
| **Tareas requisito**                  | TR-36                                                                                                                     |

| Campo                                 | Descripción                                                                                                            |
| ------------------------------------- | ---------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-39                                                                                                                  |
| **Título**                            | Modelar entidad Equipo en base de datos                                                                                |
| **ID de la Historia de Uso asociada** | HU-09                                                                                                                  |
| **Descripción**                       | Diseñar la estructura de la entidad Equipo con atributos como nombre del equipo, escudo, colores y capitán del equipo. |
| **Tareas requisito**                  | -                                                                                                                      |

| Campo                                 | Descripción                                                                             |
| ------------------------------------- | --------------------------------------------------------------------------------------- |
| **ID**                                | TR-40                                                                                   |
| **Título**                            | Crear relación entre equipo y capitán                                                   |
| **ID de la Historia de Uso asociada** | HU-09                                                                                   |
| **Descripción**                       | Implementar la relación entre el equipo y el usuario que actúa como capitán del equipo. |
| **Tareas requisito**                  | TR-39                                                                                   |

| Campo                                 | Descripción                                                                        |
| ------------------------------------- | ---------------------------------------------------------------------------------- |
| **ID**                                | TR-41                                                                              |
| **Título**                            | Implementar servicio de creación de equipos                                        |
| **ID de la Historia de Uso asociada** | HU-09                                                                              |
| **Descripción**                       | Desarrollar la lógica que permita a un capitán crear un equipo dentro del sistema. |
| **Tareas requisito**                  | TR-40                                                                              |

| Campo                                 | Descripción                                                                                 |
| ------------------------------------- | ------------------------------------------------------------------------------------------- |
| **ID**                                | TR-42                                                                                       |
| **Título**                            | Crear interfaz para registro de equipo                                                      |
| **ID de la Historia de Uso asociada** | HU-09                                                                                       |
| **Descripción**                       | Diseñar la interfaz donde el capitán pueda ingresar el nombre, escudo y colores del equipo. |
| **Tareas requisito**                  | -                                                                                           |

| Campo                                 | Descripción                                                                                                                  |
| ------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-43                                                                                                                        |
| **Título**                            | Validar datos del equipo                                                                                                     |
| **ID de la Historia de Uso asociada** | HU-09                                                                                                                        |
| **Descripción**                       | Implementar validaciones para verificar que el nombre del equipo no esté repetido y que los datos ingresados sean correctos. |
| **Tareas requisito**                  | TR-41, TR-42                                                                                                                 |

| Campo                                 | Descripción                                                                                                                         |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-44                                                                                                                               |
| **Título**                            | Definir relación entre jugadores y equipos                                                                                          |
| **ID de la Historia de Uso asociada** | HU-10                                                                                                                               |
| **Descripción**                       | Diseñar la relación entre usuarios (jugadores) y equipos para permitir que los jugadores pertenezcan a un equipo dentro del torneo. |
| **Tareas requisito**                  | TR-39, TR-16                                                                                                                        |

| Campo                                 | Descripción                                                                                        |
| ------------------------------------- | -------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-45                                                                                              |
| **Título**                            | Implementar gestión de jugadores en equipos                                                        |
| **ID de la Historia de Uso asociada** | HU-10                                                                                              |
| **Descripción**                       | Desarrollar la lógica que permita agregar o eliminar jugadores de un equipo por parte del capitán. |
| **Tareas requisito**                  | TR-44                                                                                              |

| Campo                                 | Descripción                                                                                           |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-46                                                                                                 |
| **Título**                            | Implementar consulta de plantilla del equipo                                                          |
| **ID de la Historia de Uso asociada** | HU-10                                                                                                 |
| **Descripción**                       | Desarrollar la funcionalidad que permita visualizar la lista de jugadores pertenecientes a un equipo. |
| **Tareas requisito**                  | TR-44                                                                                                 |

| Campo                                 | Descripción                                                                    |
| ------------------------------------- | ------------------------------------------------------------------------------ |
| **ID**                                | TR-47                                                                          |
| **Título**                            | Crear interfaz para administrar equipo                                         |
| **ID de la Historia de Uso asociada** | HU-10                                                                          |
| **Descripción**                       | Diseñar la interfaz donde el capitán pueda gestionar los jugadores del equipo. |
| **Tareas requisito**                  | -                                                                              |

| Campo                                 | Descripción                                                                                                          |
| ------------------------------------- | -------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-48                                                                                                                |
| **Título**                            | Validar límites de jugadores por equipo                                                                              |
| **ID de la Historia de Uso asociada** | HU-10                                                                                                                |
| **Descripción**                       | Implementar validaciones para asegurar que los equipos cumplan con el número mínimo y máximo de jugadores permitido. |
| **Tareas requisito**                  | TR-45                                                                                                                |

| Campo                                 | Descripción                                                                                                                                                 |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-49                                                                                                                                                       |
| **Título**                            | Diseñar estructura de invitaciones                                                                                                                          |
| **ID de la Historia de Uso asociada** | HU-11                                                                                                                                                       |
| **Descripción**                       | Definir la estructura para almacenar invitaciones enviadas por capitanes a jugadores, incluyendo estado de la invitación (pendiente, aceptada o rechazada). |
| **Tareas requisito**                  | -                                                                                                                                                           |

| Campo                                 | Descripción                                                                                       |
| ------------------------------------- | ------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-50                                                                                             |
| **Título**                            | Implementar entidad Invitacion en base de datos                                                   |
| **ID de la Historia de Uso asociada** | HU-11                                                                                             |
| **Descripción**                       | Crear la estructura en la base de datos para registrar las invitaciones enviadas a los jugadores. |
| **Tareas requisito**                  | TR-49                                                                                             |

| Campo                                 | Descripción                                                                                                  |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-51                                                                                                        |
| **Título**                            | Implementar servicio de envío de invitaciones                                                                |
| **ID de la Historia de Uso asociada** | HU-11                                                                                                        |
| **Descripción**                       | Desarrollar la lógica que permita a un capitán enviar invitaciones a jugadores para que se unan a su equipo. |
| **Tareas requisito**                  | TR-50                                                                                                        |

| Campo                                 | Descripción                                                                                       |
| ------------------------------------- | ------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-52                                                                                             |
| **Título**                            | Crear interfaz para enviar invitaciones                                                           |
| **ID de la Historia de Uso asociada** | HU-11                                                                                             |
| **Descripción**                       | Diseñar la interfaz donde el capitán pueda buscar jugadores y enviar invitaciones para su equipo. |
| **Tareas requisito**                  | -                                                                                                 |

| Campo                                 | Descripción                                                                                            |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-53                                                                                                  |
| **Título**                            | Registrar estado de invitación                                                                         |
| **ID de la Historia de Uso asociada** | HU-11                                                                                                  |
| **Descripción**                       | Implementar el almacenamiento y actualización del estado de las invitaciones enviadas a los jugadores. |
| **Tareas requisito**                  | TR-51                                                                                                  |

| Campo                                 | Descripción                                                                                                                  |
| ------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-54                                                                                                                        |
| **Título**                            | Implementar consulta de invitaciones recibidas                                                                               |
| **ID de la Historia de Uso asociada** | HU-12                                                                                                                        |
| **Descripción**                       | Desarrollar la funcionalidad que permita a los jugadores visualizar las invitaciones que han recibido para unirse a equipos. |
| **Tareas requisito**                  | TR-50                                                                                                                        |

| Campo                                 | Descripción                                                                                                                     |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-55                                                                                                                           |
| **Título**                            | Implementar lógica para aceptar invitación                                                                                      |
| **ID de la Historia de Uso asociada** | HU-12                                                                                                                           |
| **Descripción**                       | Desarrollar la lógica que permita a un jugador aceptar una invitación y ser agregado automáticamente al equipo correspondiente. |
| **Tareas requisito**                  | TR-54, TR-45                                                                                                                    |

| Campo                                 | Descripción                                                                                                           |
| ------------------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-56                                                                                                                 |
| **Título**                            | Implementar lógica para rechazar invitación                                                                           |
| **ID de la Historia de Uso asociada** | HU-12                                                                                                                 |
| **Descripción**                       | Desarrollar la funcionalidad que permita a un jugador rechazar una invitación sin modificar la estructura del equipo. |
| **Tareas requisito**                  | TR-54                                                                                                                 |

| Campo                                 | Descripción                                                                                   |
| ------------------------------------- | --------------------------------------------------------------------------------------------- |
| **ID**                                | TR-57                                                                                         |
| **Título**                            | Crear interfaz para responder invitaciones                                                    |
| **ID de la Historia de Uso asociada** | HU-12                                                                                         |
| **Descripción**                       | Diseñar la interfaz donde los jugadores puedan aceptar o rechazar las invitaciones recibidas. |
| **Tareas requisito**                  | -                                                                                             |

| Campo                                 | Descripción                                                                                                                  |
| ------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-58                                                                                                                        |
| **Título**                            | Definir criterios de búsqueda de jugadores                                                                                   |
| **ID de la Historia de Uso asociada** | HU-13                                                                                                                        |
| **Descripción**                       | Definir los criterios que permitirán buscar jugadores en el sistema como nombre, posición de juego, dorsal o disponibilidad. |
| **Tareas requisito**                  | TR-25                                                                                                                        |

| Campo                                 | Descripción                                                                                                    |
| ------------------------------------- | -------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-59                                                                                                          |
| **Título**                            | Implementar servicio de búsqueda de jugadores                                                                  |
| **ID de la Historia de Uso asociada** | HU-13                                                                                                          |
| **Descripción**                       | Desarrollar la lógica que permita consultar jugadores en el sistema utilizando diferentes filtros de búsqueda. |
| **Tareas requisito**                  | TR-58                                                                                                          |

| Campo                                 | Descripción                                                                                                       |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-60                                                                                                             |
| **Título**                            | Optimizar consultas de búsqueda                                                                                   |
| **ID de la Historia de Uso asociada** | HU-13                                                                                                             |
| **Descripción**                       | Implementar mejoras en las consultas a la base de datos para que la búsqueda de jugadores sea rápida y eficiente. |
| **Tareas requisito**                  | TR-59                                                                                                             |

| Campo                                 | Descripción                                                                                                 |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-61                                                                                                       |
| **Título**                            | Crear interfaz para búsqueda de jugadores                                                                   |
| **ID de la Historia de Uso asociada** | HU-13                                                                                                       |
| **Descripción**                       | Diseñar la interfaz donde el capitán pueda buscar jugadores utilizando filtros y visualizar los resultados. |
| **Tareas requisito**                  | -                                                                                                           |

| Campo                                 | Descripción                                                                                                 |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-62                                                                                                       |
| **Título**                            | Mostrar resultados de búsqueda                                                                              |
| **ID de la Historia de Uso asociada** | HU-13                                                                                                       |
| **Descripción**                       | Implementar la visualización de los jugadores encontrados en el sistema junto con su información deportiva. |
| **Tareas requisito**                  | TR-59, TR-61                                                                                                |

| Campo                                 | Descripción                                                                                                                    |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-63                                                                                                                          |
| **Título**                            | Diseñar estructura de comprobantes de pago                                                                                     |
| **ID de la Historia de Uso asociada** | HU-14                                                                                                                          |
| **Descripción**                       | Definir los atributos necesarios para almacenar los comprobantes de pago como archivo, fecha de pago, monto y equipo asociado. |
| **Tareas requisito**                  | -                                                                                                                              |

| Campo                                 | Descripción                                                                                               |
| ------------------------------------- | --------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-64                                                                                                     |
| **Título**                            | Implementar entidad ComprobantePago en base de datos                                                      |
| **ID de la Historia de Uso asociada** | HU-14                                                                                                     |
| **Descripción**                       | Crear la estructura en la base de datos para registrar los comprobantes de pago enviados por los equipos. |
| **Tareas requisito**                  | TR-63                                                                                                     |

| Campo                                 | Descripción                                                                                                 |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-65                                                                                                       |
| **Título**                            | Implementar carga de archivos de comprobantes                                                               |
| **ID de la Historia de Uso asociada** | HU-14                                                                                                       |
| **Descripción**                       | Desarrollar la funcionalidad que permita a los capitanes subir archivos de comprobantes de pago al sistema. |
| **Tareas requisito**                  | TR-64                                                                                                       |

| Campo                                 | Descripción                                                                                        |
| ------------------------------------- | -------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-66                                                                                              |
| **Título**                            | Crear interfaz para subir comprobantes                                                             |
| **ID de la Historia de Uso asociada** | HU-14                                                                                              |
| **Descripción**                       | Diseñar la interfaz donde el capitán pueda seleccionar y subir el archivo del comprobante de pago. |
| **Tareas requisito**                  | -                                                                                                  |

| Campo                                 | Descripción                                                                                                             |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-67                                                                                                                   |
| **Título**                            | Validar formato y tamaño del comprobante                                                                                |
| **ID de la Historia de Uso asociada** | HU-14                                                                                                                   |
| **Descripción**                       | Implementar validaciones para verificar que el archivo subido cumpla con el formato y tamaño permitidos por el sistema. |
| **Tareas requisito**                  | TR-65                                                                                                                   |

| Campo                                 | Descripción                                                                                                           |
| ------------------------------------- | --------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-68                                                                                                                 |
| **Título**                            | Implementar consulta de comprobantes de pago                                                                          |
| **ID de la Historia de Uso asociada** | HU-15                                                                                                                 |
| **Descripción**                       | Desarrollar la funcionalidad que permita al organizador visualizar los comprobantes de pago enviados por los equipos. |
| **Tareas requisito**                  | TR-64                                                                                                                 |

| Campo                                 | Descripción                                                                                    |
| ------------------------------------- | ---------------------------------------------------------------------------------------------- |
| **ID**                                | TR-69                                                                                          |
| **Título**                            | Implementar lógica de aprobación de pagos                                                      |
| **ID de la Historia de Uso asociada** | HU-15                                                                                          |
| **Descripción**                       | Desarrollar la lógica que permita al organizador aprobar los pagos realizados por los equipos. |
| **Tareas requisito**                  | TR-68                                                                                          |


| Campo                                 | Descripción                                                                                                              |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-70                                                                                                                    |
| **Título**                            | Implementar lógica de rechazo de pagos                                                                                   |
| **ID de la Historia de Uso asociada** | HU-15                                                                                                                    |
| **Descripción**                       | Desarrollar la funcionalidad que permita al organizador rechazar comprobantes de pago que no cumplan con los requisitos. |
| **Tareas requisito**                  | TR-68                                                                                                                    |

| Campo                                 | Descripción                                                                                                  |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-71                                                                                                        |
| **Título**                            | Actualizar estado de inscripción del equipo                                                                  |
| **ID de la Historia de Uso asociada** | HU-15                                                                                                        |
| **Descripción**                       | Implementar la actualización del estado de inscripción de un equipo cuando el pago sea aprobado o rechazado. |
| **Tareas requisito**                  | TR-69, TR-70                                                                                                 |

| Campo                                 | Descripción                                                                                            |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-72                                                                                                  |
| **Título**                            | Crear interfaz para revisión de pagos                                                                  |
| **ID de la Historia de Uso asociada** | HU-15                                                                                                  |
| **Descripción**                       | Diseñar la interfaz donde el organizador pueda visualizar los comprobantes y aprobar o rechazar pagos. |
| **Tareas requisito**                  | -                                                                                                      |

| Campo                                 | Descripción                                                                                                                   |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-73                                                                                                                         |
| **Título**                            | Diseñar estructura de alineación                                                                                              |
| **ID de la Historia de Uso asociada** | HU-16                                                                                                                         |
| **Descripción**                       | Definir la estructura para almacenar la alineación de un equipo en un partido, incluyendo jugadores y posiciones en el campo. |
| **Tareas requisito**                  | -                                                                                                                             |

| Campo                                 | Descripción                                                                                                             |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-74                                                                                                                   |
| **Título**                            | Implementar servicio de configuración de alineación                                                                     |
| **ID de la Historia de Uso asociada** | HU-16                                                                                                                   |
| **Descripción**                       | Desarrollar la lógica que permita al capitán seleccionar los jugadores titulares y definir su posición para un partido. |
| **Tareas requisito**                  | TR-73                                                                                                                   |

| Campo                                 | Descripción                                                                                                        |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-75                                                                                                              |
| **Título**                            | Crear interfaz para configurar alineación                                                                          |
| **ID de la Historia de Uso asociada** | HU-16                                                                                                              |
| **Descripción**                       | Diseñar la interfaz donde el capitán pueda seleccionar los jugadores y organizarlos en el campo antes del partido. |
| **Tareas requisito**                  | -                                                                                                                  |

| Campo                                 | Descripción                                                                                                            |
| ------------------------------------- | ---------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-76                                                                                                                  |
| **Título**                            | Implementar consulta de alineación rival                                                                               |
| **ID de la Historia de Uso asociada** | HU-17                                                                                                                  |
| **Descripción**                       | Desarrollar la funcionalidad que permita obtener la alineación registrada del equipo rival para un partido específico. |
| **Tareas requisito**                  | TR-73                                                                                                                  |

| Campo                                 | Descripción                                                                                                 |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-77                                                                                                       |
| **Título**                            | Crear interfaz para visualizar alineación rival                                                             |
| **ID de la Historia de Uso asociada** | HU-17                                                                                                       |
| **Descripción**                       | Diseñar la interfaz donde los capitanes puedan visualizar la alineación del equipo rival antes del partido. |
| **Tareas requisito**                  | -                                                                                                           |

| Campo                                 | Descripción                                                                                           |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-78                                                                                                 |
| **Título**                            | Validar disponibilidad de alineación                                                                  |
| **ID de la Historia de Uso asociada** | HU-17                                                                                                 |
| **Descripción**                       | Implementar validaciones para permitir la consulta solo si el equipo rival ya registró su alineación. |
| **Tareas requisito**                  | TR-76                                                                                                 |

| Campo                                 | Descripción                                                                                                                          |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-79                                                                                                                                |
| **Título**                            | Diseñar estructura de resultados de partido                                                                                          |
| **ID de la Historia de Uso asociada** | HU-18                                                                                                                                |
| **Descripción**                       | Definir la estructura para almacenar los resultados de los partidos, incluyendo equipos participantes, marcador y fecha del partido. |
| **Tareas requisito**                  | -                                                                                                                                    |

| Campo                                 | Descripción                                                                                |
| ------------------------------------- | ------------------------------------------------------------------------------------------ |
| **ID**                                | TR-80                                                                                      |
| **Título**                            | Implementar servicio de registro de resultados                                             |
| **ID de la Historia de Uso asociada** | HU-18                                                                                      |
| **Descripción**                       | Desarrollar la lógica que permita registrar el marcador final de un partido en el sistema. |
| **Tareas requisito**                  | TR-79                                                                                      |

| Campo                                 | Descripción                                                                                         |
| ------------------------------------- | --------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-81                                                                                               |
| **Título**                            | Crear interfaz para registrar resultados                                                            |
| **ID de la Historia de Uso asociada** | HU-18                                                                                               |
| **Descripción**                       | Diseñar la interfaz donde el organizador o árbitro pueda ingresar el resultado final de un partido. |
| **Tareas requisito**                  | -                                                                                                   |

| Campo                                 | Descripción                                                                                                         |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-82                                                                                                               |
| **Título**                            | Diseñar estructura de estadísticas del partido                                                                      |
| **ID de la Historia de Uso asociada** | HU-19                                                                                                               |
| **Descripción**                       | Definir la estructura para almacenar estadísticas del partido como goleadores, tarjetas y otros eventos relevantes. |
| **Tareas requisito**                  | -                                                                                                                   |

| Campo                                 | Descripción                                                                                            |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-83                                                                                                  |
| **Título**                            | Implementar servicio de registro de estadísticas                                                       |
| **ID de la Historia de Uso asociada** | HU-19                                                                                                  |
| **Descripción**                       | Desarrollar la lógica que permita registrar y almacenar las estadísticas generadas durante un partido. |
| **Tareas requisito**                  | TR-82                                                                                                  |

| Campo                                 | Descripción                                                                                             |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-84                                                                                                   |
| **Título**                            | Crear interfaz para registrar estadísticas                                                              |
| **ID de la Historia de Uso asociada** | HU-19                                                                                                   |
| **Descripción**                       | Diseñar la interfaz donde el árbitro pueda ingresar las estadísticas del partido como goles y tarjetas. |
| **Tareas requisito**                  | -                                                                                                       |

| Campo                                 | Descripción                                                                                                                            |
| ------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-85                                                                                                                                  |
| **Título**                            | Diseñar estructura de tabla de posiciones                                                                                              |
| **ID de la Historia de Uso asociada** | HU-20                                                                                                                                  |
| **Descripción**                       | Definir la estructura que almacene los datos de la tabla de posiciones como puntos, partidos jugados, goles a favor y goles en contra. |
| **Tareas requisito**                  | TR-79                                                                                                                                  |

| Campo                                 | Descripción                                                                                                            |
| ------------------------------------- | ---------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-86                                                                                                                  |
| **Título**                            | Implementar cálculo automático de la tabla de posiciones                                                               |
| **ID de la Historia de Uso asociada** | HU-20                                                                                                                  |
| **Descripción**                       | Desarrollar la lógica que actualice automáticamente la tabla de posiciones cuando se registren resultados de partidos. |
| **Tareas requisito**                  | TR-80, TR-85                                                                                                           |

| Campo                                 | Descripción                                                                                 |
| ------------------------------------- | ------------------------------------------------------------------------------------------- |
| **ID**                                | TR-87                                                                                       |
| **Título**                            | Crear interfaz para visualizar tabla de posiciones                                          |
| **ID de la Historia de Uso asociada** | HU-20                                                                                       |
| **Descripción**                       | Diseñar la interfaz donde los usuarios puedan consultar la clasificación actual del torneo. |
| **Tareas requisito**                  | -                                                                                           |

| Campo                                 | Descripción                                                                                      |
| ------------------------------------- | ------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-88                                                                                            |
| **Título**                            | Diseñar estructura de llaves eliminatorias                                                       |
| **ID de la Historia de Uso asociada** | HU-21                                                                                            |
| **Descripción**                       | Definir la estructura para almacenar los enfrentamientos de las rondas eliminatorias del torneo. |
| **Tareas requisito**                  | -                                                                                                |

| Campo                                 | Descripción                                                                                                                    |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-89                                                                                                                          |
| **Título**                            | Implementar generación automática de llaves                                                                                    |
| **ID de la Historia de Uso asociada** | HU-21                                                                                                                          |
| **Descripción**                       | Desarrollar la lógica que permita generar automáticamente los enfrentamientos eliminatorios basados en la tabla de posiciones. |
| **Tareas requisito**                  | TR-86, TR-88                                                                                                                   |

| Campo                                 | Descripción                                                                                                |
| ------------------------------------- | ---------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-90                                                                                                      |
| **Título**                            | Crear interfaz para visualizar llaves eliminatorias                                                        |
| **ID de la Historia de Uso asociada** | HU-21                                                                                                      |
| **Descripción**                       | Diseñar la interfaz donde los usuarios puedan visualizar los cruces de las fases eliminatorias del torneo. |
| **Tareas requisito**                  | -                                                                                                          |

| Campo                                 | Descripción                                                                                                                             |
| ------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-91                                                                                                                                   |
| **Título**                            | Implementar lógica de avance de equipos                                                                                                 |
| **ID de la Historia de Uso asociada** | HU-22                                                                                                                                   |
| **Descripción**                       | Desarrollar la lógica que determine automáticamente qué equipo avanza a la siguiente ronda eliminatoria según el resultado del partido. |
| **Tareas requisito**                  | TR-80, TR-88                                                                                                                            |

| Campo                                 | Descripción                                                                                                                 |
| ------------------------------------- | --------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-92                                                                                                                       |
| **Título**                            | Actualizar estructura de llaves eliminatorias                                                                               |
| **ID de la Historia de Uso asociada** | HU-22                                                                                                                       |
| **Descripción**                       | Implementar la actualización automática de las llaves eliminatorias cuando se definan los ganadores de cada enfrentamiento. |
| **Tareas requisito**                  | TR-91                                                                                                                       |

| Campo                                 | Descripción                                                                                                        |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-93                                                                                                              |
| **Título**                            | Definir estadísticas del torneo                                                                                    |
| **ID de la Historia de Uso asociada** | HU-23                                                                                                              |
| **Descripción**                       | Definir las estadísticas que se podrán consultar como goleadores, equipos con más victorias y tarjetas acumuladas. |
| **Tareas requisito**                  | TR-82                                                                                                              |

| Campo                                 | Descripción                                                                                                                              |
| ------------------------------------- | ---------------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-94                                                                                                                                    |
| **Título**                            | Implementar cálculo de estadísticas del torneo                                                                                           |
| **ID de la Historia de Uso asociada** | HU-23                                                                                                                                    |
| **Descripción**                       | Desarrollar la lógica que permita calcular automáticamente las estadísticas del torneo a partir de los resultados y eventos registrados. |
| **Tareas requisito**                  | TR-93                                                                                                                                    |

| Campo                                 | Descripción                                                                                    |
| ------------------------------------- | ---------------------------------------------------------------------------------------------- |
| **ID**                                | TR-95                                                                                          |
| **Título**                            | Crear interfaz para visualizar estadísticas                                                    |
| **ID de la Historia de Uso asociada** | HU-23                                                                                          |
| **Descripción**                       | Diseñar la interfaz donde los usuarios puedan consultar las estadísticas generales del torneo. |
| **Tareas requisito**                  | -                                                                                              |

| Campo                                 | Descripción                                                                                                                         |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-96                                                                                                                               |
| **Título**                            | Implementar almacenamiento de torneos finalizados                                                                                   |
| **ID de la Historia de Uso asociada** | HU-24                                                                                                                               |
| **Descripción**                       | Desarrollar la funcionalidad que permita conservar la información de torneos finalizados dentro del sistema para futuras consultas. |
| **Tareas requisito**                  | TR-13                                                                                                                               |

| Campo                                 | Descripción                                                                                                     |
| ------------------------------------- | --------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-97                                                                                                           |
| **Título**                            | Implementar consulta de historial de torneos                                                                    |
| **ID de la Historia de Uso asociada** | HU-24                                                                                                           |
| **Descripción**                       | Desarrollar la lógica que permita consultar torneos pasados junto con sus campeones y estadísticas principales. |
| **Tareas requisito**                  | TR-96                                                                                                           |

| Campo                                 | Descripción                                                                                                    |
| ------------------------------------- | -------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-98                                                                                                          |
| **Título**                            | Crear interfaz para visualizar historial de torneos                                                            |
| **ID de la Historia de Uso asociada** | HU-24                                                                                                          |
| **Descripción**                       | Diseñar la interfaz donde los usuarios puedan consultar los torneos realizados anteriormente y sus resultados. |
| **Tareas requisito**                  | -                                                                                                              |

| Campo                                 | Descripción                                                                                                                                |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------ |
| **ID**                                | TR-99                                                                                                                                      |
| **Título**                            | Diseñar estructura de registro de acciones                                                                                                 |
| **ID de la Historia de Uso asociada** | HU-25                                                                                                                                      |
| **Descripción**                       | Definir la estructura para almacenar las acciones realizadas en el sistema, incluyendo usuario, acción realizada, fecha y módulo afectado. |
| **Tareas requisito**                  | -                                                                                                                                          |

| Campo                                 | Descripción                                                                                                         |
| ------------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-100                                                                                                              |
| **Título**                            | Implementar servicio de registro de acciones                                                                        |
| **ID de la Historia de Uso asociada** | HU-25                                                                                                               |
| **Descripción**                       | Desarrollar la lógica que permita registrar automáticamente las acciones realizadas por los usuarios en el sistema. |
| **Tareas requisito**                  | TR-99                                                                                                               |

| Campo                                 | Descripción                                                                                                             |
| ------------------------------------- | ----------------------------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-101                                                                                                                  |
| **Título**                            | Implementar consulta de registros de auditoría                                                                          |
| **ID de la Historia de Uso asociada** | HU-26                                                                                                                   |
| **Descripción**                       | Desarrollar la funcionalidad que permita consultar los registros de acciones realizadas por los usuarios en el sistema. |
| **Tareas requisito**                  | TR-100                                                                                                                  |

| Campo                                 | Descripción                                                                                        |
| ------------------------------------- | -------------------------------------------------------------------------------------------------- |
| **ID**                                | TR-102                                                                                             |
| **Título**                            | Crear interfaz para visualizar registros de auditoría                                              |
| **ID de la Historia de Uso asociada** | HU-26                                                                                              |
| **Descripción**                       | Diseñar la interfaz donde el administrador pueda consultar los registros de auditoría del sistema. |
| **Tareas requisito**                  | -                                                                                                  |



