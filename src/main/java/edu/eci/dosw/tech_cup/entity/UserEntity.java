package edu.eci.dosw.tech_cup.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

/**
 * Base JPA entity for users registered in the platform.
 *
 * <p>This class stores the common personal and authentication data shared by
 * all user types. The {@code user_type} column is mapped as a regular field
 * so it can be set explicitly and satisfies the database CHECK constraint.</p>
 */
@Entity
@Table(name = "\"user\"")
public class UserEntity {

    /**
     * Unique identifier of the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * Type of user within the institution.
     * Valid values: student, professor, graduate, administrative, family.
     * Maps to the CHECK constraint in the database.
     */
    @NotBlank
    @Column(name = "user_type", nullable = false, length = 30)
    private String userType;

    /**
     * User first name.
     */
    @NotBlank
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    /**
     * User last name.
     */
    @NotBlank
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    /**
     * Unique email address used for contact and authentication.
     */
    @NotBlank
    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    /**
     * Encrypted or plain password value stored for authentication.
     */
    @NotBlank
    @Column(name = "password_user", nullable = false, length = 255)
    private String passwordUser;

    /**
     * Identification document or institutional identifier.
     */
    @NotBlank
    @Column(name = "identification", nullable = false, unique = true, length = 50)
    private String identification;

    /**
     * User birth date.
     */
    @NotNull
    @Column(name = "date_birth", nullable = false)
    private LocalDate dateBirth;

    /**
     * User gender value when provided.
     */
    @Column(name = "gender", length = 20)
    private String gender;

    /**
     * Logical status of the account. {@code true} means active.
     */
    @Column(name = "status", nullable = false)
    private Boolean status = true;

    /**
     * Creates an empty user entity required by JPA.
     */
    public UserEntity() {}

    /**
     * Creates a user with the common required profile information.
     *
     * @param firstName user first name
     * @param lastName user last name
     * @param email unique email address
     * @param passwordUser password stored for authentication
     * @param identification identification value
     * @param dateBirth birth date
     * @param gender gender value
     */
    public UserEntity(String firstName, String lastName, String email,
                      String passwordUser, String identification,
                      LocalDate dateBirth, String gender) {
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.email          = email;
        this.passwordUser   = passwordUser;
        this.identification = identification;
        this.dateBirth      = dateBirth;
        this.gender         = gender;
        this.status         = true;
    }

    /**
     * Returns the user type (student, professor, graduate, administrative, family).
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
     * Returns the user identifier.
     *
     * @return persisted user id
     */
    public Long getUserId() { return userId; }

    /**
     * Updates the user identifier.
     *
     * @param userId new user id
     */
    public void setUserId(Long userId) { this.userId = userId; }

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
     * Returns the user's email.
     *
     * @return unique email address
     */
    public String getEmail() { return email; }

    /**
     * Updates the user's email.
     *
     * @param email new email address
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Returns the stored password value.
     *
     * @return password data
     */
    public String getPasswordUser() { return passwordUser; }

    /**
     * Updates the stored password value.
     *
     * @param passwordUser new password data
     */
    public void setPasswordUser(String passwordUser) { this.passwordUser = passwordUser; }

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
     * Returns the account status.
     *
     * @return {@code true} if the account is active
     */
    public Boolean getStatus() { return status; }

    /**
     * Updates the account status.
     *
     * @param status new active flag
     */
    public void setStatus(Boolean status) { this.status = status; }
}