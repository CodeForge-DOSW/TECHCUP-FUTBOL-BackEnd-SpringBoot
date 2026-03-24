package edu.eci.dosw.tech_cup.model;

/**
 * Define los estados de una inscripción dentro del torneo.
 *
 * Este enum permite gestionar el proceso de registro de equipos,
 * desde la solicitud inicial hasta su aprobación o rechazo.
 */
public enum RegistrationStatusModel {

    /** Inscripción pendiente de revisión */
    PENDING,

    /** Inscripción en proceso de revisión */
    IN_REVIEW,

    /** Inscripción aprobada */
    APPROVED,

    /** Inscripción rechazada */
    REJECTED;

    // ===================== MÉTODOS =====================

    /**
     * Verifica si la inscripción está pendiente.
     *
     * @return true si es PENDING
     */
    public boolean isPending() { return false; }

    /**
     * Verifica si la inscripción está en revisión.
     *
     * @return true si es IN_REVIEW
     */
    public boolean isInReview() { return false; }

    /**
     * Verifica si la inscripción ha sido aprobada.
     *
     * @return true si es APPROVED
     */
    public boolean isApproved() { return false; }

    /**
     * Verifica si la inscripción ha sido rechazada.
     *
     * @return true si es REJECTED
     */
    public boolean isRejected() { return false; }

    /**
     * Verifica si la inscripción ya fue procesada.
     *
     * @return true si está aprobada o rechazada
     */
    public boolean isFinalized() { return false; }

    /**
     * Verifica si la inscripción aún puede ser evaluada.
     *
     * @return true si está pendiente o en revisión
     */
    public boolean isUnderReview() { return false; }
}
