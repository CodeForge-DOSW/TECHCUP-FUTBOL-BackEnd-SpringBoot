package edu.dosw.lab;

import src.main.java.edu.dosw.lab.creacionales.reto1.Reto1;
import src.main.java.edu.dosw.lab.creacionales.reto2.Reto2;
import edu.dosw.lab.estructurales.reto5.reto5;
import main.java.edu.dosw.lab.comportamiento.reto6.reto6;

public class Application {
    public static void main(String[] args) {
        System.out.println("✅ Proyecto Maven configurado y corriendo correctamente");

        System.out.println("RETO #1: El problema de la tienda de Don Pepe");
        Reto1.main(args);

        System.out.println("RETO #2: El chef de 5 estrellas");
        Reto2.main(args);

        System.out.println("\nRETO #5: El Café Personalizado\n");
        reto5.run();

        System.out.println("\nRETO #6: Habla con Soporte Técnico\n");
        reto6.ejecutar();
    }
}
