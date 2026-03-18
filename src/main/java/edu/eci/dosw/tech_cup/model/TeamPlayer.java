import java.time.LocalDate;

public class TeamPlayer {

    private Long id;
    private Team team;
    private User user;
    private LocalDate joinDate;
    private String status;

    public TeamPlayer() {}

    public TeamPlayer(Long id, Team team, User user, LocalDate joinDate, String status) {
        this.id = id;
        this.team = team;
        this.user = user;
        this.joinDate = joinDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public LocalDate getJoinDate() { return joinDate; }
    public void setJoinDate(LocalDate joinDate) { this.joinDate = joinDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "TeamPlayer{id=" + id + ", user=" + (user != null ? user.getEmail() : null) + ", status='" + status + "'}";
    }
}