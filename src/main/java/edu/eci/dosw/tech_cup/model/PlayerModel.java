package edu.eci.dosw.tech_cup.model;

import java.util.List;

/**
 * Represents a player within the system.
 *
 * <p>This is the concrete implementation of {@link UserRoleModel} used by the
 * application to persist and transfer user data in CRUD operations. MapStruct
 * can instantiate this class when mapping from {@code UserEntity}.</p>
 */
public class PlayerModel extends UserRoleModel {

    /** Preferred playing positions of the player. */
    private List<PositionModel> preferredPositions;

    /** Jersey number of the player. */
    private int jerseyNumber;

    /** URL of the player's profile photo. */
    private String photoUrl;

    /** Indicates whether the player is available to join a team. */
    private boolean available;

    /**
     * Creates an empty player model.
     */
    public PlayerModel() {}

    // ===== Domain methods =====

    /**
     * Indicates whether the player contains the minimum valid profile data.
     *
     * @return {@code true} if the player has a non-empty email and a first name
     */
    public boolean isValid() {
        return email != null && !email.isEmpty() && firstName != null;
    }

    /**
     * Updates the player's availability.
     *
     * @param available new availability value
     */
    public void setAvailable(boolean available) { this.available = available; }

    /**
     * Indicates whether the player is currently available.
     *
     * @return {@code true} if the player is available
     */
    public boolean isAvailable() { return available; }

    /**
     * Accepts an invitation sent to the player.
     *
     * @param invitation invitation to accept
     */
    public void acceptInvitation(InvitationModel invitation) {}

    /**
     * Rejects an invitation sent to the player.
     *
     * @param invitation invitation to reject
     */
    public void rejectInvitation(InvitationModel invitation) {}

    /**
     * Submits a payment proof for a registration process.
     *
     * @param registration related registration
     * @param url payment proof URL
     */
    public void submitPaymentProof(RegistrationModel registration, String url) {}

    /**
     * Creates a lineup for a given team and match context.
     *
     * @param team team for which the lineup is created
     * @param match match associated with the lineup
     * @return created lineup, or {@code null} when not available
     */
    public LineupModel createLineup(TeamModel team, MatchModel match) { return null; }

    // ===== Getters and setters =====

    public List<PositionModel> getPreferredPositions() { return preferredPositions; }
    public void setPreferredPositions(List<PositionModel> preferredPositions) {
        this.preferredPositions = preferredPositions;
    }

    public int getJerseyNumber() { return jerseyNumber; }
    public void setJerseyNumber(int jerseyNumber) { this.jerseyNumber = jerseyNumber; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
}
