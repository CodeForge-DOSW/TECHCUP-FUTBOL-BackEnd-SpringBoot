package edu.eci.dosw.tech_cup.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Model representing a role within the system.
 *
 * <p>A role groups a set of permissions and can be assigned to
 * multiple users. This class mirrors the structure of
 * {@code RoleEntity} for use in the service and controller layers.</p>
 */
public class RoleModel {

    /** Unique role identifier. */
    private Long roleId;

    /** Unique name that identifies the role (e.g. ADMIN, USER). */
    private String roleName;

    /** Set of permissions granted by this role. */
    private Set<PermissionModel> permissions = new HashSet<>();

    /**
     * Creates an empty role model.
     */
    public RoleModel() {}

    /**
     * Returns the role identifier.
     *
     * @return persisted role id
     */
    public Long getRoleId() { return roleId; }

    /**
     * Updates the role identifier.
     *
     * @param roleId new role id
     */
    public void setRoleId(Long roleId) { this.roleId = roleId; }

    /**
     * Returns the role name.
     *
     * @return unique role name
     */
    public String getRoleName() { return roleName; }

    /**
     * Updates the role name.
     *
     * @param roleName new role name
     */
    public void setRoleName(String roleName) { this.roleName = roleName; }

    /**
     * Returns the set of permissions granted by this role.
     *
     * @return set of permissions
     */
    public Set<PermissionModel> getPermissions() { return permissions; }

    /**
     * Updates the set of permissions granted by this role.
     *
     * @param permissions new set of permissions
     */
    public void setPermissions(Set<PermissionModel> permissions) { this.permissions = permissions; }
}