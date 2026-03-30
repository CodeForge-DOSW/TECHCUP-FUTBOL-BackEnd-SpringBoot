package edu.eci.dosw.tech_cup.model;

/**
 * Define los estados de una invitación dentro del sistema.
 *
 * Este enum permite gestionar el ciclo de vida de una invitación,
 * desde su creación hasta su aceptación, rechazo o expiración.
 */
public enum InvitationStatusModel {

    /** Invitación pendiente de respuesta */
    PENDING,

    /** Invitación aceptada */
    ACCEPTED,

    /** Invitación rechazada */
    REJECTED,

    /** Invitación expirada */
    EXPIRED;


    /**
     * Verifica si la invitación está pendiente.
     *
     * @return true si es PENDING
     */
    public boolean isPending() { return this == PENDING; }

    /**
     * Verifica si la invitación ha sido aceptada.
     *
     * @return true si es ACCEPTED
     */
    public boolean isAccepted() { return this == ACCEPTED; }

    /**
     * Verifica si la invitación ha sido rechazada.
     *
     * @return true si es REJECTED
     */
    public boolean isRejected() { return this == REJECTED; }

    /**
     * Verifica si la invitación ha expirado.
     *
     * @return true si es EXPIRED
     */
    public boolean isExpired() { return this == EXPIRED; }

    /**
     * Verifica si la invitación ya fue procesada.
     *
     * @return true si está aceptada, rechazada o expirada
     */
    public boolean isFinalized() { return false; }

    /**
     * Verifica si la invitación aún está activa.
     *
     * @return true si está pendiente
     */
    public boolean isActive() { return false; }
}
