import java.util.ArrayList;
import java.util.List;

public class SportsProfile {

    private Long id;
    private User user;
    private Integer jerseyNumber;
    private Boolean availableForTeam;
    private List<PlayerPosition> playerPositions = new ArrayList<>();

    public SportsProfile() {}

    public SportsProfile(Long id, User user, Integer jerseyNumber, Boolean availableForTeam) {
        this.id = id;
        this.user = user;
        this.jerseyNumber = jerseyNumber;
        this.availableForTeam = availableForTeam;
    }

    public List<Position> getPositions() {
        return playerPositions.stream()
                .map(PlayerPosition::getPosition)
                .toList();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Integer getJerseyNumber() { return jerseyNumber; }
    public void setJerseyNumber(Integer jerseyNumber) { this.jerseyNumber = jerseyNumber; }
    public Boolean getAvailableForTeam() { return availableForTeam; }
    public void setAvailableForTeam(Boolean availableForTeam) { this.availableForTeam = availableForTeam; }
    public List<PlayerPosition> getPlayerPositions() { return playerPositions; }
    public void setPlayerPositions(List<PlayerPosition> playerPositions) { this.playerPositions = playerPositions; }

    @Override
    public String toString() {
        return "SportsProfile{id=" + id + ", jerseyNumber=" + jerseyNumber + ", availableForTeam=" + availableForTeam + "}";
    }
}
