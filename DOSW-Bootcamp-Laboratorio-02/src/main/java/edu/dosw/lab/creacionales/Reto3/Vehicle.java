package edu.dosw.lab.creacionales.Reto3;

import java.math.BigDecimal;

public abstract class Vehicle {

    protected Category category;

    public Vehicle(Category category) {
        this.category = category;
    }

    public abstract String getType();
    public abstract int getMaxSpeed();


    public abstract BigDecimal getBasePrice();

    public abstract String getEquipment();

    public BigDecimal getFinalPrice() {
        return getBasePrice()
                .multiply(BigDecimal.valueOf(category.getFactor()));
    }
}
