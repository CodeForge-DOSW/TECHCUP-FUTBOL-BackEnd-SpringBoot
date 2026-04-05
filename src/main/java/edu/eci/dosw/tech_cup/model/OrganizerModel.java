package edu.eci.dosw.tech_cup.model;

import java.time.LocalDateTime;

/**
 * Represents a tournament organizer.
 *
 * <p>This role is responsible for managing the tournament lifecycle, including
 * creation, start, completion, and registration review.</p>
 */
public class OrganizerModel extends UserRoleModel {

    /**
     * Creates a new tournament.
     *
     * @param startDate tournament start date
     * @param endDate tournament end date
     * @param maxTeams maximum number of teams
     * @param costPerTeam registration cost per team
     * @return newly created tournament
     */
    public TournamentModel createTournament(
            LocalDateTime startDate,
            LocalDateTime endDate,
            int maxTeams,
            double costPerTeam
    ) {
        return null;
    }

    /**
     * Starts a tournament.
     *
     * @param tournament tournament to start
     */
    public void startTournament(TournamentModel tournament) {

    }

    /**
     * Finishes a tournament.
     *
     * @param tournament tournament to finish
     */
    public void finishTournament(TournamentModel tournament) {

    }

    /**
     * Retrieves a tournament by its identifier.
     *
     * @param id tournament identifier
     * @return matching tournament
     */
    public TournamentModel getTournament(Long id) {
        return null;
    }

    /**
     * Reviews a team registration.
     *
     * @param registration registration to review
     */
    public void reviewRegistration(RegistrationModel registration) {

    }
}
