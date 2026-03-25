package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.TournamentEntity;
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
class TournamentRepositoryTest {

    @Autowired
    private TournamentRepository tournamentRepository;

  

    private TournamentEntity buildTournament(String name, String status) {
        TournamentEntity t = new TournamentEntity();
        t.setName(name);
        t.setStartDate(LocalDate.now().plusDays(10));
        t.setEndDate(LocalDate.now().plusDays(20));
        t.setNumberOfTeams(8);
        t.setTeamCost(new BigDecimal("50000"));
        t.setStatus(status);
        return t;
    }

  

    @DisplayName("Should save a tournament and assign an auto-generated id")
    @Test
    void shouldSaveTournament() {
        TournamentEntity tournament = buildTournament("TechCup 2025-I", "draft");

        TournamentEntity saved = tournamentRepository.save(tournament);

        assertNotNull(saved.getTournamentId());
        assertEquals("TechCup 2025-I", saved.getName());
        assertEquals("draft", saved.getStatus());
    }



    @DisplayName("Should find a tournament by name ignoring case")
    @Test
    void shouldFindByNameIgnoreCase() {
        tournamentRepository.save(buildTournament("TechCup 2025-I", "draft"));

        Optional<TournamentEntity> upper = tournamentRepository.findByNameIgnoreCase("TECHCUP 2025-I");
        Optional<TournamentEntity> lower = tournamentRepository.findByNameIgnoreCase("techcup 2025-i");

        assertTrue(upper.isPresent());
        assertTrue(lower.isPresent());
    }

    @DisplayName("Should return true when tournament name already exists")
    @Test
    void shouldDetectExistingName() {
        tournamentRepository.save(buildTournament("Copa ECI", "draft"));

        assertTrue(tournamentRepository.existsByNameIgnoreCase("Copa ECI"));
        assertTrue(tournamentRepository.existsByNameIgnoreCase("copa eci"));
        assertFalse(tournamentRepository.existsByNameIgnoreCase("Otro Torneo"));
    }

    @DisplayName("Should find tournaments by status")
    @Test
    void shouldFindByStatus() {
        tournamentRepository.save(buildTournament("Draft Cup", "draft"));
        tournamentRepository.save(buildTournament("Active Cup", "active"));
        tournamentRepository.save(buildTournament("Progress Cup", "in_progress"));

        List<TournamentEntity> drafts = tournamentRepository.findByStatus("draft");
        List<TournamentEntity> actives = tournamentRepository.findByStatus("active");

        assertEquals(1, drafts.size());
        assertEquals(1, actives.size());
    }

   

    @DisplayName("Should save two tournaments with different names independently")
    @Test
    void shouldSaveMultipleTournamentsIndependently() {
        TournamentEntity t1 = tournamentRepository.save(buildTournament("Cup A", "draft"));
        TournamentEntity t2 = tournamentRepository.save(buildTournament("Cup B", "active"));

        assertNotEquals(t1.getTournamentId(), t2.getTournamentId());
        assertEquals(2, tournamentRepository.findAll().size());
    }

   

    @DisplayName("Should update tournament status")
    @Test
    void shouldUpdateTournamentStatus() {
        TournamentEntity tournament = tournamentRepository.save(buildTournament("UpdateCup", "draft"));

        tournament.setStatus("active");
        TournamentEntity updated = tournamentRepository.save(tournament);

        assertEquals("active", updated.getStatus());
    }

    @DisplayName("Should delete a tournament by id")
    @Test
    void shouldDeleteTournament() {
        TournamentEntity tournament = tournamentRepository.save(buildTournament("DeleteCup", "draft"));
        Long id = tournament.getTournamentId();

        tournamentRepository.deleteById(id);

        assertFalse(tournamentRepository.findById(id).isPresent());
    }
}
