package edu.dosw.lab.creacionales.Reto3;

import java.math.BigDecimal;
import java.util.List;

public class CashRegister {

    public static BigDecimal calculateTotal(List<Vehicle> vehicles) {
        return vehicles.stream()
                .map(Vehicle::getFinalPrice)   // BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}