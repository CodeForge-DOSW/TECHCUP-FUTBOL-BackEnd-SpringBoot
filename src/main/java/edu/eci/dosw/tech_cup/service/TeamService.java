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
 * Implementation of team business operations.
 *
 * <p>Coordinates access to {@link TeamRepository} and resolves FK references
 * (tournament, captain) before persisting.</p>
 */
@Service
public class TeamService implements ITeamService {

    private static final Logger log = LoggerFactory.getLogger(TeamService.class);

    private final TeamRepository teamRepository;
    private final TeamPlayerRepository teamPlayerRepository;
    private final TournamentRepository tournamentRepository;
    private final UserRepository userRepository;
    private final TeamMapper teamMapper;

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

    @Override
    public TeamResponseModel getTeam(Long id) {
        TeamEntity entity = teamRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Team not found"));
        return teamMapper.toModel(entity);
    }

    @Override
    public List<TeamResponseModel> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(teamMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<TeamResponseModel> getTeamsByTournament(Long tournamentId) {
        return teamRepository.findByTournament_TournamentId(tournamentId)
                .stream()
                .map(teamMapper::toModel)
                .collect(Collectors.toList());
    }

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