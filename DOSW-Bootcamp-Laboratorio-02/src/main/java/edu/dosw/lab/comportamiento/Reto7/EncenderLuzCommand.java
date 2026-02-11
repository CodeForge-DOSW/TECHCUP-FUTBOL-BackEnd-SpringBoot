package src.main.java.edu.dosw.lab.comportamiento.Reto7;

public class EncenderLuzCommand implements Command {

    private Luz luz;
    private String user;

    public EncenderLuzCommand(Luz luz, String user) {
        this.luz = luz;
        this.user = user;
    }

    @Override
    public void execute() {
        luz.encender();
    }

    @Override
    public void undo() {
        luz.apagar();
    }

    @Override
    public String getName() {
        return "Encender luz";
    }

    @Override
    public String getUser() {
        return user;
    }
}
