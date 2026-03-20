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
        log.debug("Creando torneo con nombre: {}", tournament != null ? tournament.getName() : "null");
        try {
            if (tournament == null) {
                log.error("Intento de crear torneo con datos nulos");
                throw new RuntimeException("Tournament cannot be null");
            }
            if (tournament.getName() == null || tournament.getName().trim().isEmpty()) {
                log.error("Nombre de torneo requerido");
                throw new RuntimeException("Tournament name is required");
            }
            if (tournament.getStartDate() == null) {
                log.error("Fecha de inicio requerida para torneo: {}", tournament.getName());
                throw new RuntimeException("Start date is required");
            }
            if (tournament.getEndDate() == null) {
                log.error("Fecha de fin requerida para torneo: {}", tournament.getName());
                throw new RuntimeException("End date is required");
            }
            if (tournament.getEndDate().isBefore(tournament.getStartDate())) {
                log.error("Fecha de fin anterior a fecha de inicio para torneo: {}", tournament.getName());
                throw new RuntimeException("End date cannot be before start date");
            }
            if (tournament.getMaxOfTeams() == null || tournament.getMaxOfTeams() < 2) {
                log.error("Máximo de equipos inválido para torneo: {}", tournament.getName());
                throw new RuntimeException("Max teams must be at least 2");
            }
            if (tournament.getTeamCost() == null || tournament.getTeamCost().compareTo(BigDecimal.ZERO) < 0) {
                log.error("Costo de equipo negativo para torneo: {}", tournament.getName());
                throw new RuntimeException("Team cost cannot be negative");
            }

            boolean nameExists = tournaments.stream()
                    .anyMatch(t -> t.getName().equalsIgnoreCase(tournament.getName()));
            if (nameExists) {
                log.warn("Intento de crear torneo con nombre ya existente: {}", tournament.getName());
                throw new RuntimeException("A tournament with that name already exists");
            }

            tournament.setId(idCounter++);
            tournament.setStatus(TournamentStatus.DRAFT.name());
            tournaments.add(tournament);
            log.info("Torneo creado correctamente: {}", tournament.getName());
            return tournament;
        } catch (RuntimeException e) {
            log.error("Error creando torneo", e);
            throw e;
        }
    }

    @Override
    public Tournament getTournament(Long id) {
        log.debug("Buscando torneo con id: {}", id);
        try {
            Tournament tournament = tournaments.stream()
                    .filter(t -> t.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Tournament not found"));
            log.info("Torneo encontrado con id: {}", id);
            return tournament;
        } catch (RuntimeException e) {
            log.error("Error buscando torneo con id: {}", id, e);
            throw e;
        }
    }

    @Override
    public List<Tournament> getAllTournaments() {
        log.debug("Obteniendo lista de todos los torneos");
        try {
            log.info("Se devuelven {} torneos", tournaments.size());
            return new ArrayList<>(tournaments);
        } catch (Exception e) {
            log.error("Error obteniendo lista de torneos", e);
            throw e;
        }
    }

    @Override
    public Tournament updateTournament(Long id, Tournament updatedTournament) {
        log.debug("Actualizando torneo con id: {}", id);
        try {
            if (updatedTournament == null) {
                log.error("Intento de actualizar torneo {} con datos nulos", id);
                throw new RuntimeException("Update data cannot be null");
            }

            Tournament existing = getTournament(id);

            if (TournamentStatus.FINISHED.name().equals(existing.getStatus())) {
                log.error("Intento de actualizar torneo finalizado con id: {}", id);
                throw new RuntimeException("Cannot update a FINISHED tournament");
            }

            if (updatedTournament.getName() != null) {
                if (updatedTournament.getName().trim().isEmpty()) {
                    log.error("Nombre vacío para actualizar torneo: {}", id);
                    throw new RuntimeException("Tournament name cannot be empty");
                }
                boolean nameExists = tournaments.stream()
                        .anyMatch(t -> t.getName().equalsIgnoreCase(updatedTournament.getName())
                                && !t.getId().equals(id));
                if (nameExists) {
                    log.warn("Intento de actualizar torneo {} con nombre ya existente: {}", id, updatedTournament.getName());
                    throw new RuntimeException("A tournament with that name already exists");
                }
                existing.setName(updatedTournament.getName());
            }

            if (updatedTournament.getStartDate() != null) {
                existing.setStartDate(updatedTournament.getStartDate());
            }

            if (updatedTournament.getEndDate() != null) {
                if (updatedTournament.getEndDate().isBefore(existing.getStartDate())) {
                    log.error("Fecha de fin anterior a fecha de inicio para torneo: {}", id);
                    throw new RuntimeException("End date cannot be before start date");
                }
                existing.setEndDate(updatedTournament.getEndDate());
            }

            if (updatedTournament.getMaxOfTeams() != null) {
                if (updatedTournament.getMaxOfTeams() < 2) {
                    log.error("Máximo de equipos inválido para torneo: {}", id);
                    throw new RuntimeException("Max teams must be at least 2");
                }
                existing.setMaxOfTeams(updatedTournament.getMaxOfTeams());
            }

            if (updatedTournament.getTeamCost() != null) {
                if (updatedTournament.getTeamCost().compareTo(BigDecimal.ZERO) < 0) {
                    log.error("Costo de equipo negativo para torneo: {}", id);
                    throw new RuntimeException("Team cost cannot be negative");
                }
                existing.setTeamCost(updatedTournament.getTeamCost());
            }

            log.info("Torneo actualizado correctamente con id: {}", id);
            return existing;
        } catch (RuntimeException e) {
            log.error("Error actualizando torneo con id: {}", id, e);
            throw e;
        }
    }

    @Override
    public void cancelTournament(Long id) {
        log.debug("Cancelando torneo con id: {}", id);
        try {
            Tournament tournament = getTournament(id);
            if (!TournamentStatus.DRAFT.name().equals(tournament.getStatus())) {
                log.error("Intento de cancelar torneo que no está en estado DRAFT: {}", id);
                throw new RuntimeException("Tournament can only be cancelled when in DRAFT status");
            }
            tournaments.remove(tournament);
            log.info("Torneo cancelado correctamente con id: {}", id);
        } catch (RuntimeException e) {
            log.error("Error cancelando torneo con id: {}", id, e);
            throw e;
        }
    }

    @Override
    public void startTournament(Long id) {
        log.debug("Iniciando torneo con id: {}", id);
        try {
            Tournament tournament = getTournament(id);
            tournament.startTournament();
            log.info("Torneo iniciado correctamente con id: {}", id);
        } catch (RuntimeException e) {
            log.error("Error iniciando torneo con id: {}", id, e);
            throw e;
        }
    }

    @Override
    public void finishTournament(Long id) {
        log.debug("Finalizando torneo con id: {}", id);
        try {
            Tournament tournament = getTournament(id);
            tournament.finishTournament();
            log.info("Torneo finalizado correctamente con id: {}", id);
        } catch (RuntimeException e) {
            log.error("Error finalizando torneo con id: {}", id, e);
            throw e;
        }
    }
}