package src.main.java.edu.dosw.lab.comportamiento.Reto7;

public interface Command {
    void execute();
    void undo();
    String getName();
    String getUser();
}
