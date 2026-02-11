package edu.dosw.lab.estructurales.reto5;

public class cremaBatida extends toppingDecorator {

    public cremaBatida(cafe cafe) {
        super(cafe);
    }

    @Override
    public String getDescripcion() {
        return cafe.getDescripcion() + " + Crema Batida";
    }

    @Override
    public double getPrecio() {
        return cafe.getPrecio() + 1800;
    }
}
