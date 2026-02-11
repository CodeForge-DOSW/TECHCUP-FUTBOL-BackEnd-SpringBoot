package edu.dosw.lab.estructurales.reto5;

public abstract class toppingDecorator implements cafe {

    protected cafe cafe;

    public toppingDecorator(cafe cafe) {
        this.cafe = cafe;
    }

    @Override
    public String getDescripcion() {
        return cafe.getDescripcion();
    }

    @Override
    public double getPrecio() {
        return cafe.getPrecio();
    }
}
