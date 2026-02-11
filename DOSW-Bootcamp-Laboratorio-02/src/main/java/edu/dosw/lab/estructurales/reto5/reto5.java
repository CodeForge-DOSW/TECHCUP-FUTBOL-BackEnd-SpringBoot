package edu.dosw.lab.estructurales.reto5;


public class reto5 {

    public static void run() {

        cafe miCafe = new cafeBasico();

        miCafe = new leche(miCafe);
        miCafe = new chocolate(miCafe);
        miCafe = new caramelo(miCafe);

        System.out.println("Descripción: " + miCafe.getDescripcion());
        System.out.println("Precio total: $" + miCafe.getPrecio());
    }
}

