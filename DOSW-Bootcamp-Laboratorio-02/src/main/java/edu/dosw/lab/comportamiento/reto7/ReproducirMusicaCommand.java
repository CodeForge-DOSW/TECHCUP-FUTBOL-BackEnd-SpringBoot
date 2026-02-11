package edu.dosw.lab.comportamiento.Reto7

public class ReproducirMusicaCommand implements Command {

    private Musica musica;
    private String user;

    public ReproducirMusicaCommand(Musica musica, String user) {
        this.musica = musica;
        this.user = user;
    }

    @Override
    public void execute() {
        musica.reproducir();
    }

    @Override
    public void undo() {
        musica.detener();
    }

    @Override
    public String getName() {
        return "Reproducir música";
    }

    @Override
    public String getUser() {
        return user;
    }
}
