package src.main.java.edu.dosw.lab.comportamiento.Reto7;

import java.util.ArrayList;
import java.util.List;

public class ControlRemoto {

    private List<Command> historial = new ArrayList<>();

    public void ejecutar(Command command) {
        command.execute();
        historial.add(command);
    }

    public void deshacer(Command command) {
        command.undo();
    }

    public void mostrarHistorial() {
        System.out.println("\n--- Historial completo ---");
        int i = 1;
        for (Command cmd : historial) {
            System.out.println(i++ + ". " + cmd.getName() + " - Usuario: " + cmd.getUser());
        }
    }

    public void investigar() {
        System.out.println("\n--- Investigando quién desconfiguró ---");
        historial.stream()
                .collect(java.util.stream.Collectors.groupingBy(Command::getUser, java.util.stream.Collectors.counting()))
                .forEach((user, count) ->
                        System.out.println(user + " realizó " + count + " acciones.")
                );
    }
}
