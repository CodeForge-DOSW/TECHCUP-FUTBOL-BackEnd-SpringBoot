package edu.eci.dosw.tech_cup.model;

/**
 * Model representing a permission within the system.
 *
 * <p>A permission defines a specific action or resource access right.
 * This class mirrors the structure of {@code PermissionEntity} for
 * use in the service and controller layers.</p>
 */
public class PermissionModel {

    /** Unique permission identifier. */
    private Long permissionId;

    /** Unique name that identifies the permission (e.g. READ_USER, DELETE_POST). */
    private String name;

    /**
     * Creates an empty permission model.
     */
    public PermissionModel() {}

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
}