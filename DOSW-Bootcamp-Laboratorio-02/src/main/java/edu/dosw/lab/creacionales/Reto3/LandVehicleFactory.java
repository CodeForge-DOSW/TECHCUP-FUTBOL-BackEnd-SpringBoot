package edu.dosw.lab.creacionales.Reto3;

public class LandVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String model, Category category) {
        return switch (model.toLowerCase()) {
            case "auto" -> new Car(category);
            default -> throw new IllegalArgumentException("Modelo no válido");
        };
    }
}

