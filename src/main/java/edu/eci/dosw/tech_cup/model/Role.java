
import java.util.List;

public class Role {

    private Long id;
    private RoleType roleName;

    public Role() {}

    public Role(Long id, RoleType roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public List<String> getPermissions() {
        return switch (roleName) {
            case ADMINISTRATOR -> List.of("CREATE_TOURNAMENT", "DELETE_TOURNAMENT", "MANAGE_USERS", "VIEW_AUDIT_LOG");
            case ORGANIZER     -> List.of("CREATE_TOURNAMENT", "EDIT_TOURNAMENT", "MANAGE_MATCHES", "ASSIGN_REFEREE");
            case CAPTAIN       -> List.of("MANAGE_TEAM", "INVITE_PLAYERS", "SUBMIT_LINEUP");
            case REFEREE       -> List.of("MANAGE_MATCH_EVENTS", "ADD_GOALS", "ADD_CARDS");
            case PLAYER        -> List.of("VIEW_TOURNAMENTS", "VIEW_MATCHES", "VIEW_STANDINGS");
        };
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public RoleType getRoleName() { return roleName; }
    public void setRoleName(RoleType roleName) { this.roleName = roleName; }

    @Override
    public String toString() {
        return "Role{id=" + id + ", roleName=" + roleName + "}";
    }
}