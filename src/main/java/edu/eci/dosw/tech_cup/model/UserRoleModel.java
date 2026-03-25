package edu.eci.dosw.tech_cup.model;

/**
 * Representa un usuario base dentro del sistema.
 *
 * Esta clase abstracta define los atributos y comportamientos comunes
 * para todos los tipos de usuarios, como jugadores, administradores
 * y organizadores.
 */
public abstract class UserRoleModel {

    /** Identificador único del usuario */
    protected Long id;

    /** Nombre del usuario */
    protected String name;

    /** Correo electrónico del usuario */
    protected String email;


    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    /** Contraseña del usuario */
    protected String password;

    /** Estado del usuario (activo/inactivo) */
    protected boolean status;

    // ===================== MÉTODOS =====================

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isStatus() {
        return status;
    }

    /**
     * Desactiva el usuario.
     *
     * Cambia su estado a inactivo.
     */
    public void deactivate() { this.status = false; }

    /**
     * Verifica si el usuario está activo.
     *
     * @return true si está activo
     */
    public boolean isActive() { return status; }

    public Long getId() {
        return id;
    }
}
