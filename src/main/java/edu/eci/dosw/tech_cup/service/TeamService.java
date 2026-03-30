package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.TeamEntity;
import edu.eci.dosw.tech_cup.entity.TeamPlayerEntity;
import edu.eci.dosw.tech_cup.entity.TournamentEntity;
import edu.eci.dosw.tech_cup.entity.UserEntity;
import edu.eci.dosw.tech_cup.mapper.TeamMapper;
import edu.eci.dosw.tech_cup.model.TeamResponseModel;
import edu.eci.dosw.tech_cup.repository.TeamPlayerRepository;
import edu.eci.dosw.tech_cup.repository.TeamRepository;
import edu.eci.dosw.tech_cup.repository.TournamentRepository;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JPA-backed implementation of the team service contract.
 *
 * <p>This service validates team data, resolves related tournament and captain
 * references, and persists the resulting entities through the repository layer.
 * It also registers the captain as an initial team member when applicable.</p>
 */
@Service
public class TeamService implements ITeamService {

    private static final Logger log = LoggerFactory.getLogger(TeamService.class);

    /**
     * Repository used to persist and query team records.
     */
    private final TeamRepository teamRepository;

    /**
     * Repository used to manage team-player membership records.
     */
    private final TeamPlayerRepository teamPlayerRepository;

    /**
     * Repository used to resolve related tournament records.
     */
    private final TournamentRepository tournamentRepository;

    /**
     * Repository used to resolve captain user records.
     */
    private final UserRepository userRepository;

    /**
     * Mapper used to convert between persistence entities and service models.
     */
    private final TeamMapper teamMapper;

    /**
     * Creates the service with its repository and mapper dependencies.
     *
     * @param teamRepository repository for team persistence operations
     * @param teamPlayerRepository repository for team-player membership operations
     * @param tournamentRepository repository for tournament lookups
     * @param userRepository repository for user lookups
     * @param teamMapper mapper for entity-model conversions
     */
    public TeamService(TeamRepository teamRepository,
                       TeamPlayerRepository teamPlayerRepository,
                       TournamentRepository tournamentRepository,
                       UserRepository userRepository,
                       TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.teamPlayerRepository = teamPlayerRepository;
        this.tournamentRepository = tournamentRepository;
        this.userRepository = userRepository;
        this.teamMapper = teamMapper;
    }

    /**
     * Creates a new team after validating its input data and related references.
     *
     * <p>If a tournament identifier is provided, the service verifies that the
     * tournament exists and that the team name is unique within it. If a captain
     * identifier is provided, the service validates that the user exists and is
     * not already captain of another team.</p>
     *
     * @param team team data to validate and persist
     * @return the persisted team mapped back to the service model
     * @throws RuntimeException if the payload is null, required fields are missing,
     * related records do not exist, or business rules are violated
     */
    @Override
    @Transactional
    public TeamResponseModel createTeam(TeamResponseModel team) {
        if (team == null) {
            throw new RuntimeException("Team data cannot be null");
        }
        if (team.getName() == null || team.getName().trim().isEmpty()) {
            throw new RuntimeException("Team name is required");
        }

        // Resolve tournament FK
        TournamentEntity tournament = null;
        if (team.getTournamentId() != null) {
            tournament = tournamentRepository.findById(team.getTournamentId())
                    .orElseThrow(() -> new RuntimeException("Tournament not found"));

            // Validate unique name within tournament
            if (teamRepository.existsByNameAndTournament_TournamentId(
                    team.getName(), team.getTournamentId())) {
                throw new RuntimeException("A team with that name already exists in this tournament");
            }
        }

        // Resolve captain FK
        UserEntity captain = null;
        if (team.getCaptainId() != null) {
            captain = userRepository.findById(team.getCaptainId())
                    .orElseThrow(() -> new RuntimeException("Captain user not found"));

            // Validate captain is not already captain of another team
            if (teamRepository.findByCaptain_UserId(team.getCaptainId()).isPresent()) {
                throw new RuntimeException("User is already captain of another team");
            }
        }

        TeamEntity entity = teamMapper.toEntity(team);
        entity.setTournament(tournament);
        entity.setCaptain(captain);
        entity.setStatus(true);
        entity.setDateInscription(LocalDate.now());

        TeamEntity saved = teamRepository.save(entity);

        // Register captain as a player in team_player
        if (captain != null) {
            TeamPlayerEntity captainPlayer = new TeamPlayerEntity(saved, captain);
            teamPlayerRepository.save(captainPlayer);
        }

        log.info("Team created with id: {}", saved.getTeamId());
        return teamMapper.toModel(saved);
    }

    /**
     * Retrieves a team by its identifier.
     *
     * @param id unique team identifier
     * @return the mapped team model
     * @throws RuntimeException if the team does not exist
     */
    @Override
    public TeamResponseModel getTeam(Long id) {
        TeamEntity entity = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        return teamMapper.toModel(entity);
    }

    /**
     * Retrieves every stored team.
     *
     * @return a list of mapped team models, which may be empty
     */
    @Override
    public List<TeamResponseModel> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(teamMapper::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all teams associated with a given tournament.
     *
     * @param tournamentId identifier of the tournament used as filter
     * @return a list of mapped team models for the requested tournament
     */
    @Override
    public List<TeamResponseModel> getTeamsByTournament(Long tournamentId) {
        return teamRepository.findByTournament_TournamentId(tournamentId)
                .stream()
                .map(teamMapper::toModel)
                .collect(Collectors.toList());
    }

    /**
     * Updates selected fields of an existing team.
     *
     * <p>Only non-null values are applied. When the captain is changed, the
     * service validates that the new captain exists and is not assigned as
     * captain of another team.</p>
     *
     * @param id identifier of the team to update
     * @param team payload containing the fields to modify
     * @return the updated team mapped back to the service model
     * @throws RuntimeException if the payload is null, the team does not exist,
     * or the new captain assignment is invalid
     */
    @Override
    @Transactional
    public TeamResponseModel updateTeam(Long id, TeamResponseModel team) {
        if (team == null) {
            throw new RuntimeException("Update data cannot be null");
        }

        TeamEntity existing = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));

        if (team.getName() != null && !team.getName().trim().isEmpty()) {
            existing.setName(team.getName());
        }
        if (team.getLogo() != null) {
            existing.setLogo(team.getLogo());
        }
        if (team.getUniformColor() != null) {
            existing.setUniformColor(team.getUniformColor());
        }

        // Update captain if provided
        if (team.getCaptainId() != null) {
            // Check captain is not already captain of another team
            teamRepository.findByCaptain_UserId(team.getCaptainId())
                    .filter(t -> !t.getTeamId().equals(id))
                    .ifPresent(t -> {
                        throw new RuntimeException("User is already captain of another team");
                    });

            UserEntity captain = userRepository.findById(team.getCaptainId())
                    .orElseThrow(() -> new RuntimeException("Captain user not found"));
            existing.setCaptain(captain);
        }

        TeamEntity saved = teamRepository.save(existing);
        return teamMapper.toModel(saved);
    }

    /**
     * Performs a logical delete by marking the team as inactive.
     *
     * @param id identifier of the team to deactivate
     * @throws RuntimeException if the team does not exist
     */
    @Override
    @Transactional
    public void deactivateTeam(Long id) {
        TeamEntity entity = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        entity.setStatus(false);
        teamRepository.save(entity);
        log.info("Team {} deactivated", id);
    }
}
