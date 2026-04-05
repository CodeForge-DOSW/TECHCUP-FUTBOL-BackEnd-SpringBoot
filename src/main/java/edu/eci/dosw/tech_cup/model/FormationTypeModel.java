package edu.eci.dosw.tech_cup.model;

/**
 * Enumerates the tactical formations a team can use within the system.
 *
 * <p>Each value represents a specific player distribution on the field and is
 * used by {@code LineupModel} to validate team lineups.</p>
 */
public enum FormationTypeModel {

    /** 3-2-1 formation. */
    THREE_TWO_ONE,

    /** 2-2-2 formation. */
    TWO_TWO_TWO,

    /** 1-4-1 formation. */
    ONE_FOUR_ONE,

    /** 2-3-1 formation. */
    TWO_THREE_ONE,

    /** 3-1-2 formation. */
    THREE_ONE_TWO,

    /** 2-1-3 formation. */
    TWO_ONE_THREE,

    /** 1-3-2 formation. */
    ONE_THREE_TWO,

    /** 0-4-3 formation. */
    ZERO_FOUR_THREE,

    /** 2-4-0 formation. */
    TWO_FOUR_ZERO,

    /** 3-3-0 formation. */
    THREE_THREE_ZERO,

    /** 4-1-1 formation. */
    FOUR_ONE_ONE,

    /** 4-2-0 formation. */
    FOUR_TWO_ZERO,

    /** 5-1-0 formation. */
    FIVE_ONE_ZERO,

    /** 0-5-1 formation. */
    ZERO_FIVE_ONE,

    /** 1-2-3 formation. */
    ONE_TWO_THREE;
}
