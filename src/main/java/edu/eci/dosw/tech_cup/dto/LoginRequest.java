package edu.eci.dosw.tech_cup.dto;

/**
 * Request payload used to authenticate a user.
 */
public class LoginRequest {

    /** User email address. */
    private String email;

    /** User password. */
    private String password;

    /**
     * Creates an empty login request.
     */
    public LoginRequest() {}

    /**
     * Creates a login request with credential values.
     *
     * @param email user email address
     * @param password user password
     */
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
