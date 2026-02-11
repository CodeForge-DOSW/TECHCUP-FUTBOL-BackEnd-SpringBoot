package edu.dosw.lab.creacionales.Reto3;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reto3 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Vehicle> cart = new ArrayList<>();
        String continuar;

        System.out.println("¡Bienvenido al Reino de los Vehículos!");

        do {

            System.out.println("\nSeleccione el tipo de vehículo:");
            System.out.println("1. Tierra");
            System.out.println("2. Acuático");
            System.out.println("3. Aéreo");
            System.out.print("Ingrese opción: ");
            int tipo = scanner.nextInt();

            VehicleFactory factory = FactoryProvider.getFactory(tipo);

            System.out.println("\nSeleccione la categoría del vehículo:");
            System.out.println("1. Económico");
            System.out.println("2. Lujo");
            System.out.println("3. Usado");
            System.out.print("Ingrese opción: ");
            int catOption = scanner.nextInt();

            Category category = switch (catOption) {
                case 1 -> Category.ECONOMICO;
                case 2 -> Category.LUJO;
                case 3 -> Category.USADO;
                default -> throw new IllegalArgumentException("Categoría inválida");
            };

            System.out.println("\nSeleccione el modelo de vehículo:");
            if (tipo == 1) {
                System.out.println("1. Auto");
                System.out.println("2. Bicicleta");
                System.out.println("3. Moto");
            } else if (tipo == 3) {
                System.out.println("1. Avión");
                System.out.println("2. Avioneta");
                System.out.println("3. Helicóptero");
            }

            System.out.print("Ingrese opción: ");
            int modelOption = scanner.nextInt();

            String model = switch (tipo) {
                case 1 -> modelOption == 1 ? "auto"
                        : modelOption == 2 ? "bicicleta"
                        : "moto";
                case 3 -> modelOption == 1 ? "avion"
                        : modelOption == 2 ? "avioneta"
                        : "helicoptero";
                default -> throw new IllegalArgumentException("Modelo inválido");
            };

            Vehicle vehicle = factory.createVehicle(model, category);
            cart.add(vehicle);

            System.out.print("\n¿Desea agregar otro vehículo? (si/no): ");
            continuar = scanner.next();

        } while (continuar.equalsIgnoreCase("si"));

        System.out.println("\n=== RESUMEN DE COMPRA ===");

        cart.forEach(v -> {
            System.out.println("\nTipo: " + v.getType());
            System.out.println("Categoría: " + v.category);
            System.out.println("Velocidad máxima: " + v.getMaxSpeed() + " km/h");
            System.out.println("Precio: $" + v.getFinalPrice());
            System.out.println("Equipamiento: " + v.getEquipment());
        });


        BigDecimal total = cart.stream()
                .map(Vehicle::getFinalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nTotal a pagar: $" + total);
        System.out.println("\n¡Gracias por su compra en el Reino de los Vehículos!");
    }
}
