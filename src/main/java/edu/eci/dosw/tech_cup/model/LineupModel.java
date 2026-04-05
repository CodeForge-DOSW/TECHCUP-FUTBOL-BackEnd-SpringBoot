package edu.eci.dosw.tech_cup.model;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the lineup of a team for a match.
 *
 * <p>This model manages starters, substitutes, and positional assignments,
 * validating that the formation complies with system rules.</p>
 */
public class LineupModel {

    private static final int STARTERS_REQUIRED = 6;
    private static final int MAX_SUBSTITUTES = 6;

    /** Unique identifier of the lineup. */
    private Long id;

    /** Team associated with the lineup. */
    private TeamModel team;

    /** Match in which the lineup is used. */
    private MatchModel match;

    /** Starting players. */
    private List<PlayerModel> starters;

    /** Substitute players. */
    private List<PlayerModel> substitutes;

    /** Position assignments per player. */
    private Map<PlayerModel, PositionModel> assignments;

    /** Tactical formation type. */
    private FormationTypeModel formation;

    /**
     * Indicates whether the lineup is valid.
     *
     * @return {@code true} if the lineup satisfies all rules
     */
    public boolean isValidLineup() {
        return false;
    }

    /**
     * Indicates whether the lineup has the required number of starters.
     *
     * @return {@code true} if the required starter count is satisfied
     */
    public boolean hasValidStarters() { return false; }

    /**
     * Indicates whether the lineup includes a valid goalkeeper.
     *
     * @return {@code true} if a goalkeeper is present
     */
    public boolean hasValidGoalkeeper() {
        return false;
    }

    /**
     * Indicates whether the formation is valid.
     *
     * @return {@code true} if the formation is correct
     */
    public boolean validateFormation() {
        return false;
    }

    /**
     * Indicates whether there are no duplicated players in the lineup.
     *
     * @return {@code true} if there are no duplicated players
     */
    public boolean hasNoDuplicates() {
        return false;
    }

    /**
     * Indicates whether the substitutes are valid.
     *
     * @return {@code true} if substitute rules are satisfied
     */
    public boolean hasValidSubstitutes() { return false; }

    /**
     * Adds a starter to the lineup.
     *
     * @param player player to add
     */
    public void addStarter(PlayerModel player) {

    }

    /**
     * Adds a substitute to the lineup.
     *
     * @param player player to add
     */
    public void addSubstitute(PlayerModel player) {

    }

    /**
     * Assigns a position to a player.
     *
     * @param player player to assign
     * @param position assigned position
     */
    public void assignPosition(PlayerModel player, PositionModel position) {

    }

    /**
     * Removes a starter from the lineup.
     *
     * @param player player to remove
     */
    public void removeStarter(PlayerModel player) {

    }

    /**
     * Removes a substitute from the lineup.
     *
     * @param player player to remove
     */
    public void removeSubstitute(PlayerModel player) {

    }

    /**
     * Indicates whether a player is already part of the lineup.
     *
     * @param player player to verify
     * @return {@code true} if the player is in starters or substitutes
     */
    public boolean isPlayerInLineup(PlayerModel player) {
        return false;
    }

    /**
     * Indicates whether the lineup can still be modified.
     *
     * @return {@code true} if the lineup is editable
     */
    public boolean isEditable() {
       return false;
    }
}
