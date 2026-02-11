package src.main.java.edu.dosw.lab.creacionales.reto1;


import java.util.List;
import java.util.Map;

public class Reto1 {

    public static void main(String[] args) {

        System.out.println("RETO #1: El problema de la tienda de Don Pepe\n");

        List<Producto> catalogo = List.of(
                new Producto("Laptop", 1200),
                new Producto("Mouse", 25),
                new Producto("Teclado", 75),
                new Producto("Monitor", 400)
        );

        Menu menu = new Menu(catalogo);
        String tipoCliente = menu.tipoCliente();
        menu.mostrarProductos();
        Map<Producto, Integer> productosEscogidos = menu.escogerProductos();
        menu.mostrarEscogidos(productosEscogidos);
        Cliente cliente = menu.crearCliente(tipoCliente, productosEscogidos);
        double subTotal = menu.calculoSubTotal(cliente);
        double total = menu.calculoTotal(cliente, subTotal);
        menu.mostrarInformacion(tipoCliente, cliente, subTotal, total);




    }
}

