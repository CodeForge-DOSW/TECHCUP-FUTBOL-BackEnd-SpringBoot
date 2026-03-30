package edu.eci.dosw.tech_cup.model;

import java.util.Objects;

/**
 * Represents an academic program within the institution.
 *
 * <p>This model identifies the academic background associated with users and is
 * used to validate system rules such as eligibility and team composition.</p>
 */
public class CareerModel {

    /** Unique identifier of the academic program. */
    private Long id;

    /** Name of the academic program. */
    private String name;

    // ===================== Domain methods =====================

    /**
     * Returns the academic program identifier.
     *
     * @return program id
     */
    public Long getId() { return id; }

    /**
     * Returns the academic program name.
     *
     * @return program name
     */
    public String getName() { return name; }

    /**
     * Sets the academic program name.
     *
     * @param name new program name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Indicates whether the academic program is valid.
     *
     * @return {@code true} if the program has a defined name
     */
    public boolean isValid() { return false; }

    /**
     * Compares whether two academic programs represent the same career.
     *
     * @param other other academic program
     * @return {@code true} if both objects represent the same program
     */
    public boolean sameCareer(CareerModel other) {
        return false;
    }
}
