package edu.eci.dosw.tech_cup.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.model.TournamentStatus;

public class TournamentService implements ITournamentService {

    private static final Logger log = LoggerFactory.getLogger(TournamentService.class);

    private final List<Tournament> tournaments = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public Tournament createTournament(Tournament tournament) {
        log.debug("Creating tournament with name: {}", tournament != null ? tournament.getName() : "null");
        try {
            if (tournament == null) {
                log.error("Attempt to create tournament with null data");
                throw new RuntimeException("Tournament cannot be null");
            }
            if (tournament.getName() == null || tournament.getName().trim().isEmpty()) {
                log.error("Tournament name is required");
                throw new RuntimeException("Tournament name is required");
            }
            if (tournament.getStartDate() == null) {
                log.error("Start date is required for tournament: {}", tournament.getName());
                throw new RuntimeException("Start date is required");
            }
            if (tournament.getEndDate() == null) {
                log.error("End date is required for tournament: {}", tournament.getName());
                throw new RuntimeException("End date is required");
            }
            if (tournament.getEndDate().isBefore(tournament.getStartDate())) {
                log.error("End date is before start date for tournament: {}", tournament.getName());
                throw new RuntimeException("End date cannot be before start date");
            }
            if (tournament.getMaxOfTeams() == null || tournament.getMaxOfTeams() < 2) {
                log.error("Invalid max teams value for tournament: {}", tournament.getName());
                throw new RuntimeException("Max teams must be at least 2");
            }
            if (tournament.getTeamCost() == null || tournament.getTeamCost().compareTo(BigDecimal.ZERO) < 0) {
                log.error("Negative team cost for tournament: {}", tournament.getName());
                throw new RuntimeException("Team cost cannot be negative");
            }

            boolean nameExists = tournaments.stream()
                    .anyMatch(t -> t.getName().equalsIgnoreCase(tournament.getName()));
            if (nameExists) {
                log.warn("Attempt to create tournament with an existing name: {}", tournament.getName());
                throw new RuntimeException("A tournament with that name already exists");
            }

            tournament.setId(idCounter++);
            tournament.setStatus(TournamentStatus.DRAFT.name());
            tournaments.add(tournament);
            log.info("Tournament created successfully: {}", tournament.getName());
            return tournament;
        } catch (RuntimeException e) {
            log.error("Error creating tournament", e);
            throw e;
        }
    }

    @Override
    public Tournament getTournament(Long id) {
        log.debug("Searching tournament with id: {}", id);
        try {
            Tournament tournament = tournaments.stream()
                    .filter(t -> t.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Tournament not found"));
            log.info("Tournament found with id: {}", id);
            return tournament;
        } catch (RuntimeException e) {
            log.error("Error searching tournament with id: {}", id, e);
            throw e;
        }
    }

    @Override
    public List<Tournament> getAllTournaments() {
        log.debug("Fetching all tournaments");
        try {
            log.info("Returning {} tournaments", tournaments.size());
            return new ArrayList<>(tournaments);
        } catch (Exception e) {
            log.error("Error fetching tournament list", e);
            throw e;
        }
    }

    @Override
    public Tournament updateTournament(Long id, Tournament updatedTournament) {
        log.debug("Updating tournament with id: {}", id);
        try {
            if (updatedTournament == null) {
                log.error("Attempt to update tournament {} with null data", id);
                throw new RuntimeException("Update data cannot be null");
            }

            Tournament existing = getTournament(id);

            if (TournamentStatus.FINISHED.name().equals(existing.getStatus())) {
                log.error("Attempt to update finished tournament with id: {}", id);
                throw new RuntimeException("Cannot update a FINISHED tournament");
            }

            if (updatedTournament.getName() != null) {
                if (updatedTournament.getName().trim().isEmpty()) {
                    log.error("Empty name while updating tournament: {}", id);
                    throw new RuntimeException("Tournament name cannot be empty");
                }
                boolean nameExists = tournaments.stream()
                        .anyMatch(t -> t.getName().equalsIgnoreCase(updatedTournament.getName())
                                && !t.getId().equals(id));
                if (nameExists) {
                    log.warn("Attempt to update tournament {} with an existing name: {}", id, updatedTournament.getName());
                    throw new RuntimeException("A tournament with that name already exists");
                }
                existing.setName(updatedTournament.getName());
            }

            if (updatedTournament.getStartDate() != null) {
                existing.setStartDate(updatedTournament.getStartDate());
            }

            if (updatedTournament.getEndDate() != null) {
                if (updatedTournament.getEndDate().isBefore(existing.getStartDate())) {
                    log.error("End date is before start date for tournament: {}", id);
                    throw new RuntimeException("End date cannot be before start date");
                }
                existing.setEndDate(updatedTournament.getEndDate());
            }

            if (updatedTournament.getMaxOfTeams() != null) {
                if (updatedTournament.getMaxOfTeams() < 2) {
                    log.error("Invalid max teams value for tournament: {}", id);
                    throw new RuntimeException("Max teams must be at least 2");
                }
                existing.setMaxOfTeams(updatedTournament.getMaxOfTeams());
            }

            if (updatedTournament.getTeamCost() != null) {
                if (updatedTournament.getTeamCost().compareTo(BigDecimal.ZERO) < 0) {
                    log.error("Negative team cost for tournament: {}", id);
                    throw new RuntimeException("Team cost cannot be negative");
                }
                existing.setTeamCost(updatedTournament.getTeamCost());
            }

            log.info("Tournament updated successfully with id: {}", id);
            return existing;
        } catch (RuntimeException e) {
            log.error("Error updating tournament with id: {}", id, e);
            throw e;
        }
    }

    @Override
    public void cancelTournament(Long id) {
        log.debug("Cancelling tournament with id: {}", id);
        try {
            Tournament tournament = getTournament(id);
            if (!TournamentStatus.DRAFT.name().equals(tournament.getStatus())) {
                log.error("Attempt to cancel tournament that is not in DRAFT status: {}", id);
                throw new RuntimeException("Tournament can only be cancelled when in DRAFT status");
            }
            tournaments.remove(tournament);
            log.info("Tournament cancelled successfully with id: {}", id);
        } catch (RuntimeException e) {
            log.error("Error cancelling tournament with id: {}", id, e);
            throw e;
        }
    }

    @Override
    public void startTournament(Long id) {
        log.debug("Starting tournament with id: {}", id);
        try {
            Tournament tournament = getTournament(id);
            tournament.startTournament();
            log.info("Tournament started successfully with id: {}", id);
        } catch (RuntimeException e) {
            log.error("Error starting tournament with id: {}", id, e);
            throw e;
        }
    }

    @Override
    public void finishTournament(Long id) {
        log.debug("Finishing tournament with id: {}", id);
        try {
            Tournament tournament = getTournament(id);
            tournament.finishTournament();
            log.info("Tournament finished successfully with id: {}", id);
        } catch (RuntimeException e) {
            log.error("Error finishing tournament with id: {}", id, e);
            throw e;
        }
    }
}