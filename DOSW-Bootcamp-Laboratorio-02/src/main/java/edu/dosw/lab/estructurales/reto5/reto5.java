package edu.dosw.lab.estructurales.reto5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class reto5 {

    public static void ejecutar() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Número de cafés a personalizar: ");
        int cantidad;
        while (true) {
            String line = scanner.nextLine();
            try {
                cantidad = Integer.parseInt(line.trim());
                if (cantidad <= 0) {
                    System.out.println("Por favor ingrese un número mayor a 0:");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entero:");
            }
        }

        List<String> descripciones = new ArrayList<>();
        List<Double> precios = new ArrayList<>();

        for (int i = 1; i <= cantidad; i++) {
            System.out.println();
            System.out.println("--- Café " + i + " ---");

            cafe miCafe = new cafeBasico();

            String linea = scanner.nextLine();
            if (linea != null && !linea.trim().isEmpty()) {
                String[] opciones = linea.split(",");
                for (String opt : opciones) {
                    String t = opt.trim();
                    if (t.isEmpty()) continue;
                    int opcion;
                    try {
                        opcion = Integer.parseInt(t);
                    } catch (NumberFormatException e) {
                        System.out.println("Opción inválida: '" + t + "' — se ignora.");
                        continue;
                    }

                    switch (opcion) {
                        case 1:
                            miCafe = new leche(miCafe);
                            break;
                        case 2:
                            miCafe = new chocolate(miCafe);
                            break;
                        case 3:
                            miCafe = new caramelo(miCafe);
                            break;
                        case 4:
                            miCafe = new cremaBatida(miCafe);
                            break;
                        case 5:
                            miCafe = new menta(miCafe);
                            System.out.println("Se agregó: Menta");
                            break;
                        case 6:
                            System.out.print("Ingrese nombre del nuevo topping: ");
                            String nombre = scanner.nextLine().trim();
                            if (nombre.isEmpty()) {
                                System.out.println("Nombre vacío. Se omite el topping nuevo.");
                                break;
                            }
                            System.out.print("Ingrese precio del topping: ");
                            String precioStr = scanner.nextLine().trim();
                            if (precioStr.isEmpty()) {
                                System.out.println("Precio vacío. Se omite el topping nuevo.");
                                break;
                            }

                            String sanitized = precioStr.replace(".", "").replace(",", "").replace("$", "").trim();
                            long precioVal;
                            try {
                                precioVal = Long.parseLong(sanitized);
                            } catch (NumberFormatException e) {
                                System.out.println("Precio inválido. Se omite el topping nuevo.");
                                break;
                            }

                            final String nombreFinal = nombre;
                            final double precioFinal = precioVal;

                            miCafe = new toppingDecorator(miCafe) {
                                @Override
                                public String getDescripcion() {
                                    return cafe.getDescripcion() + " + " + nombreFinal;
                                }

                                @Override
                                public double getPrecio() {
                                    return cafe.getPrecio() + precioFinal;
                                }
                            };
                    }
                }
            }

            descripciones.add(miCafe.getDescripcion());
            precios.add(miCafe.getPrecio());
        }

        System.out.println();
        System.out.println("--- RESUMEN DE CAFÉ PERSONALIZADO ---");
        long totalFinal = 0;

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,###", symbols);

        for (int i = 0; i < descripciones.size(); i++) {
            System.out.println();
            System.out.println("Café " + (i + 1) + ":");
            System.out.println("Ingredientes: " + descripciones.get(i));
            long precioCafe = Math.round(precios.get(i));
            System.out.println("Precio total: $" + df.format(precioCafe));
            totalFinal += precioCafe;
        }

        System.out.println();
        System.out.println("-------------------------------");
        System.out.println("Total a pagar por todos los cafés: $" + df.format(totalFinal));
        System.out.println("¡Disfrute su café!");
    }
}
