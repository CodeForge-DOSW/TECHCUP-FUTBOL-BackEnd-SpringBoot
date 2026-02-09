package edu.dosw.lab.creacionales.Reto3;

public class AirVehicleFactory implements VehicleFactory {

    @Override
    public Vehicle createVehicle(String model, Category category) {
        return switch (model.toLowerCase()) {
            case "avioneta" -> new Plane(category);
            default -> throw new IllegalArgumentException("Modelo no válido");
        };
    }
}
