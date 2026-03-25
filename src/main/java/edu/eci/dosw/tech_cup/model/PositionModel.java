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

    /**
     * Verifica si la posición corresponde a portero.
     *
     * @return true si es portero
     */
    public boolean isGoalkeeper() {
        return false;
    }

    /**
     * Verifica si la posición corresponde a defensor.
     *
     * @return true si es defensor
     */
    public boolean isDefender() {
        return false;
    }

    /**
     * Verifica si la posición corresponde a mediocampista.
     *
     * @return true si es mediocampista
     */
    public boolean isMidfielder() {
        return false;
    }

    /**
     * Verifica si la posición corresponde a delantero.
     *
     * @return true si es delantero
     */
    public boolean isForward() {
        return false;
    }

    /**
     * Verifica si la posición es ofensiva.
     *
     * @return true si la posición se considera ofensiva
     */
    public boolean isAttackingRole() {
        return false;
    }

    /**
     * Convierte un texto en una posición del sistema.
     *
     * @param value valor de texto a convertir
     * @return posición correspondiente o null si no aplica
     */
    public static PositionModel fromString(String value) {
        return null;
    }
}
