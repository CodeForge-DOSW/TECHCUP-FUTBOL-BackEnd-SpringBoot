public class RefereeMatch {

    private Long id;
    private Match match;
    private User referee;

    public RefereeMatch() {}

    public RefereeMatch(Long id, Match match, User referee) {
        this.id = id;
        this.match = match;
        this.referee = referee;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
    public User getReferee() { return referee; }
    public void setReferee(User referee) { this.referee = referee; }

    @Override
    public String toString() {
        return "RefereeMatch{id=" + id + ", referee=" + (referee != null ? referee.getEmail() : null) + "}";
    }
}
