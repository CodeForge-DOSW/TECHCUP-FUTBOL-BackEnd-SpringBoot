package edu.eci.dosw.tech_cup.model;

/**
 * Enumerates the possible statuses of a match within the system.
 *
 * <p>This enum manages the lifecycle of a {@code MatchModel}, from scheduling
 * to completion or cancellation.</p>
 */
public enum MatchStatusModel {

    /** Match is scheduled but has not started yet. */
    SCHEDULED,

    /** Match is currently in progress. */
    IN_PROGRESS,

    /** Match has finished. */
    FINISHED,

    /** Match has been cancelled. */
    CANCELLED;

    /**
     * Indicates whether the match is scheduled.
     *
     * @return {@code true} if the status is {@link #SCHEDULED}
     */
    public boolean isScheduled() { return this == SCHEDULED; }

    /**
     * Indicates whether the match is in progress.
     *
     * @return {@code true} if the status is {@link #IN_PROGRESS}
     */
    public boolean isInProgress() { return this == IN_PROGRESS; }

    /**
     * Indicates whether the match has finished.
     *
     * @return {@code true} if the status is {@link #FINISHED}
     */
    public boolean isFinished() { return this == FINISHED; }

    /**
     * Indicates whether the match has been cancelled.
     *
     * @return {@code true} if the status is {@link #CANCELLED}
     */
    public boolean isCancelled() { return this == CANCELLED; }

    /**
     * Indicates whether the match can still be modified.
     *
     * @return {@code true} if the match is scheduled or in progress
     */
    public boolean isEditable() { return isScheduled() || isInProgress(); }

    /**
     * Indicates whether the match no longer accepts changes.
     *
     * @return {@code true} if the match is finished or cancelled
     */
    public boolean isClosed() { return isFinished() || isCancelled(); }
}
