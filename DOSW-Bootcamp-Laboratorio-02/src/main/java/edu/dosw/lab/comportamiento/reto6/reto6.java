package edu.dosw.lab.comportamiento.reto6;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class reto6 {

    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Número de tickets: ");
        int n;
        while (true) {
            String line = scanner.nextLine();
            try {
                n = Integer.parseInt(line.trim());
                if (n <= 0) {
                    System.out.println("Por favor ingrese un número mayor a 0:");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Ingrese un número entero:");
            }
        }

        List<String> resultados = new ArrayList<>();
        int resueltoBasico = 0;
        int resueltoIntermedio = 0;
        int resueltoAvanzado = 0;
        int pendientes = 0;

        int sumaPrioridadResueltos = 0; // baja=1, media=2, alta=3
        int contadorResueltos = 0;

        for (int i = 1; i <= n; i++) {
            System.out.println();
            System.out.println("Ticket " + i + ":");

            // Nivel
            String nivel;
            while (true) {
                System.out.print("Nivel: ");
                nivel = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                if (nivel.equals("basico") || nivel.equals("básico") || nivel.equals("intermedio") || nivel.equals("avanzado")) {
                    if (nivel.equals("básico")) nivel = "basico";
                    break;
                }
                System.out.println("Nivel inválido. Ingrese 'basico', 'intermedio' o 'avanzado'.");
            }

            // Prioridad
            String prioridad;
            while (true) {
                System.out.print("Prioridad: ");
                prioridad = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
                if (prioridad.equals("baja") || prioridad.equals("media") || prioridad.equals("alta")) {
                    break;
                }
                System.out.println("Prioridad inválida. Ingrese 'baja', 'media' o 'alta'.");
            }

            // Descripción
            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();

            String mensaje = "";
            boolean resuelto = false;

            // Reglas: Básico solo puede ser resuelto por Técnico Básico y solo si prioridad == baja.
            // Intermedio: resuelve si prioridad baja/media; si prioridad alta se escala a Avanzado.
            // Avanzado: resuelve cualquier prioridad en nivel avanzado.

            if (nivel.equals("basico")) {
                if (prioridad.equals("baja")) {
                    mensaje = "Técnico Básico resolvió el problema.";
                    resueltoBasico++;
                    resuelto = true;
                } else {
                    mensaje = "Ningún técnico disponible. Ticket pendiente de escalamiento.";
                    pendientes++;
                }
            } else if (nivel.equals("intermedio")) {
                if (prioridad.equals("baja") || prioridad.equals("media")) {
                    mensaje = "Técnico Intermedio resolvió el problema.";
                    resueltoIntermedio++;
                    resuelto = true;
                } else {
                    mensaje = "Técnico Intermedio no pudo resolver. Técnico Avanzado resolvió el problema.";
                    resueltoAvanzado++;
                    resuelto = true;
                }
            } else { // avanzado
                mensaje = "Técnico Avanzado resolvió el problema.";
                resueltoAvanzado++;
                resuelto = true;
            }

            if (resuelto) {
                sumaPrioridadResueltos += prioridadToNumber(prioridad);
                contadorResueltos++;
            }

            resultados.add("Ticket " + i + ": " + mensaje);
        }

        // Imprimir resultados
        System.out.println();
        for (String r : resultados) {
            System.out.println(r);
            System.out.println();
        }

        // Estadísticas
        System.out.println("--- Estadísticas ---");
        System.out.println("Tickets resueltos:");
        System.out.println("Básico: " + resueltoBasico);
        System.out.println("Intermedio: " + resueltoIntermedio);
        System.out.println("Avanzado: " + resueltoAvanzado);
        System.out.println("Tickets pendientes: " + pendientes);

        double promedio = 0.0;
        if (contadorResueltos > 0) {
            promedio = (double) sumaPrioridadResueltos / contadorResueltos;
        }
        System.out.println("Promedio de prioridad de tickets resueltos: " + String.format(Locale.ROOT, "%.1f", promedio));

        // No cerramos scanner (System.in)
    }

    private static int prioridadToNumber(String prioridad) {
        if (prioridad == null) return 0;
        prioridad = prioridad.toLowerCase(Locale.ROOT);
        switch (prioridad) {
            case "baja": return 1;
            case "media": return 2;
            case "alta": return 3;
            default: return 0;
        }
    }

    // Agrego main para que la lógica viva en esta clase y RunnerReto6 pueda eliminarse si lo deseas.
    public static void main(String[] args) {
        ejecutar();
    }
}
