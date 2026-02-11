package main.java.edu.dosw.lab.comportamiento.reto6;

public class SolicitudPermiso {

    private final String motivo;
    private final int dias;

    public SolicitudPermiso(String motivo, int dias) {
        this.motivo = motivo;
        this.dias = dias;
    }

    public String getMotivo() {
        return motivo;
    }

    public int getDias() {
        return dias;
    }
}

