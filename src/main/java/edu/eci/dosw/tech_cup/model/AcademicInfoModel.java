package edu.eci.dosw.tech_cup.model;


/**
 * Represents the academic information of a player within the system.
 *
 * <p>This model encapsulates the user's academic context, helping validate
 * tournament rules such as eligibility and team composition.</p>
 */
public class AcademicInfoModel {

    /**
     * Academic semester currently attended by the player.
     */
    private String semester;

    /**
     * Returns the registered academic semester.
     *
     * @return academic semester
     */
    public String getSemester() {
        return null;
    }

    /**
     * Sets the academic semester of the player.
     *
     * @param semester academic semester
     */
    public void setSemester(String semester) {

    }

    /**
     * Indicates whether semester information exists.
     *
     * @return {@code true} if a semester has been registered
     */
    public boolean hasSemester() {
        return false;
    }

    /**
     * Indicates whether the semester format is valid.
     *
     * @return {@code true} if the semester follows the expected format
     */
    public boolean isValidSemester() {
        return false;
    }

    /**
     * Indicates whether the academic information is complete.
     *
     * @return {@code true} if the minimum required data is present
     */
    public boolean isComplete() {
        return false;
    }

    /**
     * Indicates whether the academic information is valid for tournament participation.
     *
     * @return {@code true} if the eligibility rules are satisfied
     */
    public boolean isEligibleForTournament() {
        return false;
    }

}
