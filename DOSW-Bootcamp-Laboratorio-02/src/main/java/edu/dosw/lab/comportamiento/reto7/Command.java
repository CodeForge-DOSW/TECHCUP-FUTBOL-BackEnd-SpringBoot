package edu.dosw.lab.comportamiento.reto7;

public interface Command {
    void execute();
    void undo();
    String getName();
    String getUser();
}
