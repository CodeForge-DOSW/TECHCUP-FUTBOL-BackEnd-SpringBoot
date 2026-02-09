package edu.dosw.lab.creacionales.Reto3;

public class WaterVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String model, Category category) {

        return switch (model.toLowerCase()) {
            case "lancha" -> new Boat(category);
            default -> throw new IllegalArgumentException("Modelo acuático no válido");
        };
    }
}

