package src.main.java.edu.dosw.lab.creacionales.reto1;

/**
 * Clase ClienteNuevo
 *
 * Implementa la interfaz Descuento.
 * Define la estrategia de descuento para clientes nuevos.
 *
 * En este caso, el cliente nuevo recibe un 5% de descuento
 * sobre el precio neto.
 */
public class ClienteNuevo implements Descuento {

    /**
     * Aplica un 5% de descuento sobre el precio neto.
     *
     * @param precioNeto valor antes del descuento
     * @return valor final con el descuento aplicado
     */
    @Override
    public double aplicarDescuento(double precioNeto) {
        return precioNeto * 0.95; // 5% de descuento
    }
}
