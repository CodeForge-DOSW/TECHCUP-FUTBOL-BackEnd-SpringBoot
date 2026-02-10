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
### Reto X – Nombre del Reto
#### Evidencia del código solución implementado
#### Evidencia de la respuesta ejecutada
#### Lo solicitado en cada Reto

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
Se utilizó el patrón Adapter para permitir que distintos servicios de conversión
de moneda con interfaces incompatibles puedan ser utilizados de manera uniforme
por el sistema, sin modificar su implementación original.

### ¿Cómo se aplicó?
Se definió una interfaz común `CurrencyConverter` que es utilizada por el cliente.
El `ExchangeAdapter` adapta el servicio real de tasas de cambio a esta interfaz,
permitiendo convertir cualquier moneda a otra usando tasas reales.
