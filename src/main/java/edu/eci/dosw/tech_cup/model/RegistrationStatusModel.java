package edu.eci.dosw.tech_cup.model;

/**
 * Enumerates the statuses of a registration within the tournament.
 *
 * <p>This enum models the registration workflow of teams, from the initial
 * request through review, approval, or rejection.</p>
 */
public enum RegistrationStatusModel {

    /** Registration is waiting for review. */
    PENDING,

    /** Registration is currently under review. */
    IN_REVIEW,

    /** Registration has been approved. */
    APPROVED,

    /** Registration has been rejected. */
    REJECTED;

    /**
     * Indicates whether the registration is pending review.
     *
     * @return {@code true} if the registration is pending
     */
    public boolean isPending() {
        return false;
    }

    /**
     * Indicates whether the registration is under review.
     *
     * @return {@code true} if the registration is in review
     */
    public boolean isInReview() {
        return false;
    }

    /**
     * Indicates whether the registration was approved.
     *
     * @return {@code true} if the registration is approved
     */
    public boolean isApproved() {
        return false;
    }

    /**
     * Indicates whether the registration was rejected.
     *
     * @return {@code true} if the registration is rejected
     */
    public boolean isRejected() {
        return false;
    }

    /**
     * Indicates whether the registration has reached a final state.
     *
     * @return {@code true} if the status is terminal
     */
    public boolean isFinalized() {
        return false;
    }

    /**
     * Indicates whether the registration can move to review status.
     *
     * @return {@code true} if a transition to {@link #IN_REVIEW} is allowed
     */
    public boolean canMoveToReview() {
        return false;
    }

    /**
     * Indicates whether the registration can be approved.
     *
     * @return {@code true} if a transition to {@link #APPROVED} is allowed
     */
    public boolean canApprove() {
        return false;
    }

    /**
     * Indicates whether the registration can be rejected.
     *
     * @return {@code true} if a transition to {@link #REJECTED} is allowed
     */
    public boolean canReject() {
        return false;
    }
}
