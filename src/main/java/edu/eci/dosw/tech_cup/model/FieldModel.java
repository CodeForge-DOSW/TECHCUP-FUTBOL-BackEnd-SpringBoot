package edu.eci.dosw.tech_cup.model;

import java.util.Objects;

/**
 * Represents a field where tournament matches are played.
 *
 * <p>This model identifies the physical location of matches and is used
 * to organize match logistics.</p>
 */
public class FieldModel {

    /** Unique identifier of the field. */
    private Long id;

    /** Field name. */
    private String name;

    /** Field location. */
    private String location;

    // ===================== Domain methods =====================

    /**
     * Returns the field identifier.
     *
     * @return field id
     */
    public Long getId() { return id; }

    /**
     * Returns the field name.
     *
     * @return field name
     */
    public String getName() { return name; }

    /**
     * Sets the field name.
     *
     * @param name new name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the field location.
     *
     * @return field location
     */
    public String getLocation() { return location; }

    /**
     * Sets the field location.
     *
     * @param location new location
     */
    public void setLocation(String location) { this.location = location; }

    /**
     * Indicates whether the field is valid.
     *
     * @return {@code true} if both name and location are defined
     */
    public boolean isValid() {
        return false;
    }

    /**
     * Compares whether two fields represent the same venue.
     *
     * @param other other field
     * @return {@code true} if both fields represent the same venue
     */
    public boolean sameField(FieldModel other) {
        return false;
    }
}
