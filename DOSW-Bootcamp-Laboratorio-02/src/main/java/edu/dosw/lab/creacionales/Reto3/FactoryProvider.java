package edu.dosw.lab.creacionales.Reto3;

public class FactoryProvider {

    public static VehicleFactory getFactory(int option) {
        return switch (option) {
            case 1 -> new LandVehicleFactory();
            case 2 -> new WaterVehicleFactory();
            case 3 -> new AirVehicleFactory();
            default -> throw new IllegalArgumentException("Opción no válida");
        };
    }

}
