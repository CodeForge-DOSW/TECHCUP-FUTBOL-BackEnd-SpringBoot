package edu.eci.dosw.tech_cup.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * JPA entity that represents a tournament managed by the application.
 *
 * <p>Stores the main planning and configuration data of a tournament,
 * including its date range, capacity, registration cost, and lifecycle
 * status.</p>
 */
@Entity
@Table(name = "tournament")
public class TournamentEntity {

    /**
     * Unique identifier of the tournament.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_id")
    private Long tournamentId;

    /**
     * Public name used to identify the tournament.
     */
    @NotBlank
    @Column(name = "name", nullable = false, length = 150)
    private String name;

    /**
     * Scheduled start date of the tournament.
     */
    @Column(name = "start_date")
    private LocalDate startDate;

    /**
     * Scheduled end date of the tournament.
     */
    @Column(name = "end_date")
    private LocalDate endDate;

    /**
     * Maximum number of teams that can participate.
     */
    @Column(name = "number_of_teams")
    private Integer numberOfTeams;

    /**
     * Registration cost charged per team.
     */
    @Column(name = "team_cost", precision = 10, scale = 2)
    private BigDecimal teamCost;


    /**
     * Current lifecycle status of the tournament.
     */
    @NotBlank
    @Column(name = "status", nullable = false, length = 20)
    private String status = "draft";

    /**
     * Creates an empty tournament entity required by JPA.
     */
    public TournamentEntity() {}

    /**
     * Creates a tournament with its main planning attributes.
     *
     * @param name public tournament name
     * @param startDate planned start date
     * @param endDate planned end date
     * @param numberOfTeams team capacity
     * @param teamCost registration cost per team
     */
    public TournamentEntity(String name, LocalDate startDate, LocalDate endDate,
                            Integer numberOfTeams, BigDecimal teamCost) {
        this.name          = name;
        this.startDate     = startDate;
        this.endDate       = endDate;
        this.numberOfTeams = numberOfTeams;
        this.teamCost      = teamCost;
        this.status        = "draft";
    }

    /**
     * Returns the tournament identifier.
     *
     * @return persisted tournament id
     */
    public Long getTournamentId() { return tournamentId; }

    /**
     * Updates the tournament identifier.
     *
     * @param tournamentId new tournament id
     */
    public void setTournamentId(Long tournamentId) { this.tournamentId = tournamentId; }

    /**
     * Returns the tournament name.
     *
     * @return tournament name
     */
    public String getName() { return name; }

    /**
     * Updates the tournament name.
     *
     * @param name new tournament name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the configured start date.
     *
     * @return tournament start date
     */
    public LocalDate getStartDate() { return startDate; }

    /**
     * Updates the start date.
     *
     * @param startDate new tournament start date
     */
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    /**
     * Returns the configured end date.
     *
     * @return tournament end date
     */
    public LocalDate getEndDate() { return endDate; }

    /**
     * Updates the end date.
     *
     * @param endDate new tournament end date
     */
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    /**
     * Returns the maximum number of teams.
     *
     * @return allowed team capacity
     */
    public Integer getNumberOfTeams() { return numberOfTeams; }

    /**
     * Updates the maximum number of teams.
     *
     * @param numberOfTeams new team capacity
     */
    public void setNumberOfTeams(Integer numberOfTeams) { this.numberOfTeams = numberOfTeams; }

    /**
     * Returns the registration cost per team.
     *
     * @return team registration cost
     */
    public BigDecimal getTeamCost() { return teamCost; }

    /**
     * Updates the registration cost per team.
     *
     * @param teamCost new cost value
     */
    public void setTeamCost(BigDecimal teamCost) { this.teamCost = teamCost; }

    /**
     * Returns the current tournament status.
     *
     * @return lifecycle status value
     */
    public String getStatus() { return status; }

    /**
     * Updates the tournament status.
     *
     * @param status new lifecycle status
     */
    public void setStatus(String status) { this.status = status; }
}
