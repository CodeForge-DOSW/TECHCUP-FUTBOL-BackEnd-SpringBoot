package edu.eci.dosw.tech_cup.model;

/**
 * Enumerates the possible lifecycle states of a tournament.
 *
 * <p>This enum is used to manage the state transitions of {@code TournamentModel}
 * from initial setup to final completion.</p>
 */
public enum TournamentStatusModel {

    /** Tournament is still in configuration stage. */
    DRAFT,

    /** Tournament is active and ready for progression. */
    ACTIVE,

    /** Tournament is currently in progress. */
    IN_PROGRESS,

    /** Tournament has already finished. */
    FINISHED;

    /**
     * Indicates whether the tournament is in draft state.
     *
     * @return {@code true} if the status is {@link #DRAFT}; {@code false} otherwise
     */
    public boolean isDraft() { return this == DRAFT; }

    /**
     * Indicates whether the tournament is active.
     *
     * @return {@code true} if the status is {@link #ACTIVE}; {@code false} otherwise
     */
    public boolean isActive() { return this == ACTIVE; }

    /**
     * Indicates whether the tournament is currently in progress.
     *
     * @return {@code true} if the status is {@link #IN_PROGRESS}; {@code false} otherwise
     */
    public boolean isInProgress() { return this == IN_PROGRESS; }

    /**
     * Indicates whether the tournament has finished.
     *
     * @return {@code true} if the status is {@link #FINISHED}; {@code false} otherwise
     */
    public boolean isFinished() { return this == FINISHED; }

    /**
     * Indicates whether the tournament can be started.
     *
     * @return {@code true} when the current status allows starting
     */
    public boolean canStart() { return isActive(); }

    /**
     * Indicates whether the tournament is closed to further changes.
     *
     * @return {@code true} when the tournament is finished
     */
    public boolean isClosed() { return isFinished(); }
}
