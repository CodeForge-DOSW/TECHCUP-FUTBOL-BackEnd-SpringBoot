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
     * Tournament to which the team belongs.
     * Relation N:1 — many teams belong to one tournament.
     * FK: tournament_id — ON DELETE CASCADE.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tournament_id", nullable = true)
    private TournamentEntity tournament;

    /**
     * User acting as team captain.
     * Relation 1:1 — one user can be captain of only one team.
     * FK: captain_id — UNIQUE — ON DELETE SET NULL.
     * Nullable: if the user is deleted, the team remains without a captain.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "captain_id", nullable = true, unique = true)
    private UserEntity captain;

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
     * @param tournament related tournament entity
     * @param captain related captain user entity
     */
    public TeamEntity(String name, String logo, String uniformColor,
                      TournamentEntity tournament, UserEntity captain) {
        this.name            = name;
        this.logo            = logo;
        this.uniformColor    = uniformColor;
        this.tournament      = tournament;
        this.captain         = captain;
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
     * Returns the related tournament entity.
     *
     * @return tournament entity
     */
    public TournamentEntity getTournament() { return tournament; }

    /**
     * Updates the related tournament entity.
     *
     * @param tournament tournament entity to associate
     */
    public void setTournament(TournamentEntity tournament) { this.tournament = tournament; }

    /**
     * Returns the captain user entity.
     *
     * @return captain user entity
     */
    public UserEntity getCaptain() { return captain; }

    /**
     * Updates the captain user entity.
     *
     * @param captain captain user entity
     */
    public void setCaptain(UserEntity captain) { this.captain = captain; }

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