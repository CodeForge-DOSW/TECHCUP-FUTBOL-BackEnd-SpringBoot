package edu.dosw.lab.creacionales.Reto3;

import java.math.BigDecimal;

public class Plane extends Vehicle {

    public Plane(Category category) {
        super(category);
    }

    @Override
    public String getType() {
        return "Avioneta";
    }

    @Override
    public int getMaxSpeed() {
        return 250;
    }

    @Override
    public BigDecimal getBasePrice() {
        return new BigDecimal ("150000000");
    }

    @Override
    public String getEquipment() {
        return "Instrumentos básicos de vuelo";
    }
}