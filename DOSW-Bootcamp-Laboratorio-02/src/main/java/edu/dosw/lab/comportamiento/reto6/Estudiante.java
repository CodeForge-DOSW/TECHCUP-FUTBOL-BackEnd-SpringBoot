package edu.dosw.lab.comportamiento.reto6;

public class Estudiante {

    public void enviarSolicitud(IAprobador aprobador, SolicitudPermiso solicitud) {
        aprobador.aprobar(solicitud);
    }
}

