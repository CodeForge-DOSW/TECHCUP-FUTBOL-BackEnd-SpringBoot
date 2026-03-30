package edu.eci.dosw.tech_cup.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * JPA entity that represents the membership of a player in a team.
 *
 * <p>Maps to the {@code team_player} join table. Each record links
 * a user to a team with a join date and active status.</p>
 *
 * <p>Column definitions explicitly match the PostgreSQL schema types:
 * team_player_id SERIAL (int4), team_id INTEGER (int4), user_id BIGINT.</p>
 */
@Entity
@Table(name = "team_player",
        uniqueConstraints = @UniqueConstraint(columnNames = {"team_id", "user_id"}))
public class TeamPlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_player_id", columnDefinition = "serial")
    private Integer teamPlayerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id", nullable = false, columnDefinition = "integer")
    private TeamEntity team;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "join_date", nullable = false)
    private LocalDate joinDate = LocalDate.now();

    @Column(name = "status", nullable = false)
    private Boolean status = true;

    public TeamPlayerEntity() {}

    public TeamPlayerEntity(TeamEntity team, UserEntity user) {
        this.team = team;
        this.user = user;
        this.joinDate = LocalDate.now();
        this.status = true;
    }

    public Integer getTeamPlayerId() { return teamPlayerId; }
    public void setTeamPlayerId(Integer teamPlayerId) { this.teamPlayerId = teamPlayerId; }

    public TeamEntity getTeam() { return team; }
    public void setTeam(TeamEntity team) { this.team = team; }

    public UserEntity getUser() { return user; }
    public void setUser(UserEntity user) { this.user = user; }

    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}