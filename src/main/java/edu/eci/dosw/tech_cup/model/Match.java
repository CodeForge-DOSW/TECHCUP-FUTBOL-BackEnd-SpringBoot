import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Match {

    private Long id;
    private Tournament tournament;
    private Team homeTeam;
    private Team awayTeam;
    private LocalDate date;
    private LocalTime time;
    private Field field;
    private MatchPhase phase;
    private MatchStatus status;

    private RefereeMatch refereeMatch;
    private List<Lineup> lineups = new ArrayList<>();
    private List<Goal> goals = new ArrayList<>();
    private List<Card> cards = new ArrayList<>();

    public Match() {}

    public Match(Long id, Tournament tournament, Team homeTeam, Team awayTeam,
                 LocalDate date, LocalTime time, Field field, MatchPhase phase, MatchStatus status) {
        this.id = id;
        this.tournament = tournament;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.date = date;
        this.time = time;
        this.field = field;
        this.phase = phase;
        this.status = status;
    }

    public String getScore() {
        if (homeTeam == null || awayTeam == null) return "0 - 0";
        List<User> homePlayers = homeTeam.getActivePlayers();
        List<User> awayPlayers = awayTeam.getActivePlayers();
        long homeGoals = goals.stream().filter(g -> g.getPlayer() != null && homePlayers.contains(g.getPlayer())).count();
        long awayGoals = goals.stream().filter(g -> g.getPlayer() != null && awayPlayers.contains(g.getPlayer())).count();
        return homeGoals + " - " + awayGoals;
    }

    public boolean isFinished() {
        return status == MatchStatus.FINISHED;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public Team getHomeTeam() { return homeTeam; }
    public void setHomeTeam(Team homeTeam) { this.homeTeam = homeTeam; }
    public Team getAwayTeam() { return awayTeam; }
    public void setAwayTeam(Team awayTeam) { this.awayTeam = awayTeam; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getTime() { return time; }
    public void setTime(LocalTime time) { this.time = time; }
    public Field getField() { return field; }
    public void setField(Field field) { this.field = field; }
    public MatchPhase getPhase() { return phase; }
    public void setPhase(MatchPhase phase) { this.phase = phase; }
    public MatchStatus getStatus() { return status; }
    public void setStatus(MatchStatus status) { this.status = status; }
    public RefereeMatch getRefereeMatch() { return refereeMatch; }
    public void setRefereeMatch(RefereeMatch refereeMatch) { this.refereeMatch = refereeMatch; }
    public List<Lineup> getLineups() { return lineups; }
    public void setLineups(List<Lineup> lineups) { this.lineups = lineups; }
    public List<Goal> getGoals() { return goals; }
    public void setGoals(List<Goal> goals) { this.goals = goals; }
    public List<Card> getCards() { return cards; }
    public void setCards(List<Card> cards) { this.cards = cards; }

    @Override
    public String toString() {
        return "Match{id=" + id + ", homeTeam=" + (homeTeam != null ? homeTeam.getName() : null) +
                " vs awayTeam=" + (awayTeam != null ? awayTeam.getName() : null) + ", status=" + status + "}";
    }
}
