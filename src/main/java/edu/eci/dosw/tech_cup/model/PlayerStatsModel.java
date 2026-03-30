package edu.eci.dosw.tech_cup.model;

/**
 * Represents the individual statistics of a player within the tournament.
 *
 * <p>This model stores data such as goals and disciplinary cards and is used
 * to generate rankings and performance analysis.</p>
 */
public class PlayerStatsModel {

    /** Player associated with these statistics. */
    private PlayerModel player;

    /** Number of goals scored. */
    private int goals;

    /** Number of yellow cards received. */
    private int yellowCards;

    /** Number of red cards received. */
    private int redCards;

    /**
     * Returns the player associated with these statistics.
     *
     * @return related player
     */
    public PlayerModel getPlayer() { return player; }

    public void setPlayer(PlayerModel player) { this.player = player; }

    /**
     * Returns the number of goals scored.
     *
     * @return goal count
     */
    public int getGoals() { return goals; }

    /**
     * Returns the number of yellow cards received.
     *
     * @return yellow card count
     */
    public int getYellowCards() { return yellowCards; }

    /**
     * Returns the number of red cards received.
     *
     * @return red card count
     */
    public int getRedCards() { return redCards; }

    /**
     * Increments the goal count.
     */
    public void addGoal() { goals++; }

    /**
     * Increments the yellow card count.
     */
    public void addYellowCard() { yellowCards++; }

    /**
     * Increments the red card count.
     */
    public void addRedCard() { redCards++; }

    /**
     * Indicates whether the player has any cards recorded.
     *
     * @return {@code true} if the player has disciplinary cards
     */
    public boolean hasCards() { return false; }

    /**
     * Indicates whether the player has at least one red card.
     *
     * @return {@code true} if the player has at least one red card
     */
    public boolean hasRedCard() { return false; }
}
