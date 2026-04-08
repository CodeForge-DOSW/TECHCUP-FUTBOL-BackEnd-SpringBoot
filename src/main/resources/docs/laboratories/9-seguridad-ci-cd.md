
# 🔐 README – SEGURIDAD (SPRING SECURITY, JWT, CI/CD)

---

# 🌐 1. CÓMO PROBAR APIS (POSTMAN)

## 📌 Flujo básico

1. Seleccionar método (GET, POST, etc.)
2. Poner URL
3. (Si aplica) Body → JSON
4. Send

---

## 📊 Qué validar

```text
✔ Código HTTP (200, 401, 404)
✔ Respuesta JSON
✔ Headers
```

---

## 🎯 En parcial

* Si falla → revisa:

  * URL
  * método
  * auth

---

# 🔐 2. CÓMO FUNCIONA SPRING SECURITY (BÁSICO)

## 📌 Activar seguridad

Agregar dependencia → automáticamente protege endpoints

---

## ⚙️ Comportamiento por defecto

* Usuario: `user`
* Password: generado en consola (logs)
  (*como se ve en página 4 del PDF *)

---

## 📌 Autenticación en Postman

* Tipo: **Basic Auth**
* Enviar user + password

---

## 🎯 Clave parcial

* Si no mandas credenciales → 401
* Si mandas mal → 401

---

# 🔑 3. CÓMO CONFIGURAR USUARIO Y PASSWORD

```properties
spring.security.user.name=admin
spring.security.user.password=1234
```

---

# 👥 4. CÓMO MANEJAR ROLES Y PERMISOS

## 📌 Relación típica

```text
User ↔ Role ↔ Permission
```

---

## ⚙️ En JPA

```java
@ManyToMany
@JoinTable(...)
```

---

## 🎯 En parcial

* Usuarios tienen roles
* Roles tienen permisos

---

# 🔐 5. CÓMO HACER AUTENTICACIÓN CON JWT

## 📌 Flujo completo

```text
1. Usuario envía login
2. Se valida usuario/password
3. Se genera token JWT
4. Se retorna token
5. Cliente usa token en requests
```

---

# 🧠 6. CÓMO GENERAR TOKEN (JWT SERVICE)

## 📌 Método clave

```java
generateToken(UserDetails user)
```

---

## ⚙️ Qué hace

* Define usuario
* Define expiración
* Firma con clave secreta

---

## 🎯 En parcial

* Token = string firmado
* Tiene expiración

---

# 📥 7. CÓMO HACER LOGIN (ENDPOINT)

## 📌 Endpoint

```text
POST /auth/login
```

---

## 📌 Body

```json
{
  "username": "user",
  "password": "123"
}
```

---

## 📌 Resultado

```json
{
  "token": "..."
}
```

---

## 🎯 Clave

* Usa AuthenticationManager
* Usa UserDetailsService

---

# 🧠 8. CLASES IMPORTANTES (JWT)

## 📌 UserDetails

* Representa usuario autenticado

---

## 📌 UserDetailsService

* Carga usuario desde BD

---

## 📌 AuthenticationManager

* Valida credenciales

---

## 📌 SimpleGrantedAuthority

* Representa roles/permisos

---

# 🧱 9. CÓMO FUNCIONA EL FILTRO JWT

## 📌 Flujo del filtro

```text
1. Leer header Authorization
2. Verificar "Bearer TOKEN"
3. Extraer username
4. Validar token
5. Autenticar usuario
```

---

## 📌 Header correcto

```text
Authorization: Bearer TOKEN
```

---

## 🎯 Clave parcial

* Sin token → 401
* Con token válido → acceso

---

# 🧠 10. CLASES CLAVE EN EL FILTRO

## 📌 SecurityContextHolder

* Guarda usuario autenticado

---

## 📌 UsernamePasswordAuthenticationToken

* Representa autenticación

---

## 📌 WebAuthenticationDetailsSource

* Agrega detalles del request

---

# ⚙️ 11. CÓMO CONFIGURAR SECURITY

## 📌 Configuración básica

```java
http
  .csrf(csrf -> csrf.disable())
  .authorizeHttpRequests(auth -> auth
      .requestMatchers("/auth/**").permitAll()
      .anyRequest().authenticated()
  )
```

---

## 🎯 Qué significa

* `/auth/**` → público
* resto → protegido

---

## 📌 Registrar filtro

```java
.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
```

---

## 🎯 Clave

* El filtro se ejecuta antes de auth normal

---

# 🧪 12. CÓMO PROBAR JWT EN POSTMAN

## 📌 Paso 1

Login → obtener token

---

## 📌 Paso 2

Usar token:

```text
Authorization: Bearer TOKEN
```

---

## 📌 Paso 3

Consumir endpoint protegido

---

## 🎯 Resultado

* Sin token → falla
* Con token → funciona

---

# 🧪 13. PRUEBAS DE SEGURIDAD (INTEGRACIÓN)

## 📌 Debes probar

```text
✔ Login correcto
✔ Login incorrecto
✔ Endpoint con token válido
✔ Endpoint sin token
```

---

# 🔄 14. CÓMO FUNCIONA CI/CD

## 📌 Pipeline básico

```text
build → test → deploy
```

---

## ⚙️ Flujo

1. build → compila
2. test → ejecuta pruebas
3. deploy → despliega

---

## 📌 Trigger

```text
on: pull_request
```

---

# ⚙️ 15. GITHUB ACTIONS (IDEA CLAVE)

## 📌 Jobs

```text
build
test (depende de build)
deploy (depende de test)
```

---

## 🎯 Clave parcial

* test depende de build
* deploy depende de test

---

# ☁️ 16. DESPLIEGUE (AZURE)

## 📌 Qué necesitas

* App Service
* Variables de entorno
* Base de datos

---

## ⚠️ Problemas típicos

* Puerto incorrecto
* BD no configurada
* credenciales

---

## 🎯 Solución típica

```text
✔ revisar logs
✔ configurar variables
✔ revisar conexión BD
```

---

# 🧠 RESUMEN ULTRA RÁPIDO

* Spring Security → protege endpoints
* Basic Auth → user/password
* JWT → token de autenticación
* Login → genera token
* Filtro JWT → valida token
* Header → Bearer TOKEN
* CI/CD → build → test → deploy
* Sin token → 401


