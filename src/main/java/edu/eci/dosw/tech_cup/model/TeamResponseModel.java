package edu.eci.dosw.tech_cup.model;

import java.time.LocalDate;

/**
 * Team data transfer object used by the service and controller layers.
 *
 * <p>This is a flat representation of {@link TeamModel} designed to map to and
 * from {@code TeamEntity}. It exposes only relationship identifiers
 * ({@code tournamentId}, {@code captainId}) to avoid circular references in JSON responses.</p>
 */
public class TeamResponseModel {

    /** Unique identifier of the team. */
    private Long id;

    /** Display name of the team. */
    private String name;

    /** Team logo URL or reference. */
    private String logo;

    /** Main color of the team uniform. */
    private String uniformColor;

    /** Indicates whether the team is active. */
    private boolean active;

    /** Date when the team registration was created. */
    private LocalDate dateInscription;

    /** Identifier of the related tournament. */
    private Long tournamentId;

    /** Identifier of the related captain user. */
    private Long captainId;

    /**
     * Creates an empty team response model.
     */
    public TeamResponseModel() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public String getUniformColor() { return uniformColor; }
    public void setUniformColor(String uniformColor) { this.uniformColor = uniformColor; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public LocalDate getDateInscription() { return dateInscription; }
    public void setDateInscription(LocalDate dateInscription) { this.dateInscription = dateInscription; }

    public Long getTournamentId() { return tournamentId; }
    public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }

    public Long getCaptainId() { return captainId; }
    public void setCaptainId(Long captainId) { this.captainId = captainId; }
}
