package edu.dosw.lab.estructurales.reto5;

public class chocolate extends toppingDecorator {

    public chocolate(cafe cafe) {
        super(cafe);
    }

    @Override
    public String getDescripcion() {
        return cafe.getDescripcion() + " + Chocolate";
    }

    @Override
    public double getPrecio() {
        return cafe.getPrecio() + 1500;
    }
}
