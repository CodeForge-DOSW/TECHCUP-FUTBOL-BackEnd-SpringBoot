package edu.eci.dosw.tech_cup.model;

/**
 * Representa las estadísticas individuales de un jugador dentro del torneo.
 *
 * Esta clase permite almacenar información como goles y sanciones,
 * siendo utilizada para generar rankings como máximos goleadores
 * y análisis de rendimiento.
 */
public class PlayerStatsModel {

    /** Jugador al que pertenecen las estadísticas */
    private PlayerModel player;

    /** Cantidad de goles anotados */
    private int goals;

    /** Cantidad de tarjetas amarillas */
    private int yellowCards;

    /** Cantidad de tarjetas rojas */
    private int redCards;

    // ===================== MÉTODOS =====================

    /**
     * Obtiene el jugador asociado a las estadísticas.
     *
     * @return jugador
     */
    public PlayerModel getPlayer() { return null; }

    /**
     * Obtiene la cantidad de goles.
     *
     * @return número de goles
     */
    public int getGoals() { return 0; }

    /**
     * Obtiene la cantidad de tarjetas amarillas.
     *
     * @return número de tarjetas amarillas
     */
    public int getYellowCards() { return 0; }

    /**
     * Obtiene la cantidad de tarjetas rojas.
     *
     * @return número de tarjetas rojas
     */
    public int getRedCards() { return 0; }

    /**
     * Incrementa la cantidad de goles.
     */
    public void addGoal() {}

    /**
     * Incrementa la cantidad de tarjetas amarillas.
     */
    public void addYellowCard() {}

    /**
     * Incrementa la cantidad de tarjetas rojas.
     */
    public void addRedCard() {}

    /**
     * Verifica si el jugador tiene sanciones.
     *
     * @return true si tiene tarjetas
     */
    public boolean hasCards() { return false; }

    /**
     * Verifica si el jugador tiene tarjeta roja.
     *
     * @return true si tiene al menos una roja
     */
    public boolean hasRedCard() { return false; }
}