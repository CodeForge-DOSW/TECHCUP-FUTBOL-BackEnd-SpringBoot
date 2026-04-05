package edu.eci.dosw.tech_cup.model;

/**
 * Enumerates the institutional affiliation types a user can have within the system.
 *
 * <p>This enum classifies users according to their relationship with the
 * institution, which is relevant for tournament rules such as eligibility,
 * registration, and team composition.</p>
 */
public enum AffiliationTypeModel {

    /** Graduate of the institution. */
    GRADUATE,

    /** Administrative staff member. */
    ADMINISTRATIVE_PERSONAL,

    /** Professor. */
    PROFESSOR,

    /** Family member of an institutional member. */
    FAMILY,

    /** Active student. */
    STUDENT;

    // ===================== Domain methods =====================

    /**
     * Indicates whether the affiliation type is student.
     *
     * @return {@code true} if the type is {@link #STUDENT}
     */
    public boolean isStudent() { return this == STUDENT; }

    /**
     * Indicates whether the affiliation type is graduate.
     *
     * @return {@code true} if the type is {@link #GRADUATE}
     */
    public boolean isGraduate() { return this == GRADUATE; }

    /**
     * Indicates whether the affiliation type is administrative staff.
     *
     * @return {@code true} if the type is {@link #ADMINISTRATIVE_PERSONAL}
     */
    public boolean isAdministrative() { return this == ADMINISTRATIVE_PERSONAL; }

    /**
     * Indicates whether the affiliation type is professor.
     *
     * @return {@code true} if the type is {@link #PROFESSOR}
     */
    public boolean isProfessor() { return this == PROFESSOR; }

    /**
     * Indicates whether the affiliation type is family.
     *
     * @return {@code true} if the type is {@link #FAMILY}
     */
    public boolean isFamily() { return this == FAMILY; }

    /**
     * Indicates whether the affiliation belongs directly to the institution.
     *
     * @return {@code true} if the type is student, graduate, professor, or administrative
     */
    public boolean isInstitutional() { return false; }

    /**
     * Indicates whether the affiliation is external to the institution.
     *
     * @return {@code true} if the type is family
     */
    public boolean isExternal() { return isFamily(); }
}
