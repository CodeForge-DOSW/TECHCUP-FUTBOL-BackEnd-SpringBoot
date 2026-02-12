package edu.dosw.lab.comportamiento.reto6;

public class reto6 {

    public static void ejecutar() {

        IAprobador profesor = new Profesor();
        IAprobador coordinador = new Coordinador();
        IAprobador decano = new Decano();

        profesor.setSiguiente(coordinador);
        coordinador.setSiguiente(decano);

        Estudiante estudiante = new Estudiante();

        SolicitudPermiso s1 = new SolicitudPermiso("Torneo de boxeo", 2);
        SolicitudPermiso s2 = new SolicitudPermiso("Evento académico", 5);
        SolicitudPermiso s3 = new SolicitudPermiso("Intercambio internacional", 8);
        SolicitudPermiso s4 = new SolicitudPermiso("Viaje largo", 15);

        estudiante.enviarSolicitud(profesor, s1);
        System.out.println();
        estudiante.enviarSolicitud(profesor, s2);
        System.out.println();
        estudiante.enviarSolicitud(profesor, s3);
        System.out.println();
        estudiante.enviarSolicitud(profesor, s4);
    }
}
