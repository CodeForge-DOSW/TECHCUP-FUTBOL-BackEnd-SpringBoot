package edu.eci.dosw.tech_cup.model;

/**
 * Define los estados en los que puede encontrarse un torneo.
 *
 * Este enum permite gestionar el ciclo de vida del TournamentModel,
 * desde su creación hasta su finalización.
 */
public enum TournamentStatusModel {

    /** Torneo en fase de configuración */
    DRAFT,

    /** Torneo activo (inscripciones abiertas o en preparación) */
    ACTIVE,

    /** Torneo en curso */
    IN_PROGRESS,

    /** Torneo finalizado */
    FINISHED;

    // ===================== MÉTODOS =====================

    /**
     * Verifica si el torneo está en fase de borrador.
     *
     * @return true si es DRAFT
     */
    public boolean isDraft() { return false; }

    /**
     * Verifica si el torneo está activo.
     *
     * @return true si es ACTIVE
     */
    public boolean isActive() { return false; }

    /**
     * Verifica si el torneo está en progreso.
     *
     * @return true si es IN_PROGRESS
     */
    public boolean isInProgress() { return false; }

    /**
     * Verifica si el torneo ha finalizado.
     *
     * @return true si es FINISHED
     */
    public boolean isFinished() { return false; }

    /**
     * Verifica si el torneo puede iniciar.
     *
     * @return true si está activo
     */
    public boolean canStart() { return false; }

    /**
     * Verifica si el torneo ya no admite cambios.
     *
     * @return true si está finalizado
     */
    public boolean isClosed() { return false; }
}
