package src.main.java.edu.dosw.lab.creacionales.reto1;

import java.util.Map;

/**
 * Clase ClienteNuevoFactory
 *
 * Representa una implementación concreta del patrón Factory Method.
 * Se encarga de crear el tipo de descuento correspondiente
 * para un cliente nuevo.
 *
 * Extiende de la clase abstracta Cliente.
 */
public class ClienteNuevoFactory extends Cliente {

    /**
     * Constructor que recibe el carrito de compras del cliente.
     *
     * @param carrito Mapa con productos y cantidades seleccionadas
     */
    public ClienteNuevoFactory(Map<Producto, Integer> carrito) {
        super(carrito); // Llama al constructor de la clase padre (Cliente)
    }

    /**
     * Método Factory que crea el tipo de descuento
     * correspondiente a un cliente nuevo.
     *
     * @return instancia de descuento para cliente nuevo
     */
    @Override
    protected Descuento crearDescuento() {
        return new ClienteNuevo();
    }
}
