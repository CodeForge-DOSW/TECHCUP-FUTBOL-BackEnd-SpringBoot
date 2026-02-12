package edu.dosw.lab.creacionales.reto1;

/**
 * Clase ClienteAntiguo
 *
 * Implementa la interfaz Descuento.
 * Define la estrategia de descuento para clientes frecuentes (antiguos).
 *
 * En este caso, el cliente frecuente recibe un 10% de descuento
 * sobre el precio neto.
 */
public class ClienteAntiguo implements Descuento {

    /**
     * Aplica un 10% de descuento sobre el precio neto.
     *
     * @param precioNeto valor antes del descuento
     * @return valor final con el descuento aplicado
     */
    @Override
    public double aplicarDescuento(double precioNeto) {
        return precioNeto * 0.9; // 10% de descuento
    }
}
