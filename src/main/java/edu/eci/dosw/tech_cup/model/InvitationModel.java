package edu.eci.dosw.tech_cup.model;

import java.time.LocalDateTime;

/**
 * Represents an invitation sent to a player to join a team.
 *
 * <p>This model manages the invitation workflow, including its status,
 * optional message, and creation date.</p>
 */
public class InvitationModel {

    /** Unique identifier of the invitation. */
    private Long id;

    /** Player who sends the invitation. */
    private PlayerModel sender;

    /** Player who receives the invitation. */
    private PlayerModel receiver;

    /** Team the player is invited to join. */
    private TeamModel team;

    /** Optional invitation message. */
    private String message;

    /** Current invitation status. */
    private InvitationStatusModel status;

    /** Invitation creation date. */
    private LocalDateTime createdAt;

    // ===================== Domain methods =====================

    /**
     * Accepts the invitation.
     *
     * <p>This changes the invitation status to accepted.</p>
     */
    public void accept() { this.status = InvitationStatusModel.ACCEPTED; }

    /**
     * Rejects the invitation.
     *
     * <p>This changes the invitation status to rejected.</p>
     */
    public void reject() { this.status = InvitationStatusModel.REJECTED; }

    /**
     * Indicates whether the invitation is pending.
     *
     * @return {@code true} if the invitation is pending
     */
    public boolean isPending() { return false; }

    /**
     * Indicates whether the invitation has been accepted.
     *
     * @return {@code true} if the invitation is accepted
     */
    public boolean isAccepted() { return false; }

    /**
     * Indicates whether the invitation has been rejected.
     *
     * @return {@code true} if the invitation is rejected
     */
    public boolean isRejected() { return false; }

    /**
     * Indicates whether the invitation contains valid required data.
     *
     * @return {@code true} if sender, receiver, and team are defined
     */
    public boolean isValid() {
        return false;
    }
}
