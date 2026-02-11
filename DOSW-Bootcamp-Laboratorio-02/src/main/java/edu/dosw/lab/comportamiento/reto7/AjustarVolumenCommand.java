package edu.dosw.lab.comportamiento.reto7;
public class AjustarVolumenCommand implements Command {

    private Volumen volumen;
    private String user;
    private int nivel;

    public AjustarVolumenCommand(Volumen volumen, String user, int nivel) {
        this.volumen = volumen;
        this.user = user;
        this.nivel = nivel;
    }

    @Override
    public void execute() {
        volumen.ajustar(nivel);
    }

    @Override
    public void undo() {
        volumen.resetear();
    }

    @Override
    public String getName() {
        return "Ajustar volumen a " + nivel + "%";
    }

    @Override
    public String getUser() {
        return user;
    }
}
