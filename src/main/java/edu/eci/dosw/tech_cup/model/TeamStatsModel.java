package edu.eci.dosw.tech_cup.model;

/**
 * Represents the statistics of a team within the tournament.
 *
 * <p>This derived model is used to calculate and display team performance
 * in standings and statistical summaries.</p>
 */
public class TeamStatsModel {

    /** Team associated with these statistics. */
    private TeamModel team;

    /** Number of matches played. */
    private int played;

    /** Number of matches won. */
    private int won;

    /** Number of matches drawn. */
    private int draw;

    /** Number of matches lost. */
    private int lost;

    /** Goals scored by the team. */
    private int goalsFor;

    /** Goals conceded by the team. */
    private int goalsAgainst;

    /** Goal difference value. */
    private int goalDifference;

    /** Total points earned. */
    private int points;

    /**
     * Returns the team associated with these statistics.
     *
     * @return related team
     */
    public TeamModel getTeam() {
        return team;
    }

    /**
     * Sets the team associated with these statistics.
     *
     * @param team team to associate
     */
    public void setTeam(TeamModel team) {
        this.team = team;
    }

    /**
     * Returns the number of matches played.
     *
     * @return matches played
     */
    public int getPlayed() {
        return played;
    }

    /**
     * Sets the number of matches played.
     *
     * @param played matches played
     */
    public void setPlayed(int played) {
        this.played = played;
    }

    /**
     * Returns the number of matches won.
     *
     * @return matches won
     */
    public int getWon() {
        return won;
    }

    /**
     * Sets the number of matches won.
     *
     * @param won matches won
     */
    public void setWon(int won) {
        this.won = won;
    }

    /**
     * Returns the number of matches drawn.
     *
     * @return matches drawn
     */
    public int getDraw() {
        return draw;
    }

    /**
     * Sets the number of matches drawn.
     *
     * @param draw matches drawn
     */
    public void setDraw(int draw) {
        this.draw = draw;
    }

    /**
     * Returns the number of matches lost.
     *
     * @return matches lost
     */
    public int getLost() {
        return lost;
    }

    /**
     * Sets the number of matches lost.
     *
     * @param lost matches lost
     */
    public void setLost(int lost) {
        this.lost = lost;
    }

    /**
     * Returns the goals scored by the team.
     *
     * @return goals scored
     */
    public int getGoalsFor() {
        return goalsFor;
    }

    /**
     * Sets the goals scored by the team.
     *
     * @param goalsFor goals scored
     */
    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    /**
     * Returns the goals conceded by the team.
     *
     * @return goals conceded
     */
    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    /**
     * Sets the goals conceded by the team.
     *
     * @param goalsAgainst goals conceded
     */
    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    /**
     * Returns the goal difference.
     *
     * @return goal difference
     */
    public int getGoalDifference() {
        return goalDifference;
    }

    /**
     * Sets the goal difference.
     *
     * @param goalDifference goal difference
     */
    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    /**
     * Returns the accumulated points.
     *
     * @return accumulated points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets the accumulated points.
     *
     * @param points accumulated points
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * Updates the team statistics based on a match result.
     *
     * @param goalsFor goals scored by the team
     * @param goalsAgainst goals conceded by the team
     */
    public void updateStats(int goalsFor, int goalsAgainst) {

    }

    /**
     * Calculates the goal difference of the team.
     *
     * @return calculated goal difference
     */
    public int calculateGoalDifference() {
        return 0;
    }

    /**
     * Calculates the points earned by the team based on its results.
     *
     * @return calculated points
     */
    public int calculatePoints() {
        return 0;
    }

    /**
     * Indicates whether the current statistics are valid.
     *
     * @return {@code true} if the statistics satisfy domain rules
     */
    public boolean isValid() {
        return false;
    }
}
