package src.main.java.edu.dosw.lab.creacionales.reto1;

/**
 * Clase Producto
 * Representa un producto disponible en la tienda.
 * Contiene su nombre y precio.
 */
public class Producto {

    // Nombre del producto
    private String nombre;

    // Precio del producto
    private int precio;

    /**
     * Constructor de la clase Producto
     *
     * @param name   Nombre del producto
     * @param precio Precio del producto
     */
    public Producto(String name, int precio){
        this.nombre = name;
        this.precio = precio;
    }

    /**
     * Obtiene el nombre del producto
     *
     * @return nombre del producto
     */
    public String getName(){
        return this.nombre;
    }

    /**
     * Obtiene el precio del producto
     *
     * @return precio del producto
     */
    public int getPrecio(){
        return precio;
    }

}
