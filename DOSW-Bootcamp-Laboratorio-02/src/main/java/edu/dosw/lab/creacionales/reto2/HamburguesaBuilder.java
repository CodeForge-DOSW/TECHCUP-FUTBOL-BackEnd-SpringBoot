package src.main.java.edu.dosw.lab.creacionales.reto2;

/**
 * Clase HamburguesaBuilder que actúa como ConcreteBuilder
 * en el patrón de diseño Builder.
 *
 * <p>
 * Implementa la interfaz Builder y se encarga de construir
 * paso a paso el objeto Hamburguesa.
 * </p>
 *
 * <p>
 * Esta clase contiene la lógica específica para agregar
 * ingredientes al producto en construcción.
 * </p>
 *
 * <p>
 * Categoría del patrón: Creacional.
 * </p>
 *
 * @author Brayan
 */
public class HamburguesaBuilder implements Builder {

    /**
     * Producto que se está construyendo.
     */
    private Hamburguesa hamburguesa;

    /**
     * Constructor del ConcreteBuilder.
     *
     * <p>
     * Inicializa el proceso de construcción
     * creando una nueva instancia del producto.
     * </p>
     */
    public HamburguesaBuilder() {
        reset();
    }

    /**
     * Reinicia el proceso de construcción.
     *
     * <p>
     * Se crea una nueva instancia de Hamburguesa
     * para comenzar desde cero.
     * </p>
     */
    @Override
    public void reset() {
        hamburguesa = new Hamburguesa();
    }

    /**
     * Agrega un ingrediente al producto en construcción.
     *
     * @param ingrediente Ingrediente que será añadido
     *                    a la hamburguesa.
     */
    @Override
    public void buildIngrediente(Ingrediente ingrediente) {
        hamburguesa.agregarIngrediente(ingrediente);
    }

    /**
     * Devuelve el producto final construido.
     *
     * @return Hamburguesa completamente construida.
     */
    @Override
    public Hamburguesa getResult() {
        return hamburguesa;
    }
}
