package edu.dosw.lab.comportamiento.Reto7;

import java.util.Scanner;

public class Reto7 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Luz luz = new Luz();
        Puerta puerta = new Puerta();
        Musica musica = new Musica();
        Volumen volumen = new Volumen();

        ControlRemoto control = new ControlRemoto();

        System.out.print("Número de acciones a registrar: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= n; i++) {

            System.out.println("\nAcción " + i);
            System.out.print("Usuario: ");
            String user = scanner.nextLine();

            System.out.println("1. Encender luz");
            System.out.println("2. Abrir puerta");
            System.out.println("3. Reproducir música");
            System.out.println("4. Ajustar volumen");

            System.out.print("Seleccione: ");
            int opcion = scanner.nextInt();

            Command command = null;

            switch (opcion) {
                case 1:
                    command = new EncenderLuzCommand(luz, user);
                    break;
                case 2:
                    command = new AbrirPuertaCommand(puerta, user);
                    break;
                case 3:
                    command = new ReproducirMusicaCommand(musica, user);
                    break;
                case 4:
                    System.out.print("Ingrese volumen (0-100): ");
                    int nivel = scanner.nextInt();
                    command = new AjustarVolumenCommand(volumen, user, nivel);
                    break;
            }

            control.ejecutar(command);

            System.out.print("¿Deshacer acción? (si/no): ");
            scanner.nextLine();
            String deshacer = scanner.nextLine();

            if (deshacer.equalsIgnoreCase("si")) {
                control.deshacer(command);
            }
        }

        control.mostrarHistorial();
        control.investigar();
    }
}
