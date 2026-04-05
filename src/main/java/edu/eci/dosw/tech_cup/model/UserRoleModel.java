package edu.eci.dosw.tech_cup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

/**
 * Base abstract model for users within the system.
 *
 * <p>This class defines the shared attributes and behaviors for all user roles,
 * such as players, administrators, organizers, and referees. Its profile fields
 * intentionally mirror the structure of {@code UserEntity} so mappers can
 * convert between layers without extra transformation logic.</p>
 */
public abstract class UserRoleModel {

    /** Unique user identifier. */
    protected Long id;

    /** Display name of the user. */
    protected String name;

    /** User email address. */
    protected String email;

    /** User password value. */
    protected String password;

    /** Indicates whether the user is active. */
    protected boolean status;

    // ===== Profile fields aligned with UserEntity =====

    /** User first name. */
    protected String firstName;

    /** User last name. */
    protected String lastName;

    /** Identification document or institutional identifier. */
    protected String identification;

    /** User birth date. */
    protected LocalDate dateBirth;

    /** User gender value. */
    protected String gender;

    /** User category such as student, professor, graduate, administrative, or family. */
    protected String userType;

    // ===================== Getters and setters =====================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    @JsonIgnore
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @JsonIgnore
    public String getPassword() { return password; }
    @JsonProperty
    public void setPassword(String password) { this.password = password; }

    public boolean isStatus() { return status; }
    public void setStatus(boolean status) { this.status = status; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getIdentification() { return identification; }
    public void setIdentification(String identification) { this.identification = identification; }

    public LocalDate getDateBirth() { return dateBirth; }
    public void setDateBirth(LocalDate dateBirth) { this.dateBirth = dateBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }

    // ===================== Domain methods =====================

    /**
     * Marks the user as inactive.
     */
    public void deactivate() { this.status = false; }

    /**
     * Indicates whether the user is currently active.
     *
     * @return {@code true} when the user is active; {@code false} otherwise
     */
    @JsonIgnore
    public boolean isActive() { return status; }
}
