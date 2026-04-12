package edu.eci.dosw.tech_cup.model;

import java.time.LocalDateTime;

/**
 * Represents the registration of a team in a tournament.
 *
 * <p>This model manages the registration workflow, including payment proof
 * submission, registration status, and organizer validation.</p>
 */
public class RegistrationModel {

    /** Unique identifier of the registration. */
    private Long id;

    /** Team being registered. */
    private TeamModel team;

    /** Tournament associated with the registration. */
    private TournamentModel tournament;

    /** URL of the payment proof. */
    private String paymentProofUrl;

    /** Current registration status. */
    private RegistrationStatusModel status;

    /** Registration creation date. */
    private LocalDateTime createdAt;

    /**
     * Submits the payment proof for the registration.
     *
     * @param url payment proof URL
     */
    public void submitProof(String url) {
    }

    /**
     * Marks the registration as under review.
     */
    public void markInReview() {  }

    /**
     * Approves the registration.
     */
    public void approve() { }

    /**
     * Rejects the registration.
     */
    public void reject() {  }

    /**
     * Indicates whether the registration is approved.
     *
     * @return {@code true} if the status is {@link RegistrationStatusModel#APPROVED}
     */
    public boolean isApproved() { return false; }
}
