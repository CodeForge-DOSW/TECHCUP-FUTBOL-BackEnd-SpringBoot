package edu.eci.dosw.tech_cup.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

/**
 * JPA entity that represents a team registered in the platform.
 *
 * <p>A team belongs to a tournament, references a captain user, and stores
 * operational data such as visual identity, registration date, and active
 * status.</p>
 */
@Entity
@Table(name = "team")
public class TeamEntity {

    /**
     * Unique identifier of the team.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    /**
     * Identifier of the tournament to which the team belongs.
     */
    @Column(name = "tournament_id")
    private Integer tournamentId;


    /**
     * Identifier of the user acting as team captain.
     */
    @Column(name = "captain_id", unique = true)
    private Long captainId;

    /**
     * Display name of the team.
     */
    @NotBlank
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * Optional logo URL or path associated with the team.
     */
    @Column(name = "logo")
    private String logo;

    /**
     * Main uniform color used to identify the team.
     */
    @Column(name = "uniform_color", length = 50)
    private String uniformColor;

    /**
     * Logical status of the team. {@code true} means active.
     */
    @Column(name = "status", nullable = false)
    private Boolean status = true;

    /**
     * Date on which the team registration was created.
     */
    @Column(name = "date_inscription", nullable = false)
    private LocalDate dateInscription = LocalDate.now();

    /**
     * Creates an empty team entity required by JPA.
     */
    public TeamEntity() {}

    /**
     * Creates a team with the minimum functional data required by the domain.
     *
     * @param name display name of the team
     * @param logo logo URL or path
     * @param uniformColor primary uniform color
     * @param tournamentId related tournament identifier
     * @param captainId related captain user identifier
     */
    public TeamEntity(String name, String logo, String uniformColor,
                      Integer tournamentId, Long captainId) {
        this.name            = name;
        this.logo            = logo;
        this.uniformColor    = uniformColor;
        this.tournamentId    = tournamentId;
        this.captainId       = captainId;
        this.status          = true;
        this.dateInscription = LocalDate.now();
    }

    /**
     * Returns the team identifier.
     *
     * @return persisted team id
     */
    public Long getTeamId() { return teamId; }

    /**
     * Updates the team identifier.
     *
     * @param teamId new team id
     */
    public void setTeamId(Long teamId) { this.teamId = teamId; }

    /**
     * Returns the related tournament identifier.
     *
     * @return tournament id
     */
    public Integer getTournamentId() { return tournamentId; }

    /**
     * Updates the related tournament identifier.
     *
     * @param tournamentId tournament id to associate
     */
    public void setTournamentId(Integer tournamentId) { this.tournamentId = tournamentId; }

    /**
     * Returns the captain user identifier.
     *
     * @return captain user id
     */
    public Long getCaptainId() { return captainId; }

    /**
     * Updates the captain user identifier.
     *
     * @param captainId captain user id
     */
    public void setCaptainId(Long captainId) { this.captainId = captainId; }

    /**
     * Returns the team name.
     *
     * @return team name
     */
    public String getName() { return name; }

    /**
     * Updates the team name.
     *
     * @param name new team name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the configured team logo.
     *
     * @return logo URL or path
     */
    public String getLogo() { return logo; }

    /**
     * Updates the team logo.
     *
     * @param logo new logo URL or path
     */
    public void setLogo(String logo) { this.logo = logo; }

    /**
     * Returns the uniform color.
     *
     * @return uniform color
     */
    public String getUniformColor() { return uniformColor; }

    /**
     * Updates the uniform color.
     *
     * @param uniformColor new uniform color
     */
    public void setUniformColor(String uniformColor) { this.uniformColor = uniformColor; }

    /**
     * Returns the logical status of the team.
     *
     * @return {@code true} if active, {@code false} otherwise
     */
    public Boolean getStatus() { return status; }

    /**
     * Updates the logical status of the team.
     *
     * @param status new active flag
     */
    public void setStatus(Boolean status) { this.status = status; }

    /**
     * Returns the registration date.
     *
     * @return team inscription date
     */
    public LocalDate getDateInscription() { return dateInscription; }

    /**
     * Updates the registration date.
     *
     * @param dateInscription new inscription date
     */
    public void setDateInscription(LocalDate dateInscription) { this.dateInscription = dateInscription; }
}
