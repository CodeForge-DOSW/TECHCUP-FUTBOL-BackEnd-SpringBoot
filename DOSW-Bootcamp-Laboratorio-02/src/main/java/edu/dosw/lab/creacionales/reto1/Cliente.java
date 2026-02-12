package edu.dosw.lab.creacionales.reto1;

import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase abstracta Cliente
 *
 * Representa el contexto principal del sistema.
 * Implementa el patrón Factory Method al definir el método
 * abstracto crearDescuento(), el cual será implementado
 * por las subclases.
 *
 * También trabaja junto al patrón Strategy, ya que utiliza
 * la interfaz Descuento para aplicar diferentes tipos de descuento.
 */
public abstract class Cliente {

    // Carrito de compras del cliente (Producto -> Cantidad)
    private Map<Producto, Integer> carrito;

    /**
     * Constructor del cliente
     *
     * @param carrito Mapa con productos y cantidades seleccionadas
     */
    public Cliente(Map<Producto, Integer> carrito){
        this.carrito = carrito;
    }

    /**
     * Método Factory (Factory Method).
     * Cada subclase decide qué tipo de descuento crear.
     *
     * @return una estrategia de descuento concreta
     */
    protected abstract Descuento crearDescuento();

    /**
     * Aplica el descuento correspondiente al subtotal.
     *
     * @param subTotal valor antes del descuento
     * @return total después de aplicar el descuento
     */
    public double descontar(double subTotal){
        Descuento d = crearDescuento(); // Se crea la estrategia concreta
        return d.aplicarDescuento(subTotal);
    }

    /**
     * Calcula el subtotal sumando el total de cada producto
     * (precio * cantidad).
     *
     * @return valor subtotal sin descuento
     */
    public double calcularSubTotal(){

        // Calcula el total por producto
        Map<Producto, Integer> totales = carrito.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getKey().getPrecio() * e.getValue()
                ));

        // Suma todos los valores calculados
        double subtotal = totales.values()
                .stream()
                .reduce(0, (a, b) -> a + b);

        return subtotal;
    }

    /**
     * Obtiene el carrito de compras del cliente
     *
     * @return mapa de productos y cantidades
     */
    public Map<Producto, Integer> getCarrito() {
        return carrito;
    }
}
