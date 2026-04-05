package edu.eci.dosw.tech_cup.model;

/**
 * Enumerates the positions a player can occupy on the field.
 *
 * <p>This enum classifies players according to their gameplay role and is used
 * by lineup-related logic to assign and validate positions.</p>
 */
public enum PositionModel {

    /** Goalkeeper position. */
    GOALKEEPER,

    /** Defender position. */
    DEFENDER,

    /** Midfielder position. */
    MIDFIELDER,

    /** Forward position. */
    FORWARD;

    /**
     * Indicates whether the position is goalkeeper.
     *
     * @return {@code true} if the position is {@link #GOALKEEPER}
     */
    public boolean isGoalkeeper() {
        return false;
    }

    /**
     * Indicates whether the position is defender.
     *
     * @return {@code true} if the position is {@link #DEFENDER}
     */
    public boolean isDefender() {
        return false;
    }

    /**
     * Indicates whether the position is midfielder.
     *
     * @return {@code true} if the position is {@link #MIDFIELDER}
     */
    public boolean isMidfielder() {
        return false;
    }

    /**
     * Indicates whether the position is forward.
     *
     * @return {@code true} if the position is {@link #FORWARD}
     */
    public boolean isForward() {
        return false;
    }

    /**
     * Indicates whether the position is considered attacking.
     *
     * @return {@code true} if the position is considered offensive
     */
    public boolean isAttackingRole() {
        return false;
    }

    /**
     * Converts a text value into a system position.
     *
     * @param value text value to convert
     * @return matching position, or {@code null} if the value does not apply
     */
    public static PositionModel fromString(String value) {
        return null;
    }
}
