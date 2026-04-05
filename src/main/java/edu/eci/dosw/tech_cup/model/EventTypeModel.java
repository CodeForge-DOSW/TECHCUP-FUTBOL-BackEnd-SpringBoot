package edu.eci.dosw.tech_cup.model;

/**
 * Enumerates the event types that may occur during a match.
 *
 * <p>This enum classifies actions such as goals and disciplinary cards and is
 * used by {@code MatchEventModel} to record and process gameplay events.</p>
 */
public enum EventTypeModel {

    /** Goal scored by a player. */
    GOAL,

    /** Yellow card. */
    YELLOW_CARD,

    /** Red card. */
    RED_CARD;

    // ===================== Domain methods =====================

    /**
     * Indicates whether the event type is a goal.
     *
     * @return {@code true} if the event type is {@link #GOAL}
     */
    public boolean isGoal() { return this == GOAL; }

    /**
     * Indicates whether the event type is a yellow card.
     *
     * @return {@code true} if the event type is {@link #YELLOW_CARD}
     */
    public boolean isYellowCard() { return this == YELLOW_CARD; }

    /**
     * Indicates whether the event type is a red card.
     *
     * @return {@code true} if the event type is {@link #RED_CARD}
     */
    public boolean isRedCard() { return this == RED_CARD; }

    /**
     * Indicates whether the event type is a disciplinary card.
     *
     * @return {@code true} if the event type is yellow or red card
     */
    public boolean isCard() { return false; }
}
