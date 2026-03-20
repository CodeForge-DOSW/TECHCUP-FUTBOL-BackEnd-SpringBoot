package edu.eci.dosw.tech_cup.model;


public class Administrator extends User {

    public Administrator() {}

    public Administrator(Long id, String name, String email, String password, Boolean status) {
        super(id, name, email, password, status);
    }

    public void changeUserRole(Player player, RoleType role) {
        if (player == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        player.setRole(role);
    }
}
