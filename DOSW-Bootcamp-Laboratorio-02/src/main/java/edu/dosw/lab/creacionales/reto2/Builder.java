package src.main.java.edu.dosw.lab.creacionales.reto2;

/**
 * Interfaz Builder del patrón de diseño Builder.
 *
 * <p>
 * Define los pasos necesarios para construir una Hamburguesa.
 * Permite separar el proceso de construcción del objeto final
 * de su representación concreta.
 * </p>
 *
 * <p>
 * Categoría del patrón: Creacional.
 * </p>
 *
 * <p>
 * Las clases concretas que implementen esta interfaz serán
 * responsables de construir paso a paso el objeto Hamburguesa.
 * </p>
 *
 * @author Brayan
 */
public interface Builder {

    /**
     * Reinicia el proceso de construcción.
     *
     * <p>
     * Crea una nueva instancia del producto (Hamburguesa)
     * para comenzar una nueva construcción.
     * </p>
     */
    void reset();

    /**
     * Agrega un ingrediente al producto en construcción.
     *
     * @param ingrediente Objeto Ingrediente que será añadido
     *                    a la hamburguesa.
     */
    void buildIngrediente(Ingrediente ingrediente);

    /**
     * Retorna el producto final construido.
     *
     * @return Hamburguesa completamente construida.
     */
    Hamburguesa getResult();
}
