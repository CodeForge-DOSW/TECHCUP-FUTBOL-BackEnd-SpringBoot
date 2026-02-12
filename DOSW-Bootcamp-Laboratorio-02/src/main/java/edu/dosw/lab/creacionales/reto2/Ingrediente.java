package edu.dosw.lab.creacionales.reto2;

/**
 * Clase Ingrediente que representa un componente
 * individual de la Hamburguesa.
 *
 * <p>
 * Cada ingrediente posee un nombre y un precio,
 * los cuales serán utilizados durante la construcción
 * del producto final mediante el patrón Builder.
 * </p>
 *
 * <p>
 * Esta clase forma parte del modelo del dominio
 * del problema.
 * </p>
 *
 * @author Brayan
 */
public class Ingrediente {

    /**
     * Nombre del ingrediente.
     */
    private String nombre;

    /**
     * Precio del ingrediente.
     */
    private double precio;

    /**
     * Constructor de la clase Ingrediente.
     *
     * @param nombre Nombre del ingrediente.
     * @param precio Precio asociado al ingrediente.
     */
    public Ingrediente(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    /**
     * Obtiene el nombre del ingrediente.
     *
     * @return Nombre del ingrediente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Obtiene el precio del ingrediente.
     *
     * @return Precio del ingrediente.
     */
    public double getPrecio() {
        return precio;
    }
}
