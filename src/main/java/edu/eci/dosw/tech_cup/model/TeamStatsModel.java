package edu.eci.dosw.tech_cup.model;

/**
 * Representa las estadísticas de un equipo dentro del torneo.
 *
 * Esta clase es un objeto derivado que se utiliza para calcular y representar
 * el rendimiento de los equipos en la tabla de posiciones.
 */
public class TeamStatsModel {

    /** Equipo al que pertenecen las estadísticas */
    private TeamModel team;

    /** Partidos jugados */
    private int played;

    /** Partidos ganados */
    private int won;

    /** Partidos empatados */
    private int draw;

    /** Partidos perdidos */
    private int lost;

    /** Goles a favor */
    private int goalsFor;

    /** Goles en contra */
    private int goalsAgainst;

    /** Diferencia de goles */
    private int goalDifference;

    /** Puntos obtenidos */
    private int points;

    // ===================== MÉTODOS =====================

    /**
     * Obtiene el equipo asociado.
     *
     * @return equipo
     */
    public TeamModel getTeam() { return null; }

    /**
     * Obtiene los partidos jugados.
     *
     * @return partidos jugados
     */
    public int getPlayed() { return 0; }

    /**
     * Obtiene los partidos ganados.
     *
     * @return partidos ganados
     */
    public int getWon() { return 0; }

    /**
     * Obtiene los partidos empatados.
     *
     * @return partidos empatados
     */
    public int getDraw() { return 0; }

    /**
     * Obtiene los partidos perdidos.
     *
     * @return partidos perdidos
     */
    public int getLost() { return 0; }

    /**
     * Obtiene los goles a favor.
     *
     * @return goles a favor
     */
    public int getGoalsFor() { return 0; }

    /**
     * Obtiene los goles en contra.
     *
     * @return goles en contra
     */
    public int getGoalsAgainst() { return 0; }

    /**
     * Obtiene la diferencia de goles.
     *
     * @return diferencia de goles
     */
    public int getGoalDifference() { return 0; }

    /**
     * Obtiene los puntos del equipo.
     *
     * @return puntos
     */
    public int getPoints() { return 0; }

    /**
     * Calcula la diferencia de goles.
     */
    public void calculateGoalDifference() {}

    /**
     * Calcula los puntos del equipo.
     */
    public void calculatePoints() {}

    /**
     * Verifica si las estadísticas son válidas.
     *
     * @return true si los valores son coherentes
     */
    public boolean isValid() { return false; }

    /**
     * Actualiza las estadísticas con el resultado de un partido.
     *
     * @param goalsFor goles a favor en el partido
     * @param goalsAgainst goles en contra en el partido
     */
    public void updateStats(int goalsFor, int goalsAgainst) {}
}
