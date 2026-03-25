package edu.eci.dosw.tech_cup.model;

/**
 * Define los estados en los que puede encontrarse un partido dentro del sistema.
 *
 * Este enum permite gestionar el ciclo de vida de un MatchModel,
 * desde su programación hasta su finalización o cancelación.
 */
public enum MatchStatusModel {

    /** Partido programado pero no iniciado */
    SCHEDULED,

    /** Partido en curso */
    IN_PROGRESS,

    /** Partido finalizado */
    FINISHED,

    /** Partido cancelado */
    CANCELLED;

    /**
     * Verifica si el partido está programado.
     *
     * @return true si está en estado SCHEDULED
     */
    public boolean isScheduled() { return this == SCHEDULED; }

    /**
     * Verifica si el partido está en progreso.
     *
     * @return true si está en estado IN_PROGRESS
     */
    public boolean isInProgress() { return this == IN_PROGRESS; }

    /**
     * Verifica si el partido ha finalizado.
     *
     * @return true si está en estado FINISHED
     */
    public boolean isFinished() { return this == FINISHED; }

    /**
     * Verifica si el partido ha sido cancelado.
     *
     * @return true si está en estado CANCELLED
     */
    public boolean isCancelled() { return this == CANCELLED; }

    /**
     * Verifica si el partido puede ser modificado.
     *
     * @return true si está programado o en progreso
     */
    public boolean isEditable() { return isScheduled() || isInProgress(); }

    /**
     * Verifica si el partido ya no admite cambios.
     *
     * @return true si está finalizado o cancelado
     */
    public boolean isClosed() { return isFinished() || isCancelled(); }
}
