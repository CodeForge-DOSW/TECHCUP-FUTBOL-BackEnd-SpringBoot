package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.TeamEntity;
import edu.eci.dosw.tech_cup.entity.TournamentEntity;
import edu.eci.dosw.tech_cup.entity.UserEntity;
import edu.eci.dosw.tech_cup.mapper.TeamMapper;
import edu.eci.dosw.tech_cup.model.TeamResponseModel;
import edu.eci.dosw.tech_cup.repository.TeamPlayerRepository;
import edu.eci.dosw.tech_cup.repository.TeamRepository;
import edu.eci.dosw.tech_cup.repository.TournamentRepository;
import edu.eci.dosw.tech_cup.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link TeamService}.
 *
 * <p>This test suite validates the service rules for team creation, retrieval,
 * update, filtering, and logical deactivation by using mocked repositories
 * and mapper dependencies.</p>
 */
@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @Mock
    private TeamPlayerRepository teamPlayerRepository;

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TeamMapper teamMapper;

    @InjectMocks
    private TeamService teamService;

    /**
     * Builds a valid team request model for service tests.
     *
     * @param name team name to assign
     * @param tournamentId related tournament identifier
     * @param captainId related captain identifier
     * @return a team payload ready for service operations
     */
    private TeamResponseModel buildValidTeam(String name, Long tournamentId, Long captainId) {
        TeamResponseModel t = new TeamResponseModel();
        t.setName(name);
        t.setTournamentId(tournamentId);
        t.setCaptainId(captainId);
        t.setLogo("https://example.com/logo.png");
        t.setUniformColor("azul");
        return t;
    }

    /**
     * Builds a team entity with the provided identity and status values.
     *
     * @param id persisted team identifier
     * @param name team name to assign
     * @param active whether the team is active
     * @return a team entity configured for mock responses
     */
    private TeamEntity entityWith(Long id, String name, boolean active) {
        TeamEntity e = new TeamEntity();
        e.setTeamId(id);
        e.setName(name);
        e.setStatus(active);
        e.setDateInscription(LocalDate.now());
        return e;
    }

    /**
     * Builds a team response model with the provided values.
     *
     * @param id team identifier
     * @param name team name
     * @param active whether the team is active
     * @param tournamentId related tournament identifier
     * @param captainId related captain identifier
     * @return a response model configured for assertions
     */
    private TeamResponseModel responseWith(Long id, String name, boolean active,
                                           Long tournamentId, Long captainId) {
        TeamResponseModel m = new TeamResponseModel();
        m.setId(id);
        m.setName(name);
        m.setActive(active);
        m.setTournamentId(tournamentId);
        m.setCaptainId(captainId);
        m.setDateInscription(LocalDate.now());
        return m;
    }

    /**
     * Builds a tournament entity for mock repository responses.
     *
     * @param id tournament identifier
     * @return a tournament entity with basic valid data
     */
    private TournamentEntity tournamentEntity(Long id) {
        TournamentEntity t = new TournamentEntity();
        t.setTournamentId(id);
        t.setName("TechCup");
        t.setStatus("active");
        return t;
    }

    /**
     * Builds a user entity for mock captain lookups.
     *
     * @param id user identifier
     * @return a user entity with basic valid data
     */
    private UserEntity userEntity(Long id) {
        UserEntity u = new UserEntity();
        u.setUserId(id);
        u.setEmail("captain@eci.edu.co");
        u.setFirstName("Juan");
        return u;
    }

    /**
     * Verifies that a team is created successfully when all required data is valid.
     */
    @DisplayName("Should create a team successfully with valid data")
    @Test
    void shouldCreateTeam() {
        TeamResponseModel input = buildValidTeam("Los Debuggers", 1L, 2L);
        TournamentEntity tournament = tournamentEntity(1L);
        UserEntity captain = userEntity(2L);
        TeamEntity savedEntity = entityWith(1L, "Los Debuggers", true);
        TeamResponseModel expected = responseWith(1L, "Los Debuggers", true, 1L, 2L);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(teamRepository.existsByNameAndTournament_TournamentId("Los Debuggers", 1L)).thenReturn(false);
        when(userRepository.findById(2L)).thenReturn(Optional.of(captain));
        when(teamRepository.findByCaptain_UserId(2L)).thenReturn(Optional.empty());
        when(teamMapper.toEntity(input)).thenReturn(savedEntity);
        when(teamRepository.save(savedEntity)).thenReturn(savedEntity);
        when(teamMapper.toModel(savedEntity)).thenReturn(expected);

        TeamResponseModel result = teamService.createTeam(input);

        assertNotNull(result.getId());
        assertEquals("Los Debuggers", result.getName());
        assertTrue(result.isActive());
        assertEquals(1L, result.getTournamentId());
        assertEquals(2L, result.getCaptainId());
    }

    /**
     * Verifies that team creation fails when the request payload is null.
     */
    @DisplayName("Should throw exception when creating team with null data")
    @Test
    void shouldFailCreateWhenNull() {
        assertThrows(RuntimeException.class, () -> teamService.createTeam(null));
        verifyNoInteractions(teamRepository, teamMapper);
    }

    @DisplayName("Should throw exception when team name is null")
    @Test
    void shouldFailCreateWhenNameIsNull() {
        TeamResponseModel input = buildValidTeam(null, 1L, 2L);
        assertThrows(RuntimeException.class, () -> teamService.createTeam(input));
    }

    /**
     * Verifies that team creation fails when the team name is blank.
     */
    @DisplayName("Should throw exception when team name is blank")
    @Test
    void shouldFailCreateWhenNameIsBlank() {
        TeamResponseModel input = buildValidTeam("   ", 1L, 2L);
        assertThrows(RuntimeException.class, () -> teamService.createTeam(input));
    }

    @DisplayName("Should throw exception when tournament not found")
    @Test
    void shouldFailCreateWhenTournamentNotFound() {
        TeamResponseModel input = buildValidTeam("Team X", 999L, 2L);
        when(tournamentRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> teamService.createTeam(input));
        assertEquals("Tournament not found", ex.getMessage());
    }

    /**
     * Verifies that team creation fails when the captain user does not exist.
     */
    @DisplayName("Should throw exception when captain user not found")
    @Test
    void shouldFailCreateWhenCaptainNotFound() {
        TeamResponseModel input = buildValidTeam("Team X", 1L, 999L);
        TournamentEntity tournament = tournamentEntity(1L);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(teamRepository.existsByNameAndTournament_TournamentId("Team X", 1L)).thenReturn(false);
        when(userRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> teamService.createTeam(input));
        assertEquals("Captain user not found", ex.getMessage());
    }

    /**
     * Verifies that team creation fails when the team name already exists in the same tournament.
     */
    @DisplayName("Should throw exception when team name already exists in tournament")
    @Test
    void shouldFailCreateWhenDuplicateNameInTournament() {
        TeamResponseModel input = buildValidTeam("Existing Team", 1L, 2L);
        TournamentEntity tournament = tournamentEntity(1L);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(teamRepository.existsByNameAndTournament_TournamentId("Existing Team", 1L)).thenReturn(true);

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> teamService.createTeam(input));
        assertTrue(ex.getMessage().contains("already exists"));
    }

    /**
     * Verifies that team creation fails when the selected user already captains another team.
     */
    @DisplayName("Should throw exception when user is already captain of another team")
    @Test
    void shouldFailCreateWhenCaptainAlreadyTaken() {
        TeamResponseModel input = buildValidTeam("New Team", 1L, 2L);
        TournamentEntity tournament = tournamentEntity(1L);
        UserEntity captain = userEntity(2L);
        TeamEntity existingTeam = entityWith(5L, "Other Team", true);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(tournament));
        when(teamRepository.existsByNameAndTournament_TournamentId("New Team", 1L)).thenReturn(false);
        when(userRepository.findById(2L)).thenReturn(Optional.of(captain));
        when(teamRepository.findByCaptain_UserId(2L)).thenReturn(Optional.of(existingTeam));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> teamService.createTeam(input));
        assertTrue(ex.getMessage().contains("already captain"));
    }

    /**
     * Verifies that a team can be created without a tournament assignment.
     */
    @DisplayName("Should allow creating team without tournament (null tournamentId)")
    @Test
    void shouldCreateTeamWithoutTournament() {
        TeamResponseModel input = buildValidTeam("Free Agents", null, null);
        TeamEntity savedEntity = entityWith(1L, "Free Agents", true);
        TeamResponseModel expected = responseWith(1L, "Free Agents", true, null, null);

        when(teamMapper.toEntity(input)).thenReturn(savedEntity);
        when(teamRepository.save(savedEntity)).thenReturn(savedEntity);
        when(teamMapper.toModel(savedEntity)).thenReturn(expected);

        TeamResponseModel result = teamService.createTeam(input);

        assertNotNull(result.getId());
        assertNull(result.getTournamentId());
    }

    /**
     * Verifies that a team can be retrieved by its identifier.
     */
    @DisplayName("Should return team by id")
    @Test
    void shouldGetTeamById() {
        TeamEntity entity = entityWith(1L, "Los Debuggers", true);
        TeamResponseModel model = responseWith(1L, "Los Debuggers", true, 1L, 2L);

        when(teamRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(teamMapper.toModel(entity)).thenReturn(model);

        TeamResponseModel result = teamService.getTeam(1L);

        assertEquals("Los Debuggers", result.getName());
        assertEquals(1L, result.getId());
    }

    /**
     * Verifies that retrieval fails when the requested team does not exist.
     */
    @DisplayName("Should throw exception when team not found")
    @Test
    void shouldFailGetTeamNotFound() {
        when(teamRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> teamService.getTeam(999L));
    }

    /**
     * Verifies that the service returns all teams mapped to response models.
     */
    @DisplayName("Should return all teams")
    @Test
    void shouldGetAllTeams() {
        TeamEntity e1 = entityWith(1L, "Team A", true);
        TeamEntity e2 = entityWith(2L, "Team B", true);
        TeamResponseModel m1 = responseWith(1L, "Team A", true, 1L, null);
        TeamResponseModel m2 = responseWith(2L, "Team B", true, 1L, null);

        when(teamRepository.findAll()).thenReturn(List.of(e1, e2));
        when(teamMapper.toModel(e1)).thenReturn(m1);
        when(teamMapper.toModel(e2)).thenReturn(m2);

        List<TeamResponseModel> teams = teamService.getAllTeams();
        assertEquals(2, teams.size());
    }

    /**
     * Verifies that the service returns an empty list when no teams are stored.
     */
    @DisplayName("Should return empty list when no teams")
    @Test
    void shouldReturnEmptyList() {
        when(teamRepository.findAll()).thenReturn(List.of());
        assertTrue(teamService.getAllTeams().isEmpty());
    }

    /**
     * Verifies that the service filters teams by tournament identifier.
     */
    @DisplayName("Should return teams filtered by tournament")
    @Test
    void shouldGetTeamsByTournament() {
        TeamEntity e1 = entityWith(1L, "Team A", true);
        TeamResponseModel m1 = responseWith(1L, "Team A", true, 1L, null);

        when(teamRepository.findByTournament_TournamentId(1L)).thenReturn(List.of(e1));
        when(teamMapper.toModel(e1)).thenReturn(m1);

        List<TeamResponseModel> teams = teamService.getTeamsByTournament(1L);
        assertEquals(1, teams.size());
        assertEquals(1L, teams.get(0).getTournamentId());
    }

    /**
     * Verifies that updating a team persists the new name.
     */
    @DisplayName("Should update team name")
    @Test
    void shouldUpdateTeamName() {
        TeamEntity existing = entityWith(1L, "Old Name", true);
        TeamEntity savedEntity = entityWith(1L, "New Name", true);
        TeamResponseModel resultModel = responseWith(1L, "New Name", true, 1L, 2L);

        TeamResponseModel updatePayload = new TeamResponseModel();
        updatePayload.setName("New Name");

        when(teamRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(teamRepository.save(existing)).thenReturn(savedEntity);
        when(teamMapper.toModel(savedEntity)).thenReturn(resultModel);

        TeamResponseModel result = teamService.updateTeam(1L, updatePayload);
        assertEquals("New Name", result.getName());
    }

    /**
     * Verifies that updating fails when the target team does not exist.
     */
    @DisplayName("Should throw exception when updating non-existing team")
    @Test
    void shouldFailUpdateTeamNotFound() {
        TeamResponseModel payload = new TeamResponseModel();
        payload.setName("Test");
        when(teamRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> teamService.updateTeam(999L, payload));
    }

    /**
     * Verifies that updating fails when the request payload is null.
     */
    @DisplayName("Should throw exception when updating with null payload")
    @Test
    void shouldFailUpdateWithNull() {
        assertThrows(RuntimeException.class, () -> teamService.updateTeam(1L, null));
    }

    /**
     * Verifies that updating the captain fails when the new captain already leads another team.
     */
    @DisplayName("Should throw exception when new captain is already captain of another team")
    @Test
    void shouldFailUpdateWhenCaptainTaken() {
        TeamEntity existing = entityWith(1L, "My Team", true);
        TeamEntity otherTeam = entityWith(5L, "Other Team", true);

        TeamResponseModel payload = new TeamResponseModel();
        payload.setCaptainId(3L);

        when(teamRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(teamRepository.findByCaptain_UserId(3L)).thenReturn(Optional.of(otherTeam));

        assertThrows(RuntimeException.class, () -> teamService.updateTeam(1L, payload));
    }

    /**
     * Verifies that deactivating a team changes its status to inactive and saves it.
     */
    @DisplayName("Should deactivate team")
    @Test
    void shouldDeactivateTeam() {
        TeamEntity entity = entityWith(1L, "Active Team", true);

        when(teamRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(teamRepository.save(entity)).thenReturn(entity);

        teamService.deactivateTeam(1L);

        verify(teamRepository).save(entity);
        assertFalse(entity.getStatus());
    }

    /**
     * Verifies that deactivation fails when the requested team does not exist.
     */
    @DisplayName("Should throw exception when deactivating non-existing team")
    @Test
    void shouldFailDeactivateNotFound() {
        when(teamRepository.findById(999L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> teamService.deactivateTeam(999L));
    }
}
