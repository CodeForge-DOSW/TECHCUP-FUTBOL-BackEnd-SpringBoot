package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.TournamentEntity;
import edu.eci.dosw.tech_cup.exception.NotFoundException;
import edu.eci.dosw.tech_cup.mapper.TournamentMapper;
import edu.eci.dosw.tech_cup.model.TournamentModel;
import edu.eci.dosw.tech_cup.model.TournamentStatusModel;
import edu.eci.dosw.tech_cup.repository.TournamentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio de torneos.
 *
 * <p>Coordina el acceso a {@link TournamentRepository} (capa JPA) y la conversión
 * entre entidades y modelos mediante {@link TournamentMapper}. Las validaciones
 * de negocio (fechas, estado, nombre único) se aplican aquí antes de persistir.</p>
 */
@Service
public class TournamentService implements ITournamentService {

    private static final Logger log = LoggerFactory.getLogger(TournamentService.class);

    private final TournamentRepository tournamentRepository;
    private final TournamentMapper tournamentMapper;

    public TournamentService(TournamentRepository tournamentRepository,
                             TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    @Override
    public TournamentModel createTournament(TournamentModel tournament) {
        log.debug("Creating tournament: {}", tournament != null ? tournament.getName() : "null");

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
        if (tournament.getTeamCost() != null
                && tournament.getTeamCost().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Team cost cannot be negative");
        }
        if (tournamentRepository.existsByNameIgnoreCase(tournament.getName())) {
            throw new RuntimeException("A tournament with that name already exists");
        }

        tournament.setStatus(TournamentStatusModel.DRAFT);
        TournamentEntity entity = tournamentMapper.toEntity(tournament);
        TournamentEntity saved = tournamentRepository.save(entity);

        log.info("Tournament created with id: {}", saved.getTournamentId());
        return tournamentMapper.toModel(saved);
    }

    @Override
    public TournamentModel getTournament(Long id) {
        TournamentEntity entity = tournamentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tournament not found"));
        return tournamentMapper.toModel(entity);
    }

    @Override
    public List<TournamentModel> getAllTournaments() {
        return tournamentRepository.findAll()
                .stream()
                .map(tournamentMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public TournamentModel updateTournament(Long id, TournamentModel updatedTournament) {
        if (updatedTournament == null) {
            throw new RuntimeException("Update data cannot be null");
        }

        TournamentEntity existing = tournamentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tournament not found"));

        TournamentStatusModel currentStatus =
                TournamentStatusModel.valueOf(existing.getStatus().toUpperCase());
        if (currentStatus.isFinished()) {
            throw new RuntimeException("Cannot update a FINISHED tournament");
        }

        if (updatedTournament.getName() != null) {
            boolean nameTaken = tournamentRepository
                    .findByNameIgnoreCase(updatedTournament.getName())
                    .filter(t -> !t.getTournamentId().equals(id))
                    .isPresent();
            if (nameTaken) {
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
            existing.setNumberOfTeams(updatedTournament.getMaxOfTeams());
        }
        if (updatedTournament.getTeamCost() != null) {
            if (updatedTournament.getTeamCost().compareTo(BigDecimal.ZERO) < 0) {
                throw new RuntimeException("Team cost cannot be negative");
            }
            existing.setTeamCost(updatedTournament.getTeamCost());
        }

        TournamentEntity saved = tournamentRepository.save(existing);
        return tournamentMapper.toModel(saved);
    }

    @Override
    public void cancelTournament(Long id) {
        TournamentEntity entity = tournamentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tournament not found"));

        TournamentStatusModel status =
                TournamentStatusModel.valueOf(entity.getStatus().toUpperCase());
        if (!status.isDraft()) {
            throw new RuntimeException("Tournament can only be cancelled when in DRAFT status");
        }

        tournamentRepository.delete(entity);
        log.info("Tournament {} cancelled and deleted", id);
    }

    @Override
    public void startTournament(Long id) {
        TournamentEntity entity = tournamentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tournament not found"));

        TournamentStatusModel status =
                TournamentStatusModel.valueOf(entity.getStatus().toUpperCase());

        if (status.isDraft()) {
            entity.setStatus(TournamentStatusModel.ACTIVE.name().toLowerCase());
        } else if (status.isActive()) {
            entity.setStatus(TournamentStatusModel.IN_PROGRESS.name().toLowerCase());
        } else {
            throw new RuntimeException("Tournament cannot be started from status: " + status);
        }

        tournamentRepository.save(entity);
        log.info("Tournament {} advanced to status: {}", id, entity.getStatus());
    }

    @Override
    public void finishTournament(Long id) {
        TournamentEntity entity = tournamentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tournament not found"));

        TournamentStatusModel status =
                TournamentStatusModel.valueOf(entity.getStatus().toUpperCase());
        if (!status.isInProgress()) {
            throw new RuntimeException("Tournament must be IN_PROGRESS to finish");
        }

        entity.setStatus(TournamentStatusModel.FINISHED.name().toLowerCase());
        tournamentRepository.save(entity);
        log.info("Tournament {} finished", id);
    }
}