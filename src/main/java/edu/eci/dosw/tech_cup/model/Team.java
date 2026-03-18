import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Team {

    private Long id;
    private String name;
    private String logo;
    private String uniformColor;
    private User captain;
    private String status;

    private List<TeamPlayer> teamPlayers = new ArrayList<>();
    private List<TeamInvitation> teamInvitations = new ArrayList<>();

    public Team() {}

    public Team(Long id, String name, String logo, String uniformColor, User captain, String status) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.uniformColor = uniformColor;
        this.captain = captain;
        this.status = status;
    }

    public void addPlayer(User user) {
        TeamPlayer tp = new TeamPlayer();
        tp.setTeam(this);
        tp.setUser(user);
        tp.setStatus("ACTIVE");
        tp.setJoinDate(LocalDate.now());
        teamPlayers.add(tp);
    }

    public void removePlayer(Long userId) {
        teamPlayers.removeIf(tp -> tp.getUser() != null && tp.getUser().getId().equals(userId));
    }

    public List<User> getActivePlayers() {
        return teamPlayers.stream()
                .filter(tp -> "ACTIVE".equals(tp.getStatus()))
                .map(TeamPlayer::getUser)
                .toList();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }
    public String getUniformColor() { return uniformColor; }
    public void setUniformColor(String uniformColor) { this.uniformColor = uniformColor; }
    public User getCaptain() { return captain; }
    public void setCaptain(User captain) { this.captain = captain; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<TeamPlayer> getTeamPlayers() { return teamPlayers; }
    public void setTeamPlayers(List<TeamPlayer> teamPlayers) { this.teamPlayers = teamPlayers; }
    public List<TeamInvitation> getTeamInvitations() { return teamInvitations; }
    public void setTeamInvitations(List<TeamInvitation> teamInvitations) { this.teamInvitations = teamInvitations; }

    @Override
    public String toString() {
        return "Team{id=" + id + ", name='" + name + "', status='" + status + "'}";
    }
}