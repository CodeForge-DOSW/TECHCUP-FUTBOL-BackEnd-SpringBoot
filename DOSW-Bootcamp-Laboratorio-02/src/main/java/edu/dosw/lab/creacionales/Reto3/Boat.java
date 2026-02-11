package edu.dosw.lab.creacionales.Reto3;

import java.math.BigDecimal;

public class Boat extends Vehicle {

    public Boat(Category category) {
        super(category);
    }

    @Override
    public String getType() {
        return "Lancha";
    }

    @Override
    public int getMaxSpeed() {
        return 90;
    }

    @Override
    public BigDecimal getBasePrice() {
        return new BigDecimal("300000000");
    }

    @Override
    public String getEquipment() {
        return category == Category.LUJO
                ? "Asientos premium + GPS náutico"
                : "Equipamiento básico";
    }
}

