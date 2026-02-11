package src.main.java.edu.dosw.lab.creacionales.reto1;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Clase Menu
 * Se encarga de la interacción con el usuario.
 * Permite mostrar productos, capturar selección,
 * crear el tipo de cliente y mostrar el recibo final.
 */
public class Menu {

    // Scanner para capturar datos ingresados por el usuario
    private Scanner scanner = new Scanner(System.in);

    // Lista de productos disponibles en la tienda
    private List<Producto> productosDisponibles;

    /**
     * Constructor del menú
     * @param productosDisponibles lista de productos que se mostrarán al usuario
     */
    public Menu(List<Producto> productosDisponibles){
        this.productosDisponibles = productosDisponibles;
    }

    /**
     * Solicita al usuario el tipo de cliente
     * @return String con el tipo de cliente (frecuente o nuevo)
     */
    public String tipoCliente() {
        System.out.println("Cliente (frecuente o nuevo): ");
        return scanner.nextLine();
    }

    /**
     * Crea el cliente usando el patrón Factory
     * Dependiendo del tipo retorna una fábrica diferente
     *
     * @param tipo tipo de cliente (frecuente o nuevo)
     * @param productosEscogidos mapa con productos y cantidades
     * @return instancia de Cliente correspondiente
     */
    public Cliente crearCliente(String tipo, Map<Producto, Integer> productosEscogidos){
        return "frecuente".equals(tipo)
                ? new ClienteAntiguoFactory(productosEscogidos)
                : new ClienteNuevoFactory(productosEscogidos);
    }

    /**
     * Muestra en consola la lista de productos disponibles
     */
    public void mostrarProductos(){
        System.out.println("Productos disponibles:\n");

        productosDisponibles.stream().forEach(producto ->
                System.out.println(
                        producto.getName() + " - " + "$" + producto.getPrecio() + "\n"
                )
        );
    }

    /**
     * Permite al usuario ingresar la cantidad deseada
     * para cada producto disponible.
     *
     * @return Mapa con producto como clave y cantidad como valor
     */
    public Map<Producto, Integer> escogerProductos() {
        System.out.println("Ingrese las cantidades para cada producto:");

        return productosDisponibles.stream()
                .collect(Collectors.toMap(
                        producto -> producto,
                        producto -> {
                            System.out.print(producto.getName() + ": ");
                            return scanner.nextInt(); // Captura cantidad ingresada
                        }
                ));
    }

    /**
     * Muestra los productos seleccionados por el usuario
     * @param productosEscogidos mapa de productos y cantidades
     */
    public void mostrarEscogidos(Map<Producto, Integer> productosEscogidos){
        String reporte = productosEscogidos.entrySet().stream()
                .map(p -> p.getKey().getName() + " " + p.getValue() + " Unidades agregadas")
                .collect(Collectors.joining("\n"));

        System.out.println("Escogido\n" + reporte);
    }

    /**
     * Calcula el subtotal llamando al método del cliente
     * @param cliente cliente creado
     * @return valor del subtotal
     */
    public double calculoSubTotal(Cliente cliente){
        return cliente.calcularSubTotal();
    }

    /**
     * Calcula el total aplicando descuento según tipo de cliente
     * @param cliente cliente creado
     * @param subTotal valor sin descuento
     * @return total con descuento aplicado
     */
    public double calculoTotal(Cliente cliente, double subTotal){
        return cliente.descontar(subTotal);
    }

    /**
     * Muestra el recibo final de la compra
     *
     * @param tipo tipo de cliente
     * @param cliente cliente con su carrito
     * @param subTotal valor antes del descuento
     * @param total valor final a pagar
     */
    public void mostrarInformacion(String tipo, Cliente cliente, double subTotal, double total){
        System.out.println("--- RECIBO DE COMPRA ---");
        System.out.println("Cliente: " + tipo);
        System.out.println("Productos:");

        // Genera el detalle del ticket usando Stream
        String ticket = cliente.getCarrito().entrySet().stream()
                .map(p -> p.getKey().getName() +
                        " - Cantidad: " + p.getValue() +
                        " - Total: $" + (p.getKey().getPrecio() * p.getValue()))
                .collect(Collectors.joining("\n"));

        System.out.println(ticket);
        System.out.println("Subtotal: " + subTotal);
        System.out.println("Descuento aplicado: " + (subTotal - total));
        System.out.println("Total a pagar: " + total);
        System.out.println("------------------------\n");
        System.out.println("¡Gracias por su compra!");
    }
}
