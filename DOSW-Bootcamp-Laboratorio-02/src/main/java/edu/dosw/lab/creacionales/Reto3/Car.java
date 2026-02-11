package edu.dosw.lab.creacionales.Reto3;

import java.math.BigDecimal;

public class Car extends Vehicle {

    public Car(Category category) {
        super(category);
    }

    @Override
    public String getType() {
        return "Auto";
    }

    @Override
    public int getMaxSpeed() {
        return category == Category.LUJO ? 180 : 140;
    }

    @Override
    public BigDecimal getBasePrice() {
        return new BigDecimal("500000000");
    }

    @Override
    public String getEquipment() {
        return category == Category.LUJO ?
                "Aire acondicionado + GPS" :
                "Equipamiento básico";
    }
}
