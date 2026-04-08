# LABORATORIO 9 – DATA

## Entidades JPA seleccionadas

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