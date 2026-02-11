package main.java.edu.dosw.lab.comportamiento.reto6;

public interface IAprobador {

    void setSiguiente(IAprobador siguiente);

    void aprobar(SolicitudPermiso solicitud);
}

