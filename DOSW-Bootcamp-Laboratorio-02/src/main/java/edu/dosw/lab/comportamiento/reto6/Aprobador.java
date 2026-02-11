package main.java.edu.dosw.lab.comportamiento.reto6;

public abstract class Aprobador implements IAprobador {

    protected IAprobador siguiente;

    @Override
    public void setSiguiente(IAprobador siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public void aprobar(SolicitudPermiso solicitud) {
        if (siguiente != null) {
            siguiente.aprobar(solicitud);
        } else {
            System.out.println("Nadie pudo aprobar la solicitud. Escalamiento requerido.");
        }
    }
}

