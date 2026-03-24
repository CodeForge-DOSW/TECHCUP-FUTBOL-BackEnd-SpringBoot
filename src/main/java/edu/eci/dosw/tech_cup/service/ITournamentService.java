package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.Tournament;

import java.util.List;

/**
 * Service contract for tournament-related business operations.
 *
 * <p>Defines CRUD operations, validation rules, and lifecycle transitions
 * (start, finish) for tournaments. Implementations handle all business logic
 * and state management.</p>
 */
public interface ITournamentService {

    /**
     * Creates a new tournament with validation.
     *
     * @param tournament the tournament data to persist
     * @return the created tournament with assigned id and DRAFT status
     * @throws RuntimeException if validation fails or name is not unique
     */
    Tournament createTournament(Tournament tournament);

    /**
     * Retrieves a tournament by its id.
     *
     * @param id unique tournament identifier
     * @return the tournament if found
     * @throws RuntimeException if tournament does not exist
     */
    Tournament getTournament(Long id);

    /**
     * Retrieves all tournaments regardless of status.
     *
     * @return list of all tournaments (may be empty)
     */
    List<Tournament> getAllTournaments();

    /**
     * Updates an existing tournament's properties.
     *
     * <p>Prevents updates to tournaments in FINISHED status.
     * Validates name uniqueness and date/team constraints.</p>
     *
     * @param id unique tournament identifier
     * @param updatedTournament payload with fields to update
     * @return the updated tournament
     * @throws RuntimeException if tournament not found, validation fails, or status does not allow updates
     */
    Tournament updateTournament(Long id, Tournament updatedTournament);

    /**
     * Cancels a tournament (removes it from the system).
     *
     * <p>Only tournaments in DRAFT status can be cancelled.</p>
     *
     * @param id unique tournament identifier
     * @throws RuntimeException if tournament not found or status is not DRAFT
     */
    void cancelTournament(Long id);

    /**
     * Transitions a tournament from DRAFT to STARTED state.
     *
     * @param id unique tournament identifier
     * @throws RuntimeException if tournament does not exist or transition is invalid
     */
    void startTournament(Long id);

    /**
     * Transitions a tournament from STARTED to FINISHED state.
     *
     * @param id unique tournament identifier
     * @throws RuntimeException if tournament does not exist or transition is invalid
     */
    void finishTournament(Long id);
}
