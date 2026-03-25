package edu.eci.dosw.tech_cup.model;

import java.time.LocalDate;

/**
 * Representa un usuario base dentro del sistema.
 *
 * <p>Esta clase abstracta define los atributos y comportamientos comunes
 * para todos los tipos de usuarios (jugadores, administradores, organizadores,
 * árbitros). Los campos de perfil personal reflejan directamente los campos
 * de {@code UserEntity} para que el mapper MapStruct funcione sin conversiones
 * adicionales.</p>
 */
public abstract class UserRoleModel {

    /** Identificador único del usuario (PK de base de datos) */
    protected Long id;

    /** Nombre del usuario — campo compuesto firstName + lastName en entity */
    protected String name;

    /** Correo electrónico del usuario */
    protected String email;

    /** Contraseña del usuario */
    protected String password;

    /** Estado del usuario (activo/inactivo) */
    protected boolean status;

    // ===== Campos de perfil personal (alineados con UserEntity) =====

    /** Nombre de pila */
    protected String firstName;

    /** Apellido */
    protected String lastName;

    /** Documento de identificación */
    protected String identification;

    /** Fecha de nacimiento */
    protected LocalDate dateBirth;

    /** Género */
    protected String gender;

    // ===================== GETTERS & SETTERS =====================

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
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

    // ===================== MÉTODOS DE DOMINIO =====================

    /** Desactiva el usuario cambiando su estado a inactivo. */
    public void deactivate() { this.status = false; }

    /** Verifica si el usuario está activo. */
    public boolean isActive() { return status; }
}