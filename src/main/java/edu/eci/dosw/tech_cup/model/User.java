package edu.eci.dosw.tech_cup.model;

public abstract class User {

    protected Long id;
    protected String name;
    protected String email;
    protected String password;
    protected Boolean status;

    public User() {}

    public User(Long id, String name, String email, String password, Boolean status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    public void deactivate() {
        this.status = false;
    }

    public Boolean isActive() {
        return this.status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
}
