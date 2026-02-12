import edu.dosw.lab.creacionales.reto1.Reto1;
import edu.dosw.lab.creacionales.reto2.Reto2;
import edu.dosw.lab.creacionales.Reto3.Reto3;
import edu.dosw.lab.estructurales.Reto4.Reto4;
import edu.dosw.lab.estructurales.reto5.reto5;
import edu.dosw.lab.comportamiento.reto6.reto6;

public class Application {
    public static void main(String[] args) {
        System.out.println("✅ Proyecto Maven configurado y corriendo correctamente");

        System.out.println("RETO #1: El problema de la tienda de Don Pepe");
        Reto1.ejecutar();

        System.out.println("RETO #2: El chef de 5 estrellas");
        Reto2.ejecutar();

        System.out.println("RETO #3: El Reino de los Vehículos");
        Reto3.ejecutar();

        System.out.println("RETO #4: La Estafa de la Casa de Cambio");
        Reto4.ejecutar();

        System.out.println("RETO #5: El Café Personalizado");
        reto5.ejecutar();

        System.out.println("RETO #6: Habla con Soporte Técnico");
        reto6.ejecutar();

    }
}