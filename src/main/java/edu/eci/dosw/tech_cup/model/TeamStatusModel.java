package edu.eci.dosw.tech_cup.model;

/**
 * Enumerates the possible statuses of a team within the system.
 *
 * <p>This enum describes the approval lifecycle of a team, from pending review
 * to acceptance, rejection, or expiration.</p>
 */
public enum TeamStatusModel {

    /** Team is waiting for validation or approval. */
    PENDING,

    /** Team has been accepted to participate. */
    ACCEPTED,

    /** Team has been rejected. */
    REJECTED,

    /** Team status has expired. */
    EXPIRED;

    /**
     * Indicates whether the team is pending.
     *
     * @return {@code true} if the status is {@link #PENDING}; {@code false} otherwise
     */
    public boolean isPending() { return this == PENDING; }

    /**
     * Indicates whether the team has been accepted.
     *
     * @return {@code true} if the status is {@link #ACCEPTED}; {@code false} otherwise
     */
    public boolean isAccepted() { return this == ACCEPTED; }

    /**
     * Indicates whether the team has been rejected.
     *
     * @return {@code true} if the status is {@link #REJECTED}; {@code false} otherwise
     */
    public boolean isRejected() { return this == REJECTED; }

    /**
     * Indicates whether the team status has expired.
     *
     * @return {@code true} if the status is {@link #EXPIRED}; {@code false} otherwise
     */
    public boolean isExpired() { return this == EXPIRED; }

    /**
     * Indicates whether the team has already reached a final state.
     *
     * @return {@code true} if the team is accepted, rejected, or expired
     */
    public boolean isFinalized() { return isAccepted() || isRejected() || isExpired(); }

    /**
     * Indicates whether the team is allowed to participate.
     *
     * @return {@code true} when the team has been accepted
     */
    public boolean canParticipate() { return isAccepted(); }
}
