package main.java.edu.dosw.lab.comportamiento.reto6;

public class Profesor extends Aprobador {

    @Override
    public void aprobar(SolicitudPermiso solicitud) {
        if (solicitud.getDias() <= 2) {
            System.out.println("Profesor aprobó la solicitud por "
                    + solicitud.getDias() + " días. Motivo: "
                    + solicitud.getMotivo());
        } else {
            System.out.println("Profesor no puede aprobar. Escala al Coordinador.");
            super.aprobar(solicitud);
        }
    }
}

