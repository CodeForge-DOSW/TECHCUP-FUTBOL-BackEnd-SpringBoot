
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Tournament {

    private Long id;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer numberOfTeams;
    private BigDecimal teamCost;
    private TournamentStatus status;

    private List<TeamTournament> teamTournaments = new ArrayList<>();
    private List<Rules> rules = new ArrayList<>();
    private List<ImportantDate> importantDates = new ArrayList<>();
    private List<Match> matches = new ArrayList<>();
    private List<Standings> standings = new ArrayList<>();

    public Tournament() {}

    public Tournament(Long id, String name, LocalDate startDate, LocalDate endDate,
                      Integer numberOfTeams, BigDecimal teamCost, TournamentStatus status) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfTeams = numberOfTeams;
        this.teamCost = teamCost;
        this.status = status;
    }

    public boolean canModify() {
        return status == TournamentStatus.DRAFT || status == TournamentStatus.ACTIVE;
    }

    public boolean canDelete() {
        return status == TournamentStatus.DRAFT;
    }

    public void activate() {
        if (status != TournamentStatus.DRAFT) {
            throw new IllegalStateException("Only DRAFT tournaments can be activated.");
        }
        this.status = TournamentStatus.ACTIVE;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public Integer getNumberOfTeams() { return numberOfTeams; }
    public void setNumberOfTeams(Integer numberOfTeams) { this.numberOfTeams = numberOfTeams; }
    public BigDecimal getTeamCost() { return teamCost; }
    public void setTeamCost(BigDecimal teamCost) { this.teamCost = teamCost; }
    public TournamentStatus getStatus() { return status; }
    public void setStatus(TournamentStatus status) { this.status = status; }
    public List<TeamTournament> getTeamTournaments() { return teamTournaments; }
    public void setTeamTournaments(List<TeamTournament> teamTournaments) { this.teamTournaments = teamTournaments; }
    public List<Rules> getRules() { return rules; }
    public void setRules(List<Rules> rules) { this.rules = rules; }
    public List<ImportantDate> getImportantDates() { return importantDates; }
    public void setImportantDates(List<ImportantDate> importantDates) { this.importantDates = importantDates; }
    public List<Match> getMatches() { return matches; }
    public void setMatches(List<Match> matches) { this.matches = matches; }
    public List<Standings> getStandings() { return standings; }
    public void setStandings(List<Standings> standings) { this.standings = standings; }

    @Override
    public String toString() {
        return "Tournament{id=" + id + ", name='" + name + "', status=" + status + "}";
    }
}
