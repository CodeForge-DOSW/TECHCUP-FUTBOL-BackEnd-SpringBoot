public class LineupPlayer {

    private Long id;
    private Lineup lineup;
    private User player;
    private LineupType type;
    private Double fieldPositionX;
    private Double fieldPositionY;

    public LineupPlayer() {}

    public LineupPlayer(Long id, Lineup lineup, User player, LineupType type,
                        Double fieldPositionX, Double fieldPositionY) {
        this.id = id;
        this.lineup = lineup;
        this.player = player;
        this.type = type;
        this.fieldPositionX = fieldPositionX;
        this.fieldPositionY = fieldPositionY;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Lineup getLineup() { return lineup; }
    public void setLineup(Lineup lineup) { this.lineup = lineup; }
    public User getPlayer() { return player; }
    public void setPlayer(User player) { this.player = player; }
    public LineupType getType() { return type; }
    public void setType(LineupType type) { this.type = type; }
    public Double getFieldPositionX() { return fieldPositionX; }
    public void setFieldPositionX(Double fieldPositionX) { this.fieldPositionX = fieldPositionX; }
    public Double getFieldPositionY() { return fieldPositionY; }
    public void setFieldPositionY(Double fieldPositionY) { this.fieldPositionY = fieldPositionY; }

    @Override
    public String toString() {
        return "LineupPlayer{id=" + id + ", player=" + (player != null ? player.getEmail() : null) + ", type=" + type + "}";
    }
}
