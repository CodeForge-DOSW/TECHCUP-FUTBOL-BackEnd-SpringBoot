package src.main.java.edu.dosw.lab.creacionales.reto1;

/**
 * Interfaz Descuento
 * Define el comportamiento para aplicar un descuento
 * sobre un valor neto.
 *
 * Forma parte del principio de programación orientada a interfaces,
 * permitiendo diferentes estrategias de descuento.
 */
public interface Descuento {

    /**
     * Aplica un descuento sobre el precio neto recibido.
     *
     * @param precioNeto valor antes de aplicar descuento
     * @return valor final después de aplicar el descuento
     */
    double aplicarDescuento(double precioNeto);

}
