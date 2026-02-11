package src.main.java.edu.dosw.lab.creacionales.reto1;

import java.util.Map;

/**
 * Clase ClienteAntiguoFactory
 *
 * Implementación concreta del patrón Factory Method.
 * Se encarga de crear el descuento correspondiente
 * para un cliente frecuente (antiguo).
 *
 * Extiende de la clase abstracta Cliente.
 */
public class ClienteAntiguoFactory extends Cliente {

    /**
     * Constructor que recibe el carrito de compras del cliente.
     *
     * @param carrito Mapa con productos y cantidades seleccionadas
     */
    public ClienteAntiguoFactory(Map<Producto, Integer> carrito) {
        super(carrito); // Llama al constructor de la clase padre
    }

    /**
     * Método Factory que define qué tipo de descuento
     * se aplicará al cliente frecuente.
     *
     * @return instancia de ClienteAntiguo (estrategia de descuento)
     */
    @Override
    protected Descuento crearDescuento() {
        return new ClienteAntiguo();
    }
}

