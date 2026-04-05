package edu.eci.dosw.tech_cup.model;

import java.time.LocalDateTime;

/**
 * Represents an important date within the tournament.
 *
 * <p>This model is used to register key events such as tournament start,
 * registration deadlines, and other milestone dates.</p>
 */
public class ImportantDateModel {

    /** Name or description of the important date. */
    private String name;

    /** Date and time of the event. */
    private LocalDateTime date;

    // ===================== Domain methods =====================

    /**
     * Returns the name of the important date.
     *
     * @return name or description
     */
    public String getName() { return name; }

    /**
     * Sets the name of the important date.
     *
     * @param name new name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the event date.
     *
     * @return event date and time
     */
    public LocalDateTime getDate() { return date; }

    /**
     * Sets the event date.
     *
     * @param date new date
     */
    public void setDate(LocalDateTime date) { this.date = date; }

    /**
     * Indicates whether the important date is valid.
     *
     * @return {@code true} if both name and date are defined
     */
    public boolean isValid() { return name != null && !name.trim().isEmpty() && date != null; }

    /**
     * Indicates whether the date has already passed.
     *
     * @return {@code true} if the date is earlier than the current moment
     */
    public boolean hasPassed() { return date != null && date.isBefore(LocalDateTime.now()); }

    /**
     * Indicates whether the date is coming up soon.
     *
     * @return {@code true} if the date is near in time
     */
    public boolean isUpcoming() {
        return false;
    }
}
