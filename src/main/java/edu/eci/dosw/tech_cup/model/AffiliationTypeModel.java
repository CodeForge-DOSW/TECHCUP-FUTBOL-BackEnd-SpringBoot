package edu.eci.dosw.tech_cup.model;

/**
 * Define los tipos de afiliación institucional que puede tener un usuario
 * dentro del sistema.
 *
 * Este enum permite clasificar a los jugadores según su relación con la institución,
 * lo cual es fundamental para validar reglas del torneo como elegibilidad,
 * inscripción y composición de equipos.
 */
public enum AffiliationTypeModel {

    /** Egresado de la institución */
    GRADUATE,

    /** Personal administrativo */
    ADMINISTRATIVE_PERSONAL,

    /** Profesor */
    PROFESSOR,

    /** Familiar de miembro institucional */
    FAMILY,

    /** Estudiante activo */
    STUDENT;

    // ===================== MÉTODOS =====================

    /**
     * Verifica si el usuario es estudiante.
     *
     * @return true si es estudiante
     */
    public boolean isStudent() { return this == STUDENT; }

    /**
     * Verifica si el usuario es egresado.
     *
     * @return true si es egresado
     */
    public boolean isGraduate() { return this == GRADUATE; }

    /**
     * Verifica si el usuario pertenece al personal administrativo.
     *
     * @return true si es administrativo
     */
    public boolean isAdministrative() { return this == ADMINISTRATIVE_PERSONAL; }

    /**
     * Verifica si el usuario es profesor.
     *
     * @return true si es profesor
     */
    public boolean isProfessor() { return this == PROFESSOR; }

    /**
     * Verifica si el usuario es un familiar.
     *
     * @return true si es familiar
     */
    public boolean isFamily() { return this == FAMILY; }

    /**
     * Verifica si el usuario pertenece directamente a la institución.
     *
     * @return true si es estudiante, egresado, profesor o administrativo
     */
    public boolean isInstitutional() { return false; }

    /**
     * Verifica si el usuario es externo a la institución.
     *
     * @return true si es familiar
     */
    public boolean isExternal() { return isFamily(); }
}
