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

    /**
     * Obtiene el equipo asociado a estas estadísticas.
     *
     * @return equipo asociado
     */
    public TeamModel getTeam() {
        return team;
    }

    /**
     * Asigna el equipo asociado a estas estadísticas.
     *
     * @param team equipo a asociar
     */
    public void setTeam(TeamModel team) {
        this.team = team;
    }

    /**
     * Obtiene la cantidad de partidos jugados.
     *
     * @return partidos jugados
     */
    public int getPlayed() {
        return played;
    }

    /**
     * Asigna la cantidad de partidos jugados.
     *
     * @param played partidos jugados
     */
    public void setPlayed(int played) {
        this.played = played;
    }

    /**
     * Obtiene la cantidad de partidos ganados.
     *
     * @return partidos ganados
     */
    public int getWon() {
        return won;
    }

    /**
     * Asigna la cantidad de partidos ganados.
     *
     * @param won partidos ganados
     */
    public void setWon(int won) {
        this.won = won;
    }

    /**
     * Obtiene la cantidad de partidos empatados.
     *
     * @return partidos empatados
     */
    public int getDraw() {
        return draw;
    }

    /**
     * Asigna la cantidad de partidos empatados.
     *
     * @param draw partidos empatados
     */
    public void setDraw(int draw) {
        this.draw = draw;
    }

    /**
     * Obtiene la cantidad de partidos perdidos.
     *
     * @return partidos perdidos
     */
    public int getLost() {
        return lost;
    }

    /**
     * Asigna la cantidad de partidos perdidos.
     *
     * @param lost partidos perdidos
     */
    public void setLost(int lost) {
        this.lost = lost;
    }

    /**
     * Obtiene los goles a favor del equipo.
     *
     * @return goles a favor
     */
    public int getGoalsFor() {
        return goalsFor;
    }

    /**
     * Asigna los goles a favor del equipo.
     *
     * @param goalsFor goles a favor
     */
    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    /**
     * Obtiene los goles en contra del equipo.
     *
     * @return goles en contra
     */
    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    /**
     * Asigna los goles en contra del equipo.
     *
     * @param goalsAgainst goles en contra
     */
    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    /**
     * Obtiene la diferencia de goles.
     *
     * @return diferencia de goles
     */
    public int getGoalDifference() {
        return goalDifference;
    }

    /**
     * Asigna la diferencia de goles.
     *
     * @param goalDifference diferencia de goles
     */
    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    /**
     * Obtiene los puntos acumulados.
     *
     * @return puntos acumulados
     */
    public int getPoints() {
        return points;
    }

    /**
     * Asigna los puntos acumulados.
     *
     * @param points puntos acumulados
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Actualiza las estadísticas del equipo con el resultado de un partido.
     *
     * @param goalsFor goles anotados por el equipo
     * @param goalsAgainst goles recibidos por el equipo
     */
    public void updateStats(int goalsFor, int goalsAgainst) {

    }

    /**
     * Calcula la diferencia de goles del equipo.
     *
     * @return diferencia de goles calculada
     */
    public int calculateGoalDifference() {
        return 0;
    }

    /**
     * Calcula los puntos del equipo según sus resultados.
     *
     * @return puntos calculados
     */
    public int calculatePoints() {
        return 0;
    }

    /**
     * Verifica si las estadísticas actuales son válidas.
     *
     * @return true si las estadísticas cumplen las reglas del dominio
     */
    public boolean isValid() {
        return false;
    }
}
