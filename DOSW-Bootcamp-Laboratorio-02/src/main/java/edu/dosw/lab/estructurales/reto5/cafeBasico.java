package edu.dosw.lab.estructurales.reto5;

public class cafeBasico implements cafe {

    @Override
    public String getDescripcion() {
        return "Café base";
    }

    @Override
    public double getPrecio() {
        return 5000;
    }
}

