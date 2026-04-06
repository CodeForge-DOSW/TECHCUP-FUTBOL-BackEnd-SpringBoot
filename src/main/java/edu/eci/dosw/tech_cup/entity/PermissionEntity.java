package edu.eci.dosw.tech_cup.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

/**
 * JPA entity representing a permission within the platform.
 *
 * <p>A permission defines a specific action or resource access right.
 * Permissions are grouped into roles and are not assigned directly to users.</p>
 */
@Entity
@Table(name = "permissions")
public class PermissionEntity {

    /**
     * Unique identifier of the permission.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * Unique name that identifies the permission (e.g. READ_USER, DELETE_POST).
     */
    @NotBlank
    @Column(name = "name", nullable = false, unique = true, length = 20)
    private String name;

    /**
     * Set of roles that include this permission.
     * Mapped by the {@code permissions} field in {@link RoleEntity}.
     */
    @ManyToMany(mappedBy = "permissions")
    private Set<RoleEntity> roles = new HashSet<>();

    /**
     * Creates an empty permission entity required by JPA.
     */
    public PermissionEntity() {}

    /**
     * Returns the permission identifier.
     *
     * @return persisted permission id
     */
    public Long getPermissionId() { return permissionId; }

    /**
     * Updates the permission identifier.
     *
     * @param permissionId new permission id
     */
    public void setPermissionId(Long permissionId) { this.permissionId = permissionId; }

    /**
     * Returns the permission name.
     *
     * @return unique permission name
     */
    public String getName() { return name; }

    /**
     * Updates the permission name.
     *
     * @param name new permission name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the set of roles that include this permission.
     *
     * @return set of roles
     */
    public Set<RoleEntity> getRoles() { return roles; }

    /**
     * Updates the set of roles that include this permission.
     *
     * @param roles new set of roles
     */
    public void setRoles(Set<RoleEntity> roles) { this.roles = roles; }
}