import java.util.ArrayList;
import java.util.List;

public class Position {

    private Long id;
    private String positionName;
    private List<PlayerPosition> playerPositions = new ArrayList<>();

    public Position() {}

    public Position(Long id, String positionName) {
        this.id = id;
        this.positionName = positionName;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPositionName() { return positionName; }
    public void setPositionName(String positionName) { this.positionName = positionName; }
    public List<PlayerPosition> getPlayerPositions() { return playerPositions; }
    public void setPlayerPositions(List<PlayerPosition> playerPositions) { this.playerPositions = playerPositions; }

    @Override
    public String toString() {
        return "Position{id=" + id + ", positionName='" + positionName + "'}";
    }
}
