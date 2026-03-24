package edu.eci.dosw.tech_cup.model;

/**
 * Define las posiciones que puede ocupar un jugador dentro del campo.
 *
 * Este enum permite clasificar a los jugadores según su rol en el juego,
 * siendo utilizado en LineupModel para asignar posiciones y validar
 * la alineación del equipo.
 */
public enum PositionModel {

    /** Portero */
    GOALKEEPER,

    /** Defensor */
    DEFENDER,

    /** Mediocampista */
    MIDFIELDER,

    /** Delantero */
    FORWARD;

    // ===================== MÉTODOS =====================

    /**
     * Verifica si la posición corresponde a un portero.
     *
     * @return true si es GOALKEEPER
     */
    public boolean isGoalkeeper() { return false; }

    /**
     * Verifica si la posición corresponde a un defensor.
     *
     * @return true si es DEFENDER
     */
    public boolean isDefender() { return false; }

    /**
     * Verifica si la posición corresponde a un mediocampista.
     *
     * @return true si es MIDFIELDER
     */
    public boolean isMidfielder() { return false; }

    /**
     * Verifica si la posición corresponde a un delantero.
     *
     * @return true si es FORWARD
     */
    public boolean isForward() { return false; }

    /**
     * Verifica si la posición es defensiva.
     *
     * @return true si es portero o defensor
     */
    public boolean isDefensive() { return false; }

    /**
     * Verifica si la posición es ofensiva.
     *
     * @return true si es mediocampista o delantero
     */
    public boolean isOffensive() { return false; }
}
