package edu.eci.dosw.tech_cup.model;

import java.time.LocalDate;

/**
 * Modelo de equipo para la capa de servicio y controladores.
 *
 * <p>Representa los datos de un equipo que se exponen a través de la API.
 * No contiene lógica de negocio compleja — esa vive en el dominio.</p>
 */
public class TeamModel {

    private Long id;
    private String name;
    private String logo;
    private String uniformColor;
    private boolean active;
    private LocalDate dateInscription;

    /** ID del torneo al que pertenece (para evitar serialización circular) */
    private Long tournamentId;

    /** ID del capitán (para evitar serialización circular) */
    private Long captainId;

    public TeamModel() {}

    // ===== GETTERS & SETTERS =====

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