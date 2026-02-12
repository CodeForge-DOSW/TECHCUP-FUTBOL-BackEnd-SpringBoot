package edu.dosw.lab.comportamiento.reto6;

public class Decano extends Aprobador {

    @Override
    public void aprobar(SolicitudPermiso solicitud) {
        if (solicitud.getDias() <= 10) {
            System.out.println("Decano aprobó la solicitud por "
                    + solicitud.getDias() + " días. Motivo: "
                    + solicitud.getMotivo());
        } else {
            System.out.println("Decano no puede aprobar. Solicitud rechazada.");
        }
    }
}

