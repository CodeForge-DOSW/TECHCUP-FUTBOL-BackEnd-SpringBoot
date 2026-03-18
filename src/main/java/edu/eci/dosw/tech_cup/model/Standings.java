public class Standings {

    private Long id;
    private Tournament tournament;
    private Team team;
    private Integer matchesPlayed;
    private Integer matchesWon;
    private Integer matchesDrawn;
    private Integer matchesLost;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalDifference;
    private Integer points;

    public Standings() {}

    public Standings(Long id, Tournament tournament, Team team) {
        this.id = id;
        this.tournament = tournament;
        this.team = team;
        this.matchesPlayed = 0;
        this.matchesWon = 0;
        this.matchesDrawn = 0;
        this.matchesLost = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
        this.goalDifference = 0;
        this.points = 0;
    }

    public void recalculate() {
        this.goalDifference = (goalsFor != null && goalsAgainst != null) ? goalsFor - goalsAgainst : 0;
        this.points = ((matchesWon != null ? matchesWon : 0) * 3) + (matchesDrawn != null ? matchesDrawn : 0);
        this.matchesPlayed = (matchesWon != null ? matchesWon : 0)
                + (matchesDrawn != null ? matchesDrawn : 0)
                + (matchesLost != null ? matchesLost : 0);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
    public Integer getMatchesPlayed() { return matchesPlayed; }
    public void setMatchesPlayed(Integer matchesPlayed) { this.matchesPlayed = matchesPlayed; }
    public Integer getMatchesWon() { return matchesWon; }
    public void setMatchesWon(Integer matchesWon) { this.matchesWon = matchesWon; }
    public Integer getMatchesDrawn() { return matchesDrawn; }
    public void setMatchesDrawn(Integer matchesDrawn) { this.matchesDrawn = matchesDrawn; }
    public Integer getMatchesLost() { return matchesLost; }
    public void setMatchesLost(Integer matchesLost) { this.matchesLost = matchesLost; }
    public Integer getGoalsFor() { return goalsFor; }
    public void setGoalsFor(Integer goalsFor) { this.goalsFor = goalsFor; }
    public Integer getGoalsAgainst() { return goalsAgainst; }
    public void setGoalsAgainst(Integer goalsAgainst) { this.goalsAgainst = goalsAgainst; }
    public Integer getGoalDifference() { return goalDifference; }
    public void setGoalDifference(Integer goalDifference) { this.goalDifference = goalDifference; }
    public Integer getPoints() { return points; }
    public void setPoints(Integer points) { this.points = points; }

    @Override
    public String toString() {
        return "Standings{team=" + (team != null ? team.getName() : null) + ", points=" + points + ", gd=" + goalDifference + "}";
    }
}