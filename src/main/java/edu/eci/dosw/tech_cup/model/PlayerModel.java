package edu.eci.dosw.tech_cup.model;

import java.util.List;

/**
 * Representa un jugador dentro del sistema.
 *
 * <p>Es la implementación concreta de {@link UserRoleModel} que usa el sistema
 * para persistir y transferir datos de usuario en las operaciones CRUD.
 * MapStruct puede instanciar esta clase al mapear desde {@code UserEntity}.</p>
 */
public class PlayerModel extends UserRoleModel {

    /** Posiciones preferidas del jugador */
    private List<PositionModel> preferredPositions;

    /** Número de camiseta */
    private int jerseyNumber;

    /** URL de la foto del jugador */
    private String photoUrl;

    /** Indica si el jugador está disponible para unirse a un equipo */
    private boolean available;

    public PlayerModel() {}

    // ===== MÉTODOS DE DOMINIO =====

    public boolean isValid() {
        return email != null && !email.isEmpty() && firstName != null;
    }

    public void setAvailable(boolean available) { this.available = available; }
    public boolean isAvailable() { return available; }

    public void acceptInvitation(InvitationModel invitation) {}
    public void rejectInvitation(InvitationModel invitation) {}
    public void submitPaymentProof(RegistrationModel registration, String url) {}

    public LineupModel createLineup(TeamModel team, MatchModel match) { return null; }

    // ===== GETTERS & SETTERS =====

    public List<PositionModel> getPreferredPositions() { return preferredPositions; }
    public void setPreferredPositions(List<PositionModel> preferredPositions) {
        this.preferredPositions = preferredPositions;
    }

    public int getJerseyNumber() { return jerseyNumber; }
    public void setJerseyNumber(int jerseyNumber) { this.jerseyNumber = jerseyNumber; }

    public String getPhotoUrl() { return photoUrl; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
}