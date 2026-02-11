package edu.dosw.lab.comportamiento.reto7;

public class AbrirPuertaCommand implements Command {

    private Puerta puerta;
    private String user;

    public AbrirPuertaCommand(Puerta puerta, String user) {
        this.puerta = puerta;
        this.user = user;
    }

    @Override
    public void execute() {
        puerta.abrir();
    }

    @Override
    public void undo() {
        puerta.cerrar();
    }

    @Override
    public String getName() {
        return "Abrir puerta";
    }

    @Override
    public String getUser() {
        return user;
    }
}
