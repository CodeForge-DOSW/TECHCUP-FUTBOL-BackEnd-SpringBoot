package edu.eci.dosw.tech_cup.model;

/**
 * Enumerates the statuses of an invitation within the system.
 *
 * <p>This enum manages the lifecycle of an invitation, from creation to
 * acceptance, rejection, or expiration.</p>
 */
public enum InvitationStatusModel {

    /** Invitation is waiting for a response. */
    PENDING,

    /** Invitation has been accepted. */
    ACCEPTED,

    /** Invitation has been rejected. */
    REJECTED,

    /** Invitation has expired. */
    EXPIRED;


    /**
     * Indicates whether the invitation is pending.
     *
     * @return {@code true} if the status is {@link #PENDING}
     */
    public boolean isPending() { return this == PENDING; }

    /**
     * Indicates whether the invitation has been accepted.
     *
     * @return {@code true} if the status is {@link #ACCEPTED}
     */
    public boolean isAccepted() { return this == ACCEPTED; }

    /**
     * Indicates whether the invitation has been rejected.
     *
     * @return {@code true} if the status is {@link #REJECTED}
     */
    public boolean isRejected() { return this == REJECTED; }

    /**
     * Indicates whether the invitation has expired.
     *
     * @return {@code true} if the status is {@link #EXPIRED}
     */
    public boolean isExpired() { return this == EXPIRED; }

    /**
     * Indicates whether the invitation has already reached a final state.
     *
     * @return {@code true} if the invitation is accepted, rejected, or expired
     */
    public boolean isFinalized() { return false; }

    /**
     * Indicates whether the invitation is still active.
     *
     * @return {@code true} if the invitation is still pending
     */
    public boolean isActive() { return false; }
}
