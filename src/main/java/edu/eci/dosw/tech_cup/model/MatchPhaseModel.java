package edu.eci.dosw.tech_cup.model;

/**
 * Enumerates the phases a match can belong to within the tournament.
 *
 * <p>This enum classifies matches by competition stage, such as group phase or
 * knockout rounds, helping organize brackets and tournament structure.</p>
 */
public enum MatchPhaseModel {

    /** Group stage phase. */
    GROUP_STAGE,

    /** Quarterfinal phase. */
    QUARTERFINAL,

    /** Semifinal phase. */
    SEMIFINAL,

    /** Tournament final phase. */
    FINAL;

    /**
     * Indicates whether the match belongs to the group stage.
     *
     * @return {@code true} if the phase is {@link #GROUP_STAGE}
     */
    public boolean isGroupStage() { return this == GROUP_STAGE; }

    /**
     * Indicates whether the match belongs to a knockout stage.
     *
     * @return {@code true} if the phase is quarterfinal, semifinal, or final
     */
    public boolean isKnockoutStage() { return this == QUARTERFINAL || this == SEMIFINAL || this == FINAL; }

    /**
     * Indicates whether the match is the final.
     *
     * @return {@code true} if the phase is {@link #FINAL}
     */
    public boolean isFinal() { return this == FINAL; }

    /**
     * Indicates whether the match is a semifinal.
     *
     * @return {@code true} if the phase is {@link #SEMIFINAL}
     */
    public boolean isSemifinal() { return this == SEMIFINAL; }

    /**
     * Indicates whether the match is a quarterfinal.
     *
     * @return {@code true} if the phase is {@link #QUARTERFINAL}
     */
    public boolean isQuarterfinal() { return this == QUARTERFINAL; }
}
