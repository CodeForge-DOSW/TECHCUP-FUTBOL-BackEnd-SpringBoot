package edu.eci.dosw.tech_cup.model;

/**
 * Represents an event that occurred during a match.
 *
 * <p>This model records actions such as goals and sanctions, linking them to a
 * player, a team, and a specific minute of the match.</p>
 */
public class MatchEventModel {

    /** Unique identifier of the event. */
    private Long id;

    /** Minute in which the event occurred. */
    private int minute;

    /** Player responsible for the event. */
    private PlayerModel player;

    /** Event type. */
    private EventTypeModel type;

    /** Team to which the player belongs. */
    private TeamModel team;

    /**
     * Indicates whether the event corresponds to a goal.
     *
     * @return {@code true} if the event is a goal
     */
    public boolean isGoal() { return type != null && type.isGoal(); }

    /**
     * Indicates whether the event corresponds to a yellow card.
     *
     * @return {@code true} if the event is a yellow card
     */
    public boolean isYellowCard() { return type != null && type.isYellowCard(); }

    /**
     * Indicates whether the event corresponds to a red card.
     *
     * @return {@code true} if the event is a red card
     */
    public boolean isRedCard() { return false; }

    /**
     * Indicates whether the event contains valid required data.
     *
     * @return {@code true} if player, team, and type are defined
     */
    public boolean isValid() { return false; }

    public PlayerModel getPlayer() { return player; }

    public EventTypeModel getType() { return type; }

    public TeamModel getTeam() { return team; }

    public int getMinute() { return minute; }
}
