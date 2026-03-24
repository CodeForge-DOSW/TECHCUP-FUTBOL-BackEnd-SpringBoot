package edu.eci.dosw.tech_cup.model;

/**
 * Define los estados en los que puede encontrarse un equipo dentro del sistema.
 *
 * Este enum permite gestionar el ciclo de vida de un equipo,
 * desde su creación hasta su aprobación o expiración.
 */
public enum TeamStatusModel {

    /** Equipo pendiente de validación */
    PENDING,

    /** Equipo aceptado para participar */
    ACCEPTED,

    /** Equipo rechazado */
    REJECTED,

    /** Estado expirado */
    EXPIRED;

    // ===================== MÉTODOS =====================

    /**
     * Verifica si el equipo está pendiente.
     *
     * @return true si es PENDING
     */
    public boolean isPending() { return false; }

    /**
     * Verifica si el equipo ha sido aceptado.
     *
     * @return true si es ACCEPTED
     */
    public boolean isAccepted() { return false; }

    /**
     * Verifica si el equipo ha sido rechazado.
     *
     * @return true si es REJECTED
     */
    public boolean isRejected() { return false; }

    /**
     * Verifica si el estado ha expirado.
     *
     * @return true si es EXPIRED
     */
    public boolean isExpired() { return false; }

    /**
     * Verifica si el equipo ya fue procesado.
     *
     * @return true si está aceptado, rechazado o expirado
     */
    public boolean isFinalized() { return false; }

    /**
     * Verifica si el equipo puede participar.
     *
     * @return true si está aceptado
     */
    public boolean canParticipate() { return false; }
}
