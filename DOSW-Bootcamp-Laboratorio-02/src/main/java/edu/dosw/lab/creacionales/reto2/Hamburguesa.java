package edu.dosw.lab.creacionales.reto2;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Hamburguesa que representa el Producto
 * en el patrón de diseño Builder.
 *
 * <p>
 * Contiene la lista de ingredientes que conforman
 * la hamburguesa personalizada.
 * </p>
 *
 * <p>
 * Esta clase no se encarga de su propia construcción
 * compleja, ya que esa responsabilidad recae en el Builder.
 * </p>
 *
 * <p>
 * Categoría del patrón: Creacional.
 * </p>
 *
 * @author Brayan
 */
public class Hamburguesa {

    /**
     * Lista de ingredientes que componen la hamburguesa.
     */
    private List<Ingrediente> ingredientes = new ArrayList<>();

    /**
     * Agrega un ingrediente a la hamburguesa.
     *
     * @param ingrediente Ingrediente que será añadido.
     */
    public void agregarIngrediente(Ingrediente ingrediente) {
        ingredientes.add(ingrediente);
    }

    /**
     * Retorna la lista de ingredientes de la hamburguesa.
     *
     * @return Lista de ingredientes agregados.
     */
    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    /**
     * Calcula el precio total de la hamburguesa utilizando Streams.
     *
     * <p>
     * Se suman los precios de todos los ingredientes
     * agregados a la hamburguesa.
     * </p>
     *
     * @return Precio total de la hamburguesa.
     */
    public double calcularPrecioTotal() {
        return ingredientes.stream()
                .mapToDouble(Ingrediente::getPrecio)
                .sum();
    }
}
