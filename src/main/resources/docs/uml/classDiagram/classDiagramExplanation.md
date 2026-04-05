# 📘 Sistema de Gestión de Torneos de Fútbol

## 🧠 Introducción

Este proyecto modela un sistema completo para la gestión de torneos de fútbol, abarcando desde la inscripción de equipos hasta la generación de estadísticas y llaves eliminatorias.

El diseño se basa en principios de **modelado orientado al dominio (DDD-lite)**, priorizando:

- Alta cohesión  
- Bajo acoplamiento  
- Reutilización de entidades  
- Evitar redundancia de datos  

El objetivo es representar de forma fiel el comportamiento real de un torneo.

---

# 🏗️ Arquitectura General

## 🔥 `Tournament` (Aggregate Root)

Es el núcleo del sistema. Desde aquí se controla:

- Equipos (`Team`)
- Partidos (`Match`)
- Inscripciones (`Registration`)
- Tabla de posiciones (`TeamStats`)
- Estadísticas (`PlayerStats`)
- Llaves eliminatorias

💡 Todas las operaciones importantes se centralizan aquí para mantener consistencia.

---

# 🧩 Principales Entidades

## ⚽ `Team`

Representa un equipo dentro del torneo.

### Responsabilidades:
- Gestionar jugadores  
- Validar composición del equipo  
- Determinar si puede participar  

### Métodos:
```java
addPlayer()
removePlayer()
isValidTeam()
canParticipate()
````

---

## 👤 `Player`

Representa un participante del torneo.

Incluye:

* Posiciones preferidas
* Afiliación
* Información personal

---

## 🔗 `Affiliation`

Tipos de usuario:

* STUDENT
* GRADUATE
* ADMINISTRATIVE_PERSONAL
* PROFESSOR
* FAMILY

💡 Evita herencia innecesaria.

---

## 🧾 `Registration`

Gestión de inscripción.

### Estados:

* PENDING
* IN_REVIEW
* APPROVED
* REJECTED

Incluye:

* Comprobante de pago
* Validación

---

## 🧑‍⚖️ `Referee`

* Consulta partidos
* Accede a información

---

## 🧑‍💼 `Organizer`

* Crear torneos
* Validar inscripciones
* Configurar torneo

---

## 🛠️ `Administrator`

* Gestiona programas

---

# ⚽ Gestión de Partidos

## 🧩 `Match`

Contiene:

* Equipos (home/away)
* Marcador
* Árbitro
* Fecha
* Cancha (`Field`)
* Fase (`MatchPhase`)
* Eventos (`MatchEvent`)

---

## 📌 `MatchEvent`

Tipos:

* GOAL
* YELLOW_CARD
* RED_CARD

Incluye:

* player
* team
* minute

💡 Se usa:

```java
List<MatchEvent>
```

---

# 📋 Alineaciones

## 🧩 `Lineup`

Incluye:

* Titulares
* Suplentes
* Posiciones

### Métodos:

```java
addStarter()
addSubstitute()
removeStarter()
removeSubstitute()
assignPosition()
isValidLineup()
isEditable()
```

---

# 📊 Tabla de Posiciones

## 🧩 `TeamStats`

⚠️ Dato derivado (no persistente)

* team
* played
* won
* draw
* lost
* goalsFor
* goalsAgainst
* goalDifference
* points

---

## En `Tournament`

```java
calculateStandings()
getStandingsSorted()
```

Orden:

1. points
2. goalDifference
3. goalsFor

---

# 📈 Estadísticas

## 🧩 `PlayerStats`

* player
* goals

---

## En `Tournament`

```java
getTopScorers()
getMatchHistory()
getResultsByTeam()
```

---

# 🏆 Llaves Eliminatorias

## 📌 `MatchPhase`

* GROUP_STAGE
* QUARTERFINAL
* SEMIFINAL
* FINAL

---

## En `Tournament`

```java
generateKnockoutMatches()
generateSemifinals()
generateFinal()
```

Flujo:

1. Clasificados
2. Mezcla
3. Creación de partidos
4. Avance por ganador

---

# 🧱 Patrones de Diseño

* 🟢 Aggregate Root → `Tournament` centraliza la lógica
* 🟢 Encapsulación → cada clase gestiona su comportamiento
* 🟢 CQS →

    * `addPlayer()` (comando)
    * `isValidTeam()` (consulta)
* 🟢 Composición sobre herencia → `Player → Affiliation`
* 🟢 Datos derivados → no se almacenan standings ni estadísticas
* 🟢 Uso de enums → `MatchStatus`, `MatchPhase`, `EventType`

---

# 🚫 Decisiones Importantes

* ❌ No usar Bracket
* ❌ No duplicar datos
* ❌ No acoplar backend con frontend

---

# 🧠 Conclusión

El sistema:

* Modela el dominio real
* Usa buenas prácticas
* Evita redundancia
* Es escalable

💥 Listo para implementación real.

---

# 🏁 Nota Final

Este modelo demuestra:

* Pensamiento arquitectónico
* Buen modelado UML
* Aplicación de principios SOLID y DDD-lite

```

---

# 🔥 Ahora sí

👉 Copias TODO ese bloque  
👉 Lo pegas en `README.md`  
👉 Listo para entregar  

---

Si quieres subirlo aún más:

👉 te agrego badges + estructura pro tipo GitHub (nivel proyecto empresarial) 🚀
```

canParticipate()
