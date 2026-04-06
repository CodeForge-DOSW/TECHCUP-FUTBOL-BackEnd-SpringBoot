package edu.eci.dosw.tech_cup.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * JPA entity representing a role within the platform.
 *
 * <p>A role groups a set of permissions and can be assigned to multiple users.
 * This entity owns the join table {@code role_permissions} and is referenced
 * by {@code user_roles} through the {@code UserEntity} owner side.</p>
 */
@Entity
@Table(name = "role")
public class RoleEntity {

    /**
     * Unique identifier of the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    /**
     * Unique name that identifies the role (e.g. ADMIN, USER).
     */
    @NotBlank
    @Column(name = "role_name", nullable = false, unique = true, length = 20)
    private String roleName;

    /**
     * Set of users that have been assigned this role.
     * Mapped by the {@code roles} field in {@link UserEntity}.
     */
    @ManyToMany(mappedBy = "roles")
    private Set<UserEntity> users = new HashSet<>();

    /**
     * Set of permissions granted by this role.
     * Owns the join table {@code role_permissions} with columns
     * {@code role_id} and {@code permission_id}.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<PermissionEntity> permissions = new HashSet<>();

    /**
     * Creates an empty role entity required by JPA.
     */
    public RoleEntity() {}

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
     * Returns the set of users assigned to this role.
     *
     * @return set of users
     */
    public Set<UserEntity> getUsers() { return users; }

    /**
     * Updates the set of users assigned to this role.
     *
     * @param users new set of users
     */
    public void setUsers(Set<UserEntity> users) { this.users = users; }

    /**
     * Returns the set of permissions granted by this role.
     *
     * @return set of permissions
     */
    public Set<PermissionEntity> getPermissions() { return permissions; }

    /**
     * Updates the set of permissions granted by this role.
     *
     * @param permissions new set of permissions
     */
    public void setPermissions(Set<PermissionEntity> permissions) { this.permissions = permissions; }
}