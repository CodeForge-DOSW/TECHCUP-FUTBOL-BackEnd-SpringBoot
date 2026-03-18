public class TeamTournament {

    private Long id;
    private Team team;
    private Tournament tournament;
    private String registrationStatus;

    public TeamTournament() {}

    public TeamTournament(Long id, Team team, Tournament tournament, String registrationStatus) {
        this.id = id;
        this.team = team;
        this.tournament = tournament;
        this.registrationStatus = registrationStatus;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public String getRegistrationStatus() { return registrationStatus; }
    public void setRegistrationStatus(String registrationStatus) { this.registrationStatus = registrationStatus; }

    @Override
    public String toString() {
        return "TeamTournament{id=" + id + ", team=" + (team != null ? team.getName() : null) + ", registrationStatus='" + registrationStatus + "'}";
    }
}