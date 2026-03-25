package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.TeamEntity;
import edu.eci.dosw.tech_cup.entity.TournamentEntity;
import edu.eci.dosw.tech_cup.entity.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private UserRepository userRepository;

    private TournamentEntity tournament;
    private UserEntity captain;

    // ─── Setup ──────────────────────────────────────────────────────────────

    @BeforeEach
    void setUp() {
        TournamentEntity t = new TournamentEntity();
        t.setName("TechCup 2025");
        t.setStartDate(LocalDate.now().plusDays(10));
        t.setEndDate(LocalDate.now().plusDays(20));
        t.setNumberOfTeams(8);
        t.setTeamCost(new BigDecimal("50000"));
        t.setStatus("draft");
        tournament = tournamentRepository.save(t);

        UserEntity u = new UserEntity();
        u.setFirstName("Carlos");
        u.setLastName("López");
        u.setEmail("capitan@mail.escuelaing.edu.co");
        u.setPasswordUser("pass123");
        u.setIdentification("5001");
        u.setDateBirth(LocalDate.of(1998, 5, 20));
        u.setStatus(true);
        captain = userRepository.save(u);
    }

    private TeamEntity buildTeam(String name) {
        TeamEntity team = new TeamEntity();
        team.setName(name);
        team.setLogo("logo.png");
        team.setUniformColor("Rojo");
        team.setStatus(true);
        team.setDateInscription(LocalDate.now());
        team.setTournament(tournament);
        return team;
    }

    // ─── 1. Prueba de guardado ───────────────────────────────────────────────

    @DisplayName("Should save a team and assign an auto-generated id")
    @Test
    void shouldSaveTeam() {
        TeamEntity team = buildTeam("Los Cracks");

        TeamEntity saved = teamRepository.save(team);

        assertNotNull(saved.getTeamId());
        assertEquals("Los Cracks", saved.getName());
        assertTrue(saved.getStatus());
    }

    // ─── 2. Prueba de consulta ───────────────────────────────────────────────

    @DisplayName("Should find teams by tournament id")
    @Test
    void shouldFindByTournamentId() {
        teamRepository.save(buildTeam("Equipo Alpha"));
        teamRepository.save(buildTeam("Equipo Beta"));

        List<TeamEntity> teams = teamRepository.findByTournament_TournamentId(tournament.getTournamentId());

        assertEquals(2, teams.size());
    }

    @DisplayName("Should find teams by status")
    @Test
    void shouldFindByStatus() {
        TeamEntity active = buildTeam("Activos FC");
        TeamEntity inactive = buildTeam("Inactivos SC");
        inactive.setStatus(false);

        teamRepository.save(active);
        teamRepository.save(inactive);

        assertEquals(1, teamRepository.findByStatus(true).size());
        assertEquals(1, teamRepository.findByStatus(false).size());
    }

    // ─── 3. Prueba de relación Team → Tournament y Team → User (capitán) ────

    @DisplayName("Should find a team by captain user id")
    @Test
    void shouldFindByCaptainUserId() {
        TeamEntity team = buildTeam("Capitanes United");
        team.setCaptain(captain);
        teamRepository.save(team);

        Optional<TeamEntity> found = teamRepository.findByCaptain_UserId(captain.getUserId());

        assertTrue(found.isPresent());
        assertEquals("Capitanes United", found.get().getName());
        assertEquals(captain.getUserId(), found.get().getCaptain().getUserId());
    }

    @DisplayName("Should verify team name is unique within a tournament")
    @Test
    void shouldCheckNameExistsInTournament() {
        teamRepository.save(buildTeam("Nombre Unico"));

        assertTrue(teamRepository.existsByNameAndTournament_TournamentId(
                "Nombre Unico", tournament.getTournamentId()));
        assertFalse(teamRepository.existsByNameAndTournament_TournamentId(
                "Otro Nombre", tournament.getTournamentId()));
    }

    @DisplayName("Should persist team with its tournament relationship")
    @Test
    void shouldPersistTeamWithTournament() {
        TeamEntity team = buildTeam("Team con Torneo");
        TeamEntity saved = teamRepository.save(team);

        assertNotNull(saved.getTournament());
        assertEquals(tournament.getTournamentId(), saved.getTournament().getTournamentId());
    }

    // ─── 4. Prueba de actualización y eliminación ────────────────────────────

    @DisplayName("Should update team name")
    @Test
    void shouldUpdateTeamName() {
        TeamEntity team = teamRepository.save(buildTeam("Nombre Viejo"));

        team.setName("Nombre Nuevo");
        TeamEntity updated = teamRepository.save(team);

        assertEquals("Nombre Nuevo", updated.getName());
    }

    @DisplayName("Should delete a team by id")
    @Test
    void shouldDeleteTeam() {
        TeamEntity team = teamRepository.save(buildTeam("Equipo a Eliminar"));
        Long id = team.getTeamId();

        teamRepository.deleteById(id);

        assertFalse(teamRepository.findById(id).isPresent());
    }
}