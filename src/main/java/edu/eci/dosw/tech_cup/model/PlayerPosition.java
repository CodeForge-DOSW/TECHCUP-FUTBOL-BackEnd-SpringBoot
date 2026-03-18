public class PlayerPosition {

    private Long id;
    private User user;
    private Position position;

    public PlayerPosition() {}

    public PlayerPosition(Long id, User user, Position position) {
        this.id = id;
        this.user = user;
        this.position = position;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Position getPosition() { return position; }
    public void setPosition(Position position) { this.position = position; }

    @Override
    public String toString() {
        return "PlayerPosition{id=" + id + ", user=" + (user != null ? user.getEmail() : null) + ", position=" + position + "}";
    }
}
