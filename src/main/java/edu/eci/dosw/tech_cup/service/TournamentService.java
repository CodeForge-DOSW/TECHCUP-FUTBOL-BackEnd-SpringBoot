package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.model.TournamentStatus;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of tournament business logic and state management.
 *
 * <p>Manages tournament CRUD operations with comprehensive validation,
 * enforces state transitions (DRAFT → STARTED → FINISHED), and maintains
 * business rules (unique names, date constraints, team limits).</p>
 */
@Service
public class TournamentService implements ITournamentService {

    /**
     * In-memory storage of tournaments (should be replaced with a database repository).
     */
    private final List<Tournament> tournaments = new ArrayList<>();

    /**
     * Identifier generator for new tournaments.
     */
    private Long idCounter = 1L;

    /**
     * Creates a new tournament with comprehensive validation.
     *
     * <p>Validates all required fields, enforces date logic, checks team constraints,
     * and ensures tournament name uniqueness.</p>
     *
     * @param tournament the tournament to create
     * @return the persisted tournament with assigned id and DRAFT status
     * @throws RuntimeException if any validation fails
     */
    @Override
    public Tournament createTournament(Tournament tournament) {
        if (tournament == null) {
            throw new RuntimeException("Tournament cannot be null");
        }
        if (tournament.getName() == null || tournament.getName().trim().isEmpty()) {
            throw new RuntimeException("Tournament name is required");
        }
        if (tournament.getStartDate() == null) {
            throw new RuntimeException("Start date is required");
        }
        if (tournament.getEndDate() == null) {
            throw new RuntimeException("End date is required");
        }
        if (tournament.getEndDate().isBefore(tournament.getStartDate())) {
            throw new RuntimeException("End date cannot be before start date");
        }
        if (tournament.getMaxOfTeams() == null || tournament.getMaxOfTeams() < 2) {
            throw new RuntimeException("Max teams must be at least 2");
        }
        if (tournament.getTeamCost() == null || tournament.getTeamCost().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Team cost cannot be negative");
        }

        boolean nameExists = tournaments.stream()
                .anyMatch(t -> t.getName().equalsIgnoreCase(tournament.getName()));
        if (nameExists) {
            throw new RuntimeException("A tournament with that name already exists");
        }

        tournament.setId(idCounter++);
        tournament.setStatus(TournamentStatus.DRAFT.name());
        tournaments.add(tournament);
        return tournament;
    }

    /**
     * Retrieves a tournament by its id.
     *
     * @param id unique tournament identifier
     * @return the tournament if found
     * @throws RuntimeException if tournament does not exist
     */
    @Override
    public Tournament getTournament(Long id) {
        return tournaments.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
    }

    /**
     * Retrieves all tournaments.
     *
     * @return a copy of the tournament list
     */
    @Override
    public List<Tournament> getAllTournaments() {
        return new ArrayList<>(tournaments);
    }

    /**
     * Updates an existing tournament's properties.
     *
     * <p>Prevents updates to tournaments in FINISHED status. Validates all
     * updates follow business rules (unique name, date constraints, etc.).</p>
     *
     * @param id unique tournament identifier
     * @param updatedTournament fields to update
     * @return the updated tournament
     * @throws RuntimeException if validation fails or status does not allow updates
     */
    @Override
    public Tournament updateTournament(Long id, Tournament updatedTournament) {
        if (updatedTournament == null) {
            throw new RuntimeException("Update data cannot be null");
        }

        Tournament existing = getTournament(id);

        if (TournamentStatus.FINISHED.name().equals(existing.getStatus())) {
            throw new RuntimeException("Cannot update a FINISHED tournament");
        }

        if (updatedTournament.getName() != null) {
            if (updatedTournament.getName().trim().isEmpty()) {
                throw new RuntimeException("Tournament name cannot be empty");
            }
            boolean nameExists = tournaments.stream()
                    .anyMatch(t -> t.getName().equalsIgnoreCase(updatedTournament.getName())
                            && !t.getId().equals(id));
            if (nameExists) {
                throw new RuntimeException("A tournament with that name already exists");
            }
            existing.setName(updatedTournament.getName());
        }

        if (updatedTournament.getStartDate() != null) {
            existing.setStartDate(updatedTournament.getStartDate());
        }

        if (updatedTournament.getEndDate() != null) {
            if (updatedTournament.getEndDate().isBefore(existing.getStartDate())) {
                throw new RuntimeException("End date cannot be before start date");
            }
            existing.setEndDate(updatedTournament.getEndDate());
        }

        if (updatedTournament.getMaxOfTeams() != null) {
            if (updatedTournament.getMaxOfTeams() < 2) {
                throw new RuntimeException("Max teams must be at least 2");
            }
            existing.setMaxOfTeams(updatedTournament.getMaxOfTeams());
        }

        if (updatedTournament.getTeamCost() != null) {
            if (updatedTournament.getTeamCost().compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Team cost cannot be negative");
            }
            existing.setTeamCost(updatedTournament.getTeamCost());
        }

        return existing;
    }

    /**
     * Cancels a tournament (deletes it from the system).
     *
     * <p>Only tournaments in DRAFT status can be cancelled.</p>
     *
     * @param id unique tournament identifier
     * @throws RuntimeException if tournament not found or not in DRAFT status
     */
    @Override
    public void cancelTournament(Long id) {
        Tournament tournament = getTournament(id);
        if (!TournamentStatus.DRAFT.name().equals(tournament.getStatus())) {
            throw new RuntimeException("Tournament can only be cancelled when in DRAFT status");
        }
        tournaments.remove(tournament);
    }

    /**
     * Transitions a tournament from DRAFT to STARTED status.
     *
     * @param id unique tournament identifier
     * @throws RuntimeException if the tournament does not exist or the transition is invalid
     */
    @Override
    public void startTournament(Long id) {
        Tournament tournament = getTournament(id);
        tournament.startTournament();
    }

    /**
     * Transitions a tournament from STARTED to FINISHED status.
     *
     * @param id unique tournament identifier
     * @throws RuntimeException if the tournament does not exist or the transition is invalid
     */
    @Override
    public void finishTournament(Long id) {
        Tournament tournament = getTournament(id);
        tournament.finishTournament();
    }
}