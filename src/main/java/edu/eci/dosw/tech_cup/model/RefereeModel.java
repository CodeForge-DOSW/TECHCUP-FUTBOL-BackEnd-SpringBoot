package edu.eci.dosw.tech_cup.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a referee within the system.
 *
 * <p>This role manages the matches assigned to the referee, including result
 * registration and match detail lookup.</p>
 */
public class RefereeModel extends UserRoleModel {

    /** List of matches assigned to the referee. */
    private List<MatchModel> matches;


    /**
     * Registers results for the matches assigned to the referee.
     *
     * <p>This method updates relevant match information by marking in-progress
     * matches as finished.</p>
     */
    public void registerMatchResults() {
        if (matches == null) {
            return;
        }
        for (MatchModel match : matches) {
            if (match != null && match.getStatus() != null && match.getStatus().isInProgress()) {
                match.setStatus(MatchStatusModel.FINISHED);
            }
        }
    }

    /**
     * Returns the list of matches assigned to the referee.
     *
     * @return assigned match list
     */
    public List<MatchModel> getAssignedMatches() {
        return matches == null ? new ArrayList<>() : new ArrayList<>(matches);
    }

    /**
     * Returns the details of a specific assigned match.
     *
     * @param match match to inspect
     * @return the same match if it is assigned to the referee; {@code null} otherwise
     */
    public MatchModel getMatchDetails(MatchModel match) {
        if (match == null || matches == null || !matches.contains(match)) {
            return null;
        }
        return match;
    }
}
