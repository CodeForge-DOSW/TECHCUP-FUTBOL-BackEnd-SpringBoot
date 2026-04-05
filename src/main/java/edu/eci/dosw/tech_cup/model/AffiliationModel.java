package edu.eci.dosw.tech_cup.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents the institutional affiliation of a user within the system.
 *
 * <p>This model identifies the user's relationship with the institution
 * (student, graduate, and so on) and the academic programs associated with it.
 * It is relevant for validating tournament rules such as eligibility and team composition.</p>
 */
public class AffiliationModel {

    /** User affiliation type. */
    private AffiliationTypeModel type;

    /** List of academic programs associated with the user. */
    private List<CareerModel> careers;

    /**
     * Indicates whether the user is a student.
     *
     * @return {@code true} if the affiliation is student
     */
    public boolean isStudent() { return false; }

    /**
     * Indicates whether the user is a graduate.
     *
     * @return {@code true} if the affiliation is graduate
     */
    public boolean isGraduate() { return type != null && type.isGraduate(); }

    /**
     * Indicates whether the user belongs to the administrative staff.
     *
     * @return {@code true} if the affiliation is administrative
     */
    public boolean isAdministrative() { return type != null && type.isAdministrative(); }

    /**
     * Indicates whether the user belongs to a specific academic program.
     *
     * @param career academic program to validate
     * @return {@code true} if the affiliation includes the given program
     */
    public boolean belongsToCareer(CareerModel career) {
        return false;
    }

    /**
     * Indicates whether the user belongs to any of the provided academic programs.
     *
     * @param careers list of academic programs
     * @return {@code true} if the affiliation matches at least one program
     */
    public boolean belongsToAnyCareer(List<CareerModel> careers) {
        return false;
    }

    /**
     * Adds an academic program to the affiliation.
     *
     * @param career academic program to add
     */
    public void addCareer(CareerModel career) {

    }

    /**
     * Removes an academic program from the affiliation.
     *
     * @param career academic program to remove
     */
    public void removeCareer(CareerModel career) {

    }

    /**
     * Changes the affiliation type of the user.
     *
     * @param type new affiliation type
     */
    public void changeType(AffiliationTypeModel type) { this.type = type; }
}
