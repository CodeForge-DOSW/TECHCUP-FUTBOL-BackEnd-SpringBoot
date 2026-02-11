package edu.dosw.lab.estructurales.reto5;

public class leche extends toppingDecorator {

    public leche(cafe cafe) {
        super(cafe);
    }

    @Override
    public String getDescripcion() {
        return cafe.getDescripcion() + " + Leche";
    }

    @Override
    public double getPrecio() {
        return cafe.getPrecio() + 1000;
    }
}
