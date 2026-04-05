package edu.eci.dosw.tech_cup.model;

/**
 * Represents a sanction within the tournament.
 *
 * <p>This model defines penalties that can be applied to players or teams when
 * they violate competition rules.</p>
 */
public class SanctionModel {

    /**
     * Description of the sanction.
     */
    private String description;

    /**
     * Penalty associated with the sanction.
     */
    private String penalty;

    /**
     * Returns the sanction description.
     *
     * @return sanction description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the sanction description.
     *
     * @param description new sanction description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the associated penalty.
     *
     * @return sanction penalty
     */
    public String getPenalty() {
        return penalty;
    }

    /**
     * Sets the associated penalty.
     *
     * @param penalty new sanction penalty
     */
    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    /**
     * Indicates whether the sanction is valid.
     *
     * @return {@code true} if the sanction has both description and penalty
     */
    public boolean isValid() {
        return false;
    }

    /**
     * Indicates whether the sanction is severe.
     *
     * @return {@code true} if the sanction implies a severe penalty
     */
    public boolean isSevere() {
        return false;
    }
}
