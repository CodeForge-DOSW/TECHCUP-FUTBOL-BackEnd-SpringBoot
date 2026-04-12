package edu.eci.dosw.tech_cup.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    /** Set of roles assigned to this user. */
    protected Set<RoleModel> roles = new HashSet<>();

    // ===================== Getters and setters =====================

    /**
     * Returns the user identifier.
     *
     * @return persisted user id
     */
    public Long getId() { return id; }

    /**
     * Updates the user identifier.
     *
     * @param id new user id
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Returns the display name of the user.
     *
     * @return user name
     */
    @JsonIgnore
    public String getName() { return name; }

    /**
     * Updates the display name of the user.
     *
     * @param name new user name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the user email address.
     *
     * @return email address
     */
    public String getEmail() { return email; }

    /**
     * Updates the user email address.
     *
     * @param email new email address
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Returns the stored password value.
     *
     * @return password data
     */
    @JsonIgnore
    public String getPassword() { return password; }

    /**
     * Updates the stored password value.
     *
     * @param password new password data
     */
    @JsonProperty
    public void setPassword(String password) { this.password = password; }

    /**
     * Returns the account status.
     *
     * @return {@code true} if the account is active
     */
    public boolean isStatus() { return status; }

    /**
     * Updates the account status.
     *
     * @param status new active flag
     */
    public void setStatus(boolean status) { this.status = status; }

    /**
     * Returns the user's first name.
     *
     * @return first name
     */
    public String getFirstName() { return firstName; }

    /**
     * Updates the user's first name.
     *
     * @param firstName new first name
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /**
     * Returns the user's last name.
     *
     * @return last name
     */
    public String getLastName() { return lastName; }

    /**
     * Updates the user's last name.
     *
     * @param lastName new last name
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /**
     * Returns the identification value.
     *
     * @return user identification
     */
    public String getIdentification() { return identification; }

    /**
     * Updates the identification value.
     *
     * @param identification new identification value
     */
    public void setIdentification(String identification) { this.identification = identification; }

    /**
     * Returns the birth date.
     *
     * @return date of birth
     */
    public LocalDate getDateBirth() { return dateBirth; }

    /**
     * Updates the birth date.
     *
     * @param dateBirth new date of birth
     */
    public void setDateBirth(LocalDate dateBirth) { this.dateBirth = dateBirth; }

    /**
     * Returns the gender value.
     *
     * @return gender text
     */
    public String getGender() { return gender; }

    /**
     * Updates the gender value.
     *
     * @param gender new gender text
     */
    public void setGender(String gender) { this.gender = gender; }

    /**
     * Returns the user type.
     *
     * @return user type value
     */
    public String getUserType() { return userType; }

    /**
     * Updates the user type.
     *
     * @param userType new user type value
     */
    public void setUserType(String userType) { this.userType = userType; }

    /**
     * Returns the set of roles assigned to this user.
     *
     * @return set of roles
     */
    public Set<RoleModel> getRoles() { return roles; }

    /**
     * Updates the set of roles assigned to this user.
     *
     * @param roles new set of roles
     */
    public void setRoles(Set<RoleModel> roles) { this.roles = roles; }

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