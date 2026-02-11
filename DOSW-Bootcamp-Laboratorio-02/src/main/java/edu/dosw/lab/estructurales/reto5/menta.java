package edu.dosw.lab.estructurales.reto5;

public class menta extends toppingDecorator {

    public menta(cafe cafe) {
        super(cafe);
    }

    @Override
    public String getDescripcion() {
        return cafe.getDescripcion() + " + Menta";
    }

    @Override
    public double getPrecio() {
        return cafe.getPrecio() + 1300;
    }
}
