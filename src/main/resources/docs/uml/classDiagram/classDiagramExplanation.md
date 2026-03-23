# 📊 Class Diagram Explanation – TECHCUP FÚTBOL

Este documento describe el diseño del diagrama de clases del sistema TECHCUP FÚTBOL, incluyendo entidades, relaciones, patrones de diseño y decisiones clave.

---

# 🧠 1. Visión General

El sistema permite gestionar:

- Equipos
- Jugadores
- Torneos
- Invitaciones
- Inscripciones
- Alineaciones

Se diseñó bajo principios de bajo acoplamiento y alta cohesión.

---

# 👤 2. Modelo de Usuarios

- User (abstract)
- id: Long 
- name: String 
- email: String 
- password: String 
- status: Boolean

Subclases:

- Player
- Organizer
- Administrator
- Referee

---

## ⚽ Player
Player

- role: RoleType 
- fieldPosition: FieldPosition 
- team: Team



- Un jugador pertenece a un solo equipo
- `FieldPosition` define el tipo general del jugador

---

# ⚽ 3. Equipos

- Team 
- id: Long 
- name: String 
- color: String 
- logo: String 
- captain: Player 
- players: List<Player>
- status: TeamStatus


- El capitán es un `Player`
- Las reglas de cantidad de jugadores se manejan fuera del modelo

---

# 🧱 4. Builder Pattern

- <<builder>> TeamBuilder 
- setName(name: String): TeamBuilder 
- setColor(color: String): TeamBuilder 
- setLogo(logo: String): TeamBuilder 
- setCaptain(captain: Player): TeamBuilder 
- addPlayer(player: Player): TeamBuilder 
- build(): Team


Permite construir equipos paso a paso.

---

# 📩 5. Invitaciones

Invitation

- id: Long
- sender: Player 
- receiver: Player 
- team: Team 
- status: InvitationStatus 
- message: String


---

# 🧾 6. Inscripciones

- Registration 
- id: Long 
- team: Team 
- tournament: Tournament 
- paymentProof: String 
- status: RegistrationStatus 
- createdAt: Date


Estados:

PENDING
UNDER_REVIEW
APPROVED
REJECTED


Representa el proceso de validación del pago.

---

# ⚽ 7. Alineaciones

Lineup

- id: Long 
- team: Team 
- match: Match 
- starters: List<Player>
- substitutes: List<Player>
- positions: Map<Position, Player>
- strategy: FormationStrategy 
- assignPlayer(position: Position, player: Player)


---

# 🎯 8. Enum Position

Position
GK
LB
RB
CB1
CB2
CB3
LM
RM
CM1
CM2
CAM
LW
RW
ST1
ST2


---

# ⚠️ Diferencia importante

| Tipo | Uso |
|------|-----|
| FieldPosition | tipo de jugador |
| Position | ubicación en cancha |

---

# 🧠 9. Strategy Pattern


FormationStrategy

getAvailablePositions(): List<Position>


Implementaciones:

- Formation433
- Formation442
- Formation352

Responsabilidades:

- Strategy define posiciones
- Lineup guarda asignaciones
- Capitán decide ubicación

---

# 🔍 10. Búsqueda de Jugadores

PlayerSearchService

searchByPosition()

searchByName()

searchByAge()

searchByGender()

searchById()


---

# ⚖️ 11. Validaciones

Las siguientes reglas NO se representan en el diagrama:

- Mínimo 7 jugadores
- Máximo 12 jugadores
- Un jugador no puede pertenecer a dos equipos
- Mayoría de ciertos programas
- 7 jugadores por partido

Estas se implementan en lógica de negocio (services).

---

# 🏆 12. Patrones utilizados

- Builder → creación de equipos
- Strategy → formaciones
- Service → búsqueda de jugadores
- Enum → tipado fuerte

---

# 🧠 13. Principios aplicados

- Single Responsibility Principle
- Bajo acoplamiento
- Alta cohesión

---

# 🚀 14. Conclusión

El diseño:

- Es flexible
- Escalable
- Fácil de mantener
- Representa correctamente el dominio

---

# 🎤 Frase clave

“El sistema separa la construcción de equipos mediante Builder y la variabilidad de formaciones mediante Strategy, manteniendo las reglas de negocio fuera del modelo.”