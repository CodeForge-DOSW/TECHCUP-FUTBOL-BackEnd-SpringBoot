package edu.eci.dosw.tech_cup.model;


public class Player extends User {

    private RoleType role;
    private FieldPosition position;

    public Player() {}

    public Player(Long id, String name, String email, String password, Boolean status,
                  RoleType role, FieldPosition position) {
        super(id, name, email, password, status);
        this.role = role;
        this.position = position;
    }

    public Boolean isValid() {
        return this.name != null && !this.name.isBlank()
                && this.email != null && !this.email.isBlank()
                && this.role != null
                && this.position != null;
    }

    // Getters and Setters
    public RoleType getRole() { return role; }
    public void setRole(RoleType role) { this.role = role; }

    public FieldPosition getPosition() { return position; }
    public void setPosition(FieldPosition position) { this.position = position; }
}
