#ss
## Guía Práctica – SCRUM, KANBAN Y ESTIMACIÓN
---

## Escribir Historias de Usuario

Una historia de usuario sigue siempre este formato:

> **Como** [rol del usuario], **quiero** [funcionalidad], **para** [beneficio o razón].

### Ejemplo aplicado a Bankify
> Como cliente de Bankify, quiero consultar el saldo de mi cuenta bancaria, para saber cuánto dinero tengo disponible en cualquier momento.

### Campos que debe tener una HU completa

| Campo | Descripción |
|-------|-------------|
| **ID** | Asignado por Jira (ej: `DOSW-3`) |
| **Título** | Nombre corto de la HU |
| **Descripción** | Formato Como/Quiero/Para |
| **Prioridad** | Alto / Medio / Bajo (la define el PO) |
| **Story Points** | Estimación en puntos (se define en Planning Poker) |
| **Tareas asociadas** | Lista de tareas técnicas para implementarla |

---

## 4. Escribir Tareas de una Historia de Usuario

Cada historia debe tener **al menos 3 tareas**. Las tareas son actividades técnicas concretas y pequeñas.

### Ejemplo para la HU "Consultar saldo"
- Crear endpoint `GET /cuenta/{id}/saldo` en el backend.
- Implementar validación de que el cliente autenticado es el dueño de la cuenta.
- Diseñar la pantalla de visualización del saldo en el frontend.
- Escribir pruebas unitarias para el servicio de consulta de saldo.

### Campos de una tarea en Jira

| Campo | Descripción |
|-------|-------------|
| **Título** | Nombre corto de la tarea |
| **Descripción** | Detalle de qué hay que hacer |
| **Actividades relacionadas** | Subtareas o pasos internos |
| **Asignado a** | Quién del equipo la ejecuta |

---

## 5. Definir Prioridades (rol: Product Owner)

El Product Owner asigna prioridad a cada HU según su valor para el negocio. Se usan tres niveles:

| Nivel | Criterio |
|-------|----------|
| **Alto** | Funcionalidad esencial, el sistema no funciona sin ella o tiene alto impacto en el cliente. |
| **Medio** | Importante pero no bloquea otras funcionalidades. |
| **Bajo** | Deseable, puede postergarse a sprints posteriores. |

Cada prioridad debe incluir una **justificación corta**. Ejemplo:

> **HU-01 – Crear cuenta bancaria → Prioridad: Alta**
> Justificación: Sin la creación de cuentas, ninguna otra funcionalidad del sistema puede ejecutarse. Es el punto de entrada del sistema.

---

## 6. Crear Épicas, Historias y Tareas en Jira

### Crear una Épica
1. En el proyecto Jira, ir a **Backlog** o **Board**.
2. Click en **"Create"** → seleccionar tipo **Epic**.
3. Completar:
    - **Título**: nombre de la épica.
    - **Descripción**: qué cubre esta épica.
    - **Fecha de vencimiento**: fecha esperada de entrega (Julio 2026 para Bankify).
4. Guardar.

### Crear una Historia de Usuario
1. Click en **"Create"** → tipo **Story**.
2. Completar **título** y **descripción** (formato Como/Quiero/Para).
3. Vincular a la épica correspondiente en el campo **"Epic Link"**.
4. Guardar. Jira asigna un ID automático (ej: `DOSW-3`).

### Crear una Tarea
1. Click en **"Create"** → tipo **Task** (o Subtask si es hijo de una HU).
2. Completar **título**, **descripción** y **actividades relacionadas**.
3. Vincular a la historia de usuario correspondiente.
4. Guardar.

### Actualizar Story Points en una Historia
1. Abrir la historia de usuario en Jira.
2. Buscar el campo **"Story Points"** o **"Puntos de Historia Estimados"**.
3. Ingresar el valor consensuado en Planning Poker.
4. Guardar.

---

## 7. Planning Poker – Estimación por Puntos de Historia

### Preparación
- Tener las 4 historias de usuario ya escritas y claras.
- Usar una herramienta: [Planning Poker Online](https://www.planningpokeronline.com) o [Miro](https://miro.com).
- El **Scrum Master** crea la sesión y comparte el link con el equipo.

### Dinámica sesión a sesión
1. El **SM** presenta la primera historia de usuario.
2. El equipo hace preguntas hasta que todos entienden el alcance.
3. Cada integrante selecciona su carta **sin mostrarla** (valores: 1, 2, 3, 5, 8, 13, 20...).
4. Todos revelan sus cartas **al mismo tiempo**.
5. **Si todos coinciden** → ese es el valor de la HU. ✅
6. **Si hay diferencias grandes** (ej: uno vota 3 y otro vota 13):
    - El que votó más bajo explica por qué.
    - El que votó más alto explica por qué.
    - Se vuelve a votar.
    - Se repite hasta llegar a consenso.
7. Se registra el valor final en `scrum_work_breakdown.md` y luego en Jira.

### Valores de referencia (Fibonacci)

| Puntos | Esfuerzo |
|--------|----------|
| 1 – 2 | Trivial o muy simple |
| 3 – 5 | Esfuerzo moderado |
| 8 | Complejo |
| 13 | Muy complejo |
| 20+ | Demasiado grande, dividir la HU |

---

## 8. Planeación del Sprint en Jira

### ¿Qué HU entran al primer sprint?
Se seleccionan las HU con **prioridad más alta** cuya suma de puntos sea manejable para el equipo en 4 semanas.

### Cómo crear y poblar un sprint en Jira
1. En el **Backlog** de Jira, click en **"Create Sprint"**.
2. Arrastrar las historias de usuario seleccionadas desde el backlog al sprint.
3. Para cada tarea del sprint, asignar un responsable en el campo **"Assignee"**.
4. Click en **"Start Sprint"** → definir fechas de inicio y fin.

---

## 9. Archivos de documentación del proyecto

### `scrum_work_breakdown.md` – estructura sugerida

```markdown
# Scrum Work Breakdown

## Roles del equipo
| Integrante | Rol |
|------------|-----|
| Nombre 1   | Product Owner |
| Nombre 2   | Scrum Master |
| Nombre 3   | Developer |

## Épica
- **ID Jira**: DOSW-1
- **Nombre**: [nombre de la épica]
- **Descripción**: [descripción]

## Historias de Usuario

### HU-01 – [Título]  (ID Jira: DOSW-2)
- **Descripción**: Como [rol], quiero [acción], para [beneficio].
- **Prioridad**: Alto / Medio / Bajo
- **Justificación**: [razón de la prioridad]
- **Story Points**: [valor consensuado en Planning Poker]
- **Video estimación**: [link si aplica]

#### Tareas
| ID Jira | Título | Asignado a |
|---------|--------|------------|
| DOSW-5  | [tarea 1] | [nombre] |
| DOSW-6  | [tarea 2] | [nombre] |
| DOSW-7  | [tarea 3] | [nombre] |
```

### `jira.md` – estructura sugerida

```markdown
# Evidencias Jira

## Épica
![Captura épica](../images/jira_epica.png)

## Historias de Usuario
### HU-01
![Captura HU-01](../images/jira_hu01.png)

## Tareas
### Tarea DOSW-5
![Captura tarea](../images/jira_tarea01.png)

## Cronograma
![Cronograma Jira](../images/jira_cronograma.png)

## Sprint Backlog
![Sprint](../images/jira_sprint.png)
**Decisión de planeación**: [explicar por qué se eligieron esas HU para el sprint]
```

---

## 10. Reglas de negocio de Bankify – para definir HU y tareas correctamente

Al escribir las HU y tareas del sistema, tener presentes estas reglas:

- Los números de cuenta tienen **exactamente 10 dígitos**, solo numéricos.
- Los **2 primeros dígitos** identifican el banco (`01` = Bancolombia, `02` = Davivienda).
- Una cuenta solo es válida si el banco está **registrado en el sistema**.
- Los depósitos se hacen mediante **PSE**.
- Los reportes tributarios se generan en **PDF** (para el cliente) y **JSON** (para la DIAN).
- Los roles del sistema son: **cliente**, **asesor**, **supervisor** y **gerente financiero**, cada uno con acciones específicas.

---

> 💡 Para el parcial: lo más importante es saber **cómo se escribe una HU** (formato Como/Quiero/Para), **cómo funciona Planning Poker** (votación simultánea + consenso + Fibonacci) y **qué va en cada nivel** de la jerarquía Épica → HU → Tarea.