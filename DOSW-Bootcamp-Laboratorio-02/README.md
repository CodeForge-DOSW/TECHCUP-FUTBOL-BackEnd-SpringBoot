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

### Reto 2 – El chef de 5 estrellas
#### Evidencia del código solución implementado
![img.png](img.png)

![img_1.png](img_1.png)

![img_2.png](img_2.png)

![img_3.png](img_3.png)

![img_4.png](img_4.png)

![img_5.png](img_5.png)
#### Evidencia de la respuesta ejecutada
![img_6.png](img_6.png)
#### Lo solicitado en cada Reto
- Categoría del patrón de diseño: es creacional

-Patrón Utilizado: builder
- Justificación: la hamburguesa al tener mucho ingrediente tendriamos que
  tener muchos constructores para cada una de sus variantes por eso se utilizo el
  patron Builder
  o-¿Cómo lo aplicó?
  lo aplique con una interfaz que es la que tiene todos los metodos de
  cntruccion y un se utilizo una clase para que implementara esta interfaz y
  sobreescribiera todos los metodos de esta y ademas se untilizo un director
  que es la clase chef

## Reto #3   El Reino de los Vehículos
### Patrón de Diseño

- Categoría: Patrones Creacionales
- Patrón Utilizado: Abstract Factory

### Justificación

Se implementó el patrón Abstract Factory con el objetivo de desacoplar el proceso de creación de los distintos tipos de vehículos del resto de la aplicación. Dado que el sistema debe manejar familias de objetos relacionados (vehículos terrestres, acuáticos y aéreos), cada una con múltiples modelos y categorías (Económico, Lujo y Usado), el uso de este patrón permite crear estos objetos sin que el cliente conozca las clases concretas que se están instanciando.

Este enfoque facilita la extensibilidad del sistema, ya que permite agregar nuevos tipos de vehículos o nuevas familias de vehículos sin modificar el código del cliente, cumpliendo con el principio de Abierto/Cerrado y reduciendo el acoplamiento entre componentes. Además, el patrón garantiza la coherencia entre los objetos creados dentro de una misma familia, asegurando que cada vehículo se construya con características acordes a su tipo y categoría.

### ¿Cómo se aplicó?
El patrón Abstract Factory se aplicó definiendo una interfaz de fábrica abstracta (VehicleFactory) que declara el método para la creación de vehículos sin especificar sus clases concretas. A partir de esta fábrica abstracta, se implementaron fábricas concretas como LandVehicleFactory, WaterVehicleFactory y AirVehicleFactory, cada una responsable de crear una familia específica de vehículos según su medio de operación: terrestre, acuático o aéreo.

Cada fábrica concreta encapsula la lógica de creación de los modelos de vehículos correspondientes a su familia. En el caso de los vehículos terrestres, se crean autos, bicicletas y motos; para los vehículos acuáticos, se crean lanchas, veleros y jet skis; y para los vehículos aéreos, se crean aviones, avionetas y helicópteros. De esta forma, se garantiza que los objetos creados pertenezcan a una misma familia y mantengan coherencia en sus características.

Las clases concretas de vehículos (Car, Bike, Moto, Boat, Sailboat, JetSki, Plane, Avioneta, Helicopter, entre otras) extienden de la clase abstracta Vehicle, la cual define el comportamiento común y las operaciones que deben implementar todos los vehículos, como el cálculo de la velocidad máxima, el equipamiento y el precio final según su categoría.


## Reto #4  La Estafa de la Casa de Cambio
### Patrón de Diseño

- Categoría: Patrones Estructurales
- Patrón Utilizado: Adapter

### Justificación

Se utilizó el patrón Adapter porque el sistema necesitaba integrar un servicio
de conversión de tasas reales cuya interfaz no coincidía con la forma en que el
cliente requería realizar las conversiones.

El patrón Adapter permite:
- Adaptar una clase existente a una nueva interfaz sin modificar su código.
- Evitar cambios en el código cliente cuando se reemplazan o agregan servicios.
- Encapsular la lógica de adaptación en una sola clase.
- Facilitar la extensibilidad del sistema a futuro.

Gracias a este patrón, el sistema puede utilizar diferentes proveedores de tasas
de cambio sin afectar la lógica principal de las transacciones.

### ¿Cómo se aplicó?
1. **Interfaz Objetivo (Target)**  
   Se definió la interfaz CurrencyConverter, que representa la forma estándar en
   que el cliente realiza las conversiones de moneda.

2. **Servicio de Conversión Real (Adaptee)**  
   Se implementó la clase RealExchangeRateService, encargada de manejar las tasas
   reales de conversión utilizando una moneda base (USD).

3. **Adaptador (Adapter)**  
   La clase ExchangeAdapter implementa la interfaz CurrencyConverter y actúa como
   intermediario entre el cliente y el servicio real de tasas.  
   Esta clase traduce las solicitudes del cliente a llamadas compatibles con el
   servicio de conversión real.

4. **Cliente**  
   El cliente interactúa únicamente con la interfaz CurrencyConverter, sin conocer
   la implementación concreta ni la fuente de las tasas de cambio.

