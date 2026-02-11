package edu.dosw.lab.comportamiento.reto7;

public class Volumen {
    private int nivel = 0;

    public void ajustar(int nivel) {
        this.nivel = nivel;
        System.out.println("Volumen ajustado a " + nivel + "%");
    }

    public void resetear() {
        this.nivel = 0;
        System.out.println("Volumen regresado a 0%");
    }
}
