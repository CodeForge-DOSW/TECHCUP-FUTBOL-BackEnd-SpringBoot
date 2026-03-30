package edu.eci.dosw.tech_cup.model;

/**
 * Represents a rule within the tournament.
 *
 * <p>This model defines conditions and restrictions that must be respected
 * during tournament execution, such as team requirements, player behavior,
 * or gameplay rules.</p>
 */
public class RuleModel {

    /** Rule description. */
    private String description;


    /**
     * Returns the rule description.
     *
     * @return rule description
     */
    public String getDescription() { return description; }

    /**
     * Sets the rule description.
     *
     * @param description new rule description
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Indicates whether the rule is valid.
     *
     * @return {@code true} if the description is not null or empty
     */
    public boolean isValid() { return false; }

    /**
     * Indicates whether the rule applies in the current context.
     *
     * @return {@code true} if the rule applies
     */
    public boolean applies() { return isValid(); }
}
