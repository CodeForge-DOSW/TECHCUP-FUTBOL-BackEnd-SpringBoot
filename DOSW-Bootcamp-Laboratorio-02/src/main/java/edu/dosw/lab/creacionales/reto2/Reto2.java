package edu.dosw.lab.creacionales.reto2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reto2 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Ingrediente> catalogo = new ArrayList<>(List.of(
                new Ingrediente("Pan", 3000),
                new Ingrediente("Carne", 10000),
                new Ingrediente("Queso", 5000),
                new Ingrediente("Lechuga", 2000),
                new Ingrediente("Tomate", 2000),
                new Ingrediente("Salsa especial", 3000)
        ));

        System.out.println("Seleccione ingredientes para su hamburguesa:");

        for (int i = 0; i < catalogo.size(); i++) {
            System.out.println((i + 1) + ". " +
                    catalogo.get(i).getNombre() +
                    " ($" + catalogo.get(i).getPrecio() + ")");
        }

        System.out.println((catalogo.size() + 1) + ". Agregar nuevo ingrediente");

        System.out.println("Ingrese números separados por coma:");
        String input = scanner.nextLine();

        List<Ingrediente> seleccionados = new ArrayList<>();

        for (String s : input.split(",")) {

            int opcion = Integer.parseInt(s.trim());

            if (opcion >= 1 && opcion <= catalogo.size()) {
                seleccionados.add(catalogo.get(opcion - 1));
            }

            else if (opcion == catalogo.size() + 1) {

                System.out.println("Ingrese el nombre del nuevo ingrediente:");
                String nombre = scanner.nextLine();

                System.out.println("Ingrese el precio del ingrediente:");
                double precio = Double.parseDouble(scanner.nextLine());

                Ingrediente nuevo = new Ingrediente(nombre, precio);

                catalogo.add(nuevo);

                seleccionados.add(nuevo);

                System.out.println("Ingrediente agregado correctamente.");
            }

            else {
                System.out.println("Opción inválida: " + opcion);
            }
        }

        Builder builder = new HamburguesaBuilder();
        Chef chef = new Chef(builder);

        chef.hacerHamburguesa(seleccionados);

        Hamburguesa hamburguesa = builder.getResult();

        System.out.println("\n--- HAMBURGUESA PERSONALIZADA ---");

        hamburguesa.getIngredientes()
                .stream()
                .map(Ingrediente::getNombre)
                .forEach(nombre -> System.out.println("- " + nombre));

        System.out.println("Precio total: $" +
                hamburguesa.calcularPrecioTotal());

        System.out.println("¡Disfrute su hamburguesa!");
    }

    public static void ejecutar() {
        main(new String[0]);
    }
}
