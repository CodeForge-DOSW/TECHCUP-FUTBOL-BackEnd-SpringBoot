package edu.dosw.lab.comportamiento.reto6;

public class Coordinador extends Aprobador {

    @Override
    public void aprobar(SolicitudPermiso solicitud) {
        if (solicitud.getDias() <= 5) {
            System.out.println("Coordinador aprobó la solicitud por "
                    + solicitud.getDias() + " días. Motivo: "
                    + solicitud.getMotivo());
        } else {
            System.out.println("Coordinador no puede aprobar. Escala al Decano.");
            super.aprobar(solicitud);
        }
    }
}
