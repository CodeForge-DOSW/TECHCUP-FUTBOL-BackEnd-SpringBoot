
public class UserRole {

    private Long id;
    private User user;
    private Role role;

    public UserRole() {}

    public UserRole(Long id, User user, Role role) {
        this.id = id;
        this.user = user;
        this.role = role;
    }

    public boolean isValid() {
        return user != null && role != null && user.isActive();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    @Override
    public String toString() {
        return "UserRole{id=" + id + ", user=" + (user != null ? user.getEmail() : null) + ", role=" + role + "}";
    }
}