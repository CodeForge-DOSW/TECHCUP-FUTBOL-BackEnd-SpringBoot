public class Goal {

    private Long id;
    private Match match;
    private User player;
    private Integer minute;

    public Goal() {}

    public Goal(Long id, Match match, User player, Integer minute) {
        this.id = id;
        this.match = match;
        this.player = player;
        this.minute = minute;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Match getMatch() { return match; }
    public void setMatch(Match match) { this.match = match; }
    public User getPlayer() { return player; }
    public void setPlayer(User player) { this.player = player; }
    public Integer getMinute() { return minute; }
    public void setMinute(Integer minute) { this.minute = minute; }

    @Override
    public String toString() {
        return "Goal{id=" + id + ", player=" + (player != null ? player.getFullName() : null) + ", minute=" + minute + "}";
    }
}
