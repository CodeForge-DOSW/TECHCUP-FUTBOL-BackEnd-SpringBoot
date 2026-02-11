# LABORATORIO 2: SOLID – Patrones de Diseño – Diagramación UML Clases y POO Avanzada

## PREGUNTAS INICIALES:
1. ¿Qué ventaja ofrece el polimorfismo en el diseño de clases frente al uso de
   múltiples condicionales para determinar el comportamiento de un objeto?

El polimorfismo permite que un mismo método tenga diferentes comportamientos según el objeto que lo implemente, evitando el uso de múltiples estructuras condicionales. Esto mejora la legibilidad del código, facilita la extensibilidad del sistema y reduce la posibilidad de errores al agregar nuevos comportamientos sin modificar el código existente.

2. ¿Por qué una clase inmutable puede mejorar la seguridad?

Una clase inmutable mejora la seguridad porque su estado no puede modificarse después de su creación. Esto evita cambios accidentales o no autorizados en los datos, garantiza la consistencia del objeto y facilita el manejo seguro en entornos concurrentes.

3. Según el principio de Abierto/Cerrado, ¿cómo deberíamos modificar el
   sistema si queremos añadir una nueva funcionalidad sin alterar el código
   existente?

Según el principio de Abierto/Cerrado, el sistema debe extenderse mediante nuevas clases o implementaciones, utilizando herencia o interfaces, en lugar de modificar el código ya existente. De esta manera se agregan nuevas funcionalidades sin afectar las ya implementadas.

4. ¿Qué es y por qué usamos el pom.xml?

El archivo pom.xml es el archivo principal de configuración de un proyecto Maven. Se utiliza para definir las dependencias, plugins, versión del proyecto y el ciclo de vida de construcción, permitiendo automatizar y estandarizar el proceso de compilación y empaquetado.

5. ¿Qué diferencia hay entre mvn compile, mvn package y mvn install?

- mvn compile: compila el código fuente del proyecto.
- mvn package: compila el código y genera el archivo empaquetado (JAR o WAR).
- mvn install: compila, empaqueta e instala el artefacto en el repositorio local de Maven para que pueda ser usado por otros proyectos.

6. ¿Qué diferencia existe entre una interfaz y una clase abstracta?

Una interfaz define un conjunto de métodos que una clase debe implementar, sin proporcionar implementación ni estado. Una clase abstracta puede contener métodos abstractos y métodos con implementación, así como atributos, y sirve como una base común para clases relacionadas.

---

## Retos completados

### Reto 1 – El problema de la tienda de Don Pepe
#### Implementación
## Cliente

![img.png](img.png)
![img_1.png](img_1.png)
![img_2.png](img_2.png)
![img_3.png](img_3.png)
![img_4.png](img_4.png)
![img_5.png](img_5.png)
![img_6.png](img_6.png)
![img_7.png](img_7.png)
![img_8.png](img_8.png)

---


#### Evidencia de la respuesta ejecutada

![img.png](img.png)
![img_1.png](img_1.png)

---
#### Lo solicitado en cada Reto

- ¿Cómo está aplicando en su solución cada uno de los principios SOLID?

En la solución se aplican los principios SOLID porque cada clase tiene una función específica (por ejemplo, Producto solo representa datos y Descuento solo aplica descuentos), y el sistema permite agregar nuevos tipos de descuento sin modificar las clases existentes. Además, la clase Cliente trabaja con la abstracción Descuento en lugar de depender de clases concretas, lo que hace el diseño más flexible y organizado.

- ¿Cómo están aplicando polimorfismo en tu solución?

El polimorfismo se aplica cuando el programa usa la interfaz Descuento sin saber qué tipo específico de descuento está ejecutando. Dependiendo del tipo de cliente, se aplica automáticamente el descuento correspondiente (nuevo o antiguo), sin necesidad de usar condicionales.

---

### Reto 6 – Nombre del Reto
#### Evidencia del código solución implementado

---

##### Categoría del patrón de diseño
ESTRUCTURALES

##### Patrón Utilizado
DECORATOR

##### Justificación
El patrón Decorator permite añadir responsabilidades adicionales a un objeto de manera dinámica sin alterar su estructura original. Esto es útil cuando se desea extender la funcionalidad de una clase sin modificar su código, promoviendo la adherencia al principio de Abierto/Cerrado.

En este caso de los cafés se utiliza el patrón Decorator para añadir diferentes tipos de ingredientes (como leche, azúcar, etc.) a una bebida base (como café o té) sin modificar las clases originales de las bebidas, evitando la proliferación de subclases para cada combinación posible de ingredientes.

##### ¿Cómo lo aplicó?
Se creó una interfaz base para las bebidas y luego se implementaron clases concretas para cada tipo de bebida. A continuación, se crearon clases decoradoras que implementan la misma interfaz y añaden ingredientes adicionales a la bebida base. Cada decorador envuelve una instancia de la bebida original y añade su propio comportamiento (como el costo adicional y la descripción del ingrediente) antes de delegar las llamadas al objeto original.
