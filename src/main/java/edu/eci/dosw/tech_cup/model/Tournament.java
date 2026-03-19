package edu.eci.dosw.tech_cup.model;



import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Tournament {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String status;
    private Integer maxOfTeams;
    private BigDecimal teamCost;
    private Long id;
    private String name;
    private List<Team> teams;

    public Tournament() {
        this.teams = new ArrayList<>();
        this.status = TournamentStatus.DRAFT.name();
    }

    public Tournament(Long id, String name, LocalDateTime startDate, LocalDateTime endDate,
                      Integer maxOfTeams, BigDecimal teamCost) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxOfTeams = maxOfTeams;
        this.teamCost = teamCost;
        this.teams = new ArrayList<>();
        this.status = TournamentStatus.DRAFT.name();
    }

    public void startTournament() {
        if (!TournamentStatus.DRAFT.name().equals(this.status)) {
            throw new IllegalStateException("Tournament can only be started from DRAFT status");
        }
        this.status = TournamentStatus.ACTIVE.name();
    }

    public void finishTournament() {
        if (!TournamentStatus.IN_PROGRESS.name().equals(this.status)) {
            throw new IllegalStateException("Tournament can only be finished if IN_PROGRESS");
        }
        this.status = TournamentStatus.IN_PROGRESS.name();
    }

    public Boolean isActive() {
        return TournamentStatus.ACTIVE.name().equals(this.status)
                || TournamentStatus.IN_PROGRESS.name().equals(this.status);
    }

    public void addTeam(Team team) {
        if (team == null) throw new IllegalArgumentException("Team cannot be null");
        if (this.teams.size() >= this.maxOfTeams) {
            throw new IllegalStateException("Tournament has reached maximum number of teams");
        }
        this.teams.add(team);
    }

    public List<Match> generateMatches() {
        List<Match> matches = new ArrayList<>();
        for (int i = 0; i < teams.size(); i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                Match match = new Match();
                match.setTournament(this);
                match.setHomeTeam(teams.get(i));
                match.setAwayTeam(teams.get(j));
                match.setPhase(MatchPhase.GROUP_STAGE);
                matches.add(match);
            }
        }
        return matches;
    }

    // Getters and Setters
    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getMaxOfTeams() { return maxOfTeams; }
    public void setMaxOfTeams(Integer maxOfTeams) { this.maxOfTeams = maxOfTeams; }

    public BigDecimal getTeamCost() { return teamCost; }
    public void setTeamCost(BigDecimal teamCost) { this.teamCost = teamCost; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<Team> getTeams() { return teams; }
    public void setTeams(List<Team> teams) { this.teams = teams; }
}
