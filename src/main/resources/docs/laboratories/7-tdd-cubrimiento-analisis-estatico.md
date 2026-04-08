# 🧠 README – GUÍA PRÁCTICA PARA PARCIAL (TDD, TESTING, COBERTURA, SONAR)

Basado en los temas del laboratorio

---

# 🧪 1. CÓMO HACER PRUEBAS UNITARIAS (JUNIT 5)

## 📌 Estructura básica

```java
class NombreClaseTest {

    @Test
    void deberiaHacerAlgo() {
        // 1. Arrange (preparar)
        
        // 2. Act (ejecutar)
        
        // 3. Assert (verificar)
    }
}
```

---

## ⚙️ Flujo real al escribir una prueba

1. Crear objetos necesarios
2. Ejecutar el método que quieres probar
3. Verificar resultado con `assert`

---

## 🧩 Asserts más usados

```java
assertEquals(esperado, obtenido);
assertTrue(condicion);
assertFalse(condicion);
assertNotNull(objeto);
assertNull(objeto);
```

---

## 🎯 Qué debes probar SIEMPRE

* Casos normales (funciona bien)
* Casos borde (valores límite)
* Casos inválidos (errores)
* Reglas de negocio

---

## ⚠️ Tips clave de parcial

* El nombre del test describe el comportamiento
* Un test = una sola cosa
* No mezclar muchas validaciones en un mismo test

---

# 🔁 2. CÓMO APLICAR TDD (TEST DRIVEN DEVELOPMENT)

## 🔄 Ciclo TDD

1. 🔴 Escribir test (falla)
2. 🟢 Hacer código mínimo (pasa)
3. 🔵 Refactorizar

---

## 📌 Cómo hacerlo en práctica

1. Lees el método que debes implementar
2. Escribes un test basado en esa descripción
3. Ejecutas → debe fallar
4. Implementas lo mínimo para que pase
5. Repites con otro caso

---

## 🎯 En el parcial

* Primero piensas en **condiciones**
* Luego haces tests para cada condición
* Luego implementas

---

# 📊 3. CÓMO GENERAR COBERTURA (JACOCO)

## 📌 Qué es lo importante

* Mide qué tanto código cubren tus pruebas
* Se ejecuta automáticamente con Maven

---

## ⚙️ Cómo usarlo

```bash
mvn test
```

---

## 📁 Dónde ver resultados

```id="2b6h8m"
target/site/jacoco/index.html
```

---

## 📊 Qué debes mirar

* % cobertura total
* Cobertura por clase
* Líneas no cubiertas

---

## 🎯 Cómo subir la cobertura

* Crear más pruebas
* Cubrir:

    * if
    * else
    * errores
    * caminos alternos

---

## ⚠️ Regla típica

* Mínimo: 80%
* Ideal: >85%

---

# 🔍 4. CÓMO HACER ANÁLISIS ESTÁTICO (SONARQUBE)

## 📌 Qué hace

* Detecta errores
* Code smells
* Problemas de calidad
* Seguridad

---

## ⚙️ Flujo práctico

1. Tener Sonar corriendo
2. Tener token
3. Ejecutar:

```bash
mvn verify sonar:sonar -Dsonar.token=TOKEN
```

---

## 📊 Qué revisar en Sonar

* Bugs
* Vulnerabilities
* Code smells
* Coverage
* Duplicación

---

## 🎯 Qué te pueden pedir en parcial

* Ejecutar análisis
* Interpretar resultados
* Corregir código

---

# 📦 5. CÓMO MANEJAR DEPENDENCIAS (MAVEN)

## 📌 Agregar dependencia

```xml
<dependency>
    <groupId>...</groupId>
    <artifactId>...</artifactId>
    <version>...</version>
</dependency>
```

---

## ⚙️ Comandos clave

```bash
mvn clean
mvn compile
mvn test
mvn package
```

---

## 🎯 Tip importante

* Si algo falla → `mvn clean install`

---

# 🌿 6. CÓMO TRABAJAR CON RAMAS (GIT)

## 📌 Flujo básico

```bash
git checkout -b feature/nombre
git add .
git commit -m "mensaje"
git push origin feature/nombre
```

---

## 🔀 Pull Request

* De `feature` → `develop`
* Otro revisa
* Luego merge

---

## 🎯 Buenas prácticas

* 1 feature = 1 rama
* Commits claros
* No trabajar en main

---

# 🧠 7. CÓMO PENSAR CASOS DE PRUEBA (CLAVE)

Cuando veas un método:

### Pregúntate:

1. ¿Qué pasa si TODO sale bien?
2. ¿Qué pasa si algo no existe?
3. ¿Qué pasa si hay datos inválidos?
4. ¿Qué reglas deben cumplirse?

---

## 📌 Plantilla mental

```id="kzbx3c"
caso_exitoso
caso_error
caso_borde
caso_regla_negocio
```

---

# 🧠 RESUMEN ULTRA RÁPIDO (PARA EL PARCIAL)

* Tests = Arrange + Act + Assert
* TDD = test → código → refactor
* JaCoCo = ver cobertura en /target
* Sonar = analizar calidad
* Maven = dependencias + build
* Git = ramas + PR
* Siempre probar:

    * éxito
    * error
    * borde


