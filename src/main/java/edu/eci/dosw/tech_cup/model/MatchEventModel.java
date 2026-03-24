package edu.eci.dosw.tech_cup.model;

/**
 * Representa un evento ocurrido durante un partido.
 *
 * Esta clase permite registrar acciones como goles y sanciones,
 * asociándolas a un jugador, un equipo y un minuto específico del partido.
 */
public class MatchEventModel {

    /** Identificador único del evento */
    private Long id;

    /** Minuto en el que ocurrió el evento */
    private int minute;

    /** Jugador que realizó el evento */
    private PlayerModel player;

    /** Tipo de evento */
    private EventTypeModel type;

    /** Equipo al que pertenece el jugador */
    private TeamModel team;

    // ===================== MÉTODOS =====================

    /**
     * Verifica si el evento corresponde a un gol.
     *
     * @return true si es un gol
     */
    public boolean isGoal() { return false; }

    /**
     * Verifica si el evento corresponde a una tarjeta amarilla.
     *
     * @return true si es tarjeta amarilla
     */
    public boolean isYellowCard() { return false; }

    /**
     * Verifica si el evento corresponde a una tarjeta roja.
     *
     * @return true si es tarjeta roja
     */
    public boolean isRedCard() { return false; }

    /**
     * Verifica si el evento es válido.
     *
     * @return true si tiene jugador, equipo y tipo definidos
     */
    public boolean isValid() { return false; }
}
