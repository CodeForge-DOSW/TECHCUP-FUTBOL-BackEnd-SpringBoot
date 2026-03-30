package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.TournamentEntity;
import edu.eci.dosw.tech_cup.mapper.TournamentMapper;
import edu.eci.dosw.tech_cup.model.TournamentModel;
import edu.eci.dosw.tech_cup.model.TournamentStatusModel;
import edu.eci.dosw.tech_cup.repository.TournamentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JPA-backed implementation of the tournament service contract.
 *
 * <p>This service applies validation rules and lifecycle transitions for
 * tournaments before delegating persistence to {@link TournamentRepository}.
 * It also coordinates conversions between entities and models through
 * {@link TournamentMapper}.</p>
 */
@Service
public class TournamentService implements ITournamentService {

    private static final Logger log = LoggerFactory.getLogger(TournamentService.class);

    /**
     * Repository used to persist and query tournament records.
     */
    private final TournamentRepository tournamentRepository;

    /**
     * Mapper used to convert between persistence entities and service models.
     */
    private final TournamentMapper tournamentMapper;

    /**
     * Creates the service with its repository and mapper dependencies.
     *
     * @param tournamentRepository repository for tournament persistence operations
     * @param tournamentMapper mapper for entity-model conversions
     */
    public TournamentService(TournamentRepository tournamentRepository,
                             TournamentMapper tournamentMapper) {
        this.tournamentRepository = tournamentRepository;
        this.tournamentMapper = tournamentMapper;
    }

    /**
     * Creates a new tournament after validating its business constraints.
     *
     * <p>The service validates required fields, date order, team capacity,
     * non-negative registration cost, and name uniqueness. New tournaments are
     * always initialized with {@link TournamentStatusModel#DRAFT} status.</p>
     *
     * @param tournament tournament data to validate and persist
     * @return the persisted tournament mapped back to the service model
     * @throws RuntimeException if the payload is null, required fields are missing,
     * validation fails, or another tournament already uses the same name
     */
    @Override
    @Transactional
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

    /**
     * Retrieves a tournament by its identifier.
     *
     * @param id unique tournament identifier
     * @return the mapped tournament model
     * @throws RuntimeException if the tournament does not exist
     */
    @Override
    public TournamentModel getTournament(Long id) {
        TournamentEntity entity = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));
        return tournamentMapper.toModel(entity);
    }

    /**
     * Retrieves every stored tournament.
     *
     * @return a list of mapped tournament models, which may be empty
     */
    @Override
    public List<TournamentModel> getAllTournaments() {
        return tournamentRepository.findAll()
                .stream()
                .map(tournamentMapper::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Updates selected fields of an existing tournament.
     *
     * <p>The service rejects updates for tournaments already in
     * {@link TournamentStatusModel#FINISHED} status. When provided, the new name
     * must remain unique and the end date cannot be earlier than the current
     * start date.</p>
     *
     * @param id identifier of the tournament to update
     * @param updatedTournament payload containing the fields to modify
     * @return the updated tournament mapped back to the service model
     * @throws RuntimeException if the payload is null, the tournament does not exist,
     * validation fails, or the current status forbids updates
     */
    @Override
    @Transactional
    public TournamentModel updateTournament(Long id, TournamentModel updatedTournament) {
        if (updatedTournament == null) {
            throw new RuntimeException("Update data cannot be null");
        }

        TournamentEntity existing = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

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

    /**
     * Cancels a tournament by deleting it when it is still in draft status.
     *
     * @param id identifier of the tournament to cancel
     * @throws RuntimeException if the tournament does not exist or is not in draft status
     */
    @Override
    @Transactional
    public void cancelTournament(Long id) {
        TournamentEntity entity = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

        TournamentStatusModel status =
                TournamentStatusModel.valueOf(entity.getStatus().toUpperCase());
        if (!status.isDraft()) {
            throw new RuntimeException("Tournament can only be cancelled when in DRAFT status");
        }

        tournamentRepository.delete(entity);
        log.info("Tournament {} cancelled and deleted", id);
    }

    /**
     * Advances a tournament to its next start-related lifecycle state.
     *
     * <p>A tournament moves from {@code draft} to {@code active}, and from
     * {@code active} to {@code in_progress}. Any other source status is rejected.</p>
     *
     * @param id identifier of the tournament to advance
     * @throws RuntimeException if the tournament does not exist or the current status
     * does not allow the transition
     */
    @Override
    @Transactional
    public void startTournament(Long id) {
        TournamentEntity entity = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

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

    /**
     * Marks a tournament as finished once it is already in progress.
     *
     * @param id identifier of the tournament to finish
     * @throws RuntimeException if the tournament does not exist or is not in progress
     */
    @Override
    @Transactional
    public void finishTournament(Long id) {
        TournamentEntity entity = tournamentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tournament not found"));

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
