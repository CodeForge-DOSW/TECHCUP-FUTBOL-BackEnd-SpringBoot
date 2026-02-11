package src.main.java.edu.dosw.lab.creacionales.reto2;

import java.util.List;

/**
 * Clase Chef que actúa como Director en el patrón de diseño Builder.
 *
 * <p>
 * Su responsabilidad es coordinar el proceso de construcción
 * de la Hamburguesa utilizando un objeto Builder.
 * </p>
 *
 * <p>
 * El Chef no conoce los detalles internos de cómo se construye
 * la hamburguesa, solo ejecuta los pasos definidos en la interfaz Builder.
 * </p>
 *
 * <p>
 * Categoría del patrón: Creacional.
 * </p>
 *
 * @author Brayan
 */
public class Chef {

    /**
     * Referencia al Builder que se utilizará para construir la hamburguesa.
     */
    private Builder builder;

    /**
     * Constructor del Director (Chef).
     *
     * @param builder Implementación concreta del Builder que se usará
     *                para construir el producto.
     */
    public Chef(Builder builder) {
        this.builder = builder;
    }

    /**
     * Permite cambiar dinámicamente el Builder.
     *
     * <p>
     * Esto hace que el Director pueda construir diferentes
     * tipos de productos usando distintos Builders.
     * </p>
     *
     * @param builder Nueva implementación de Builder.
     */
    public void cambiarBuilder(Builder builder) {
        this.builder = builder;
    }

    /**
     * Coordina el proceso de construcción de la hamburguesa.
     *
     * <p>
     * Primero reinicia el Builder y luego agrega cada ingrediente
     * recibido en la lista.
     * </p>
     *
     * @param ingredientes Lista de ingredientes seleccionados
     *                     para construir la hamburguesa.
     */
    public void hacerHamburguesa(List<Ingrediente> ingredientes) {
        builder.reset();
        for (Ingrediente i : ingredientes) {
            builder.buildIngrediente(i);
        }
    }
}
