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

    /**
     * Verifica si la inscripción está pendiente de revisión.
     *
     * @return true si está en estado pendiente
     */
    public boolean isPending() {
        return false;
    }

    /**
     * Verifica si la inscripción está en revisión.
     *
     * @return true si está en revisión
     */
    public boolean isInReview() {
        return false;
    }

    /**
     * Verifica si la inscripción fue aprobada.
     *
     * @return true si está aprobada
     */
    public boolean isApproved() {
        return false;
    }

    /**
     * Verifica si la inscripción fue rechazada.
     *
     * @return true si está rechazada
     */
    public boolean isRejected() {
        return false;
    }

    /**
     * Verifica si la inscripción está finalizada.
     *
     * @return true si el estado es terminal
     */
    public boolean isFinalized() {
        return false;
    }

    /**
     * Determina si el estado permite pasar a revisión.
     *
     * @return true si puede cambiar a IN_REVIEW
     */
    public boolean canMoveToReview() {
        return false;
    }

    /**
     * Determina si el estado permite aprobar la inscripción.
     *
     * @return true si puede cambiar a APPROVED
     */
    public boolean canApprove() {
        return false;
    }

    /**
     * Determina si el estado permite rechazar la inscripción.
     *
     * @return true si puede cambiar a REJECTED
     */
    public boolean canReject() {
        return false;
    }
}
