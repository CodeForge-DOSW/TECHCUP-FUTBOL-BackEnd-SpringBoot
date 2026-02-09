package edu.dosw.lab.creacionales.Reto3;

public enum Category {
    ECONOMICO(0.8),
    LUJO(1.5),
    USADO(0.6);

    private final double factor;

    Category(double factor) {
        this.factor = factor;
    }

    public double getFactor() {
        return factor;
    }
}

