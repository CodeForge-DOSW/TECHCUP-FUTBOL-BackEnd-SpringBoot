package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.TournamentModel;
import edu.eci.dosw.tech_cup.model.TournamentStatusModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TournamentService implements ITournamentService {

    private static final Logger log = LoggerFactory.getLogger(TournamentService.class);

    private final List<TournamentModel> tournaments = new ArrayList<>();
    private Long idCounter = 1L;

    @Override
    public TournamentModel createTournament(TournamentModel tournament) {

        log.debug("Creating tournament with name: {}", tournament != null ? tournament.getName() : "null");

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
            throw new RuntimeException("Tournament must have at least 2 teams");
        }

        if (tournament.getTeamCost() != null &&
                tournament.getTeamCost().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Team cost cannot be negative");
        }

        boolean nameExists = tournaments.stream()
                .anyMatch(t -> t.getName().equalsIgnoreCase(tournament.getName()));

        if (nameExists) {
            throw new RuntimeException("A tournament with that name already exists");
        }

        tournament.setId(idCounter++);
        tournament.setStatus(TournamentStatusModel.DRAFT);

        tournaments.add(tournament);

        return tournament;
    }

    @Override
    public TournamentModel getTournament(Long id) {
        return tournaments.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
    }

    @Override
    public List<TournamentModel> getAllTournaments() {
        return new ArrayList<>(tournaments);
    }

    @Override
    public TournamentModel updateTournament(Long id, TournamentModel updatedTournament) {

        if (updatedTournament == null) {
            throw new RuntimeException("Update data cannot be null");
        }

        TournamentModel existing = getTournament(id);

        if (existing.getStatus().isFinished()) {
            throw new RuntimeException("Cannot update a FINISHED tournament");
        }

        if (updatedTournament.getName() != null) {
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

        if (updatedTournament.getTeamCost() != null) {
            if (updatedTournament.getTeamCost().compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Team cost cannot be negative");
            }
            existing.setTeamCost(updatedTournament.getTeamCost());
        }

        return existing;
    }

    @Override
    public void cancelTournament(Long id) {
        TournamentModel tournament = getTournament(id);

        if (!tournament.getStatus().isDraft()) {
            throw new RuntimeException("Tournament can only be cancelled when in DRAFT status");
        }

        tournaments.remove(tournament);
    }

    @Override
    public void startTournament(Long id) {
        TournamentModel tournament = getTournament(id);

        // 🔥 Ajuste clave: respetar flujo del modelo
        // DRAFT -> ACTIVE
        if (tournament.getStatus().isDraft()) {
            tournament.setStatus(TournamentStatusModel.ACTIVE);
            return;
        }

        // ACTIVE -> IN_PROGRESS (usa lógica del modelo)
        tournament.start();
    }

    @Override
    public void finishTournament(Long id) {
        TournamentModel tournament = getTournament(id);

        // Solo el modelo decide si puede finalizar
        tournament.finish();
    }
}