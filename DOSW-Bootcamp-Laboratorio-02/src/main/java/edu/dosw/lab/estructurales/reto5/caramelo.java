package edu.dosw.lab.estructurales.reto5;

public class caramelo extends toppingDecorator {

    public caramelo(cafe cafe) {
        super(cafe);
    }

    @Override
    public String getDescripcion() {
        return cafe.getDescripcion() + " + Caramelo";
    }

    @Override
    public double getPrecio() {
        return cafe.getPrecio() + 1200;
    }
}

