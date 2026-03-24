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

    /** Contraseña del usuario */
    protected String password;

    /** Estado del usuario (activo/inactivo) */
    protected boolean status;

    // ===================== MÉTODOS =====================

    /**
     * Desactiva el usuario.
     *
     * Cambia su estado a inactivo.
     */
    public void deactivate() {}

    /**
     * Verifica si el usuario está activo.
     *
     * @return true si está activo
     */
    public boolean isActive() { return false; }
}
