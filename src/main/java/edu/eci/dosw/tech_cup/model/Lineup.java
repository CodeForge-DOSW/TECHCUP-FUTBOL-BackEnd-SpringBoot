import java.util.ArrayList;
import java.util.List;

public class Lineup {

    private Long id;
    private Match match;
    private Team team;
    private String formation;
    private List<LineupPlayer> lineupPlayers = new ArrayList<>();

    public Lineup() {}

    public Lineup(Long id, Match match, Team team, String formation) {
        this.id = id;
        this.match = match;
        this.team = team;
        this.formation = formation;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
    public String getFormation() { return formation; }
    public void setFormation(String formation) { this.formation = formation; }
    public List<LineupPlayer> getLineupPlayers() { return lineupPlayers; }
    public void setLineupPlayers(List<LineupPlayer> lineupPlayers) { this.lineupPlayers = lineupPlayers; }

    @Override
    public String toString() {
        return "Lineup{id=" + id + ", team=" + (team != null ? team.getName() : null) + ", formation='" + formation + "'}";
    }
}