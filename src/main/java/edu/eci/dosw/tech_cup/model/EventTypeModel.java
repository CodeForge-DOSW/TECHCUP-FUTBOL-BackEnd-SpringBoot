package edu.eci.dosw.tech_cup.model;

/**
 * Define los tipos de eventos que pueden ocurrir durante un partido.
 *
 * Este enum permite clasificar acciones como goles y sanciones,
 * siendo utilizado en MatchEventModel para registrar y procesar
 * los eventos del juego.
 */
public enum EventTypeModel {

    /** Gol anotado por un jugador */
    GOAL,

    /** Tarjeta amarilla */
    YELLOW_CARD,

    /** Tarjeta roja */
    RED_CARD;

    // ===================== MÉTODOS =====================

    /**
     * Verifica si el evento corresponde a un gol.
     *
     * @return true si es GOAL
     */
    public boolean isGoal() { return this == GOAL; }

    /**
     * Verifica si el evento corresponde a una tarjeta amarilla.
     *
     * @return true si es YELLOW_CARD
     */
    public boolean isYellowCard() { return this == YELLOW_CARD; }

    /**
     * Verifica si el evento corresponde a una tarjeta roja.
     *
     * @return true si es RED_CARD
     */
    public boolean isRedCard() { return this == RED_CARD; }

    /**
     * Verifica si el evento corresponde a una sanción (tarjeta).
     *
     * @return true si es amarilla o roja
     */
    public boolean isCard() { return false; }
}
