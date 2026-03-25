package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.TournamentModel;
import edu.eci.dosw.tech_cup.model.TournamentStatusModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentServiceTest {

    private ITournamentService tournamentService;

    private static final LocalDateTime FUTURE_START = LocalDateTime.now().plusDays(10);
    private static final LocalDateTime FUTURE_END   = LocalDateTime.now().plusDays(20);

    @BeforeEach
    void setUp() {
        tournamentService = new TournamentService();
    }

    private TournamentModel buildValidTournament(String name) {
        TournamentModel t = new TournamentModel();
        t.setName(name);
        t.setStartDate(FUTURE_START);
        t.setEndDate(FUTURE_END);
        t.setMaxOfTeams(8);
        t.setTeamCost(new BigDecimal("50000"));
        return t;
    }

    // ================= CREATE =================

    @DisplayName("Should create a tournament with all valid fields")
    @Test
    void shouldCreateTournamentWithValidData() {
        TournamentModel result = tournamentService.createTournament(buildValidTournament("TechCup 2025-I"));

        assertNotNull(result.getId());
        assertEquals("TechCup 2025-I", result.getName());
        assertEquals(TournamentStatusModel.DRAFT, result.getStatus());
    }

    @DisplayName("Should assign DRAFT as initial status when creating a tournament")
    @Test
    void shouldAssignDraftStatusOnCreate() {
        TournamentModel result = tournamentService.createTournament(buildValidTournament("TechCup Draft"));

        assertEquals(TournamentStatusModel.DRAFT, result.getStatus());
    }

    @DisplayName("Should auto-assign different IDs to each tournament")
    @Test
    void shouldAutoAssignIdOnCreate() {
        TournamentModel r1 = tournamentService.createTournament(buildValidTournament("Cup A"));
        TournamentModel r2 = tournamentService.createTournament(buildValidTournament("Cup B"));

        assertNotEquals(r1.getId(), r2.getId());
    }

    @DisplayName("Should throw exception when creating a null tournament")
    @Test
    void shouldFailCreateWhenTournamentIsNull() {
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(null));
    }

    @DisplayName("Should throw exception when name is null")
    @Test
    void shouldFailCreateWhenNameIsNull() {
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(buildValidTournament(null)));
    }

    @DisplayName("Should throw exception when name is blank")
    @Test
    void shouldFailCreateWhenNameIsBlank() {
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(buildValidTournament("   ")));
    }

    @DisplayName("Should throw exception when start date is null")
    @Test
    void shouldFailCreateWhenStartDateIsNull() {
        TournamentModel t = buildValidTournament("NullStart");
        t.setStartDate(null);

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should throw exception when end date is null")
    @Test
    void shouldFailCreateWhenEndDateIsNull() {
        TournamentModel t = buildValidTournament("NullEnd");
        t.setEndDate(null);

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should throw exception when end date is before start date")
    @Test
    void shouldFailCreateWhenEndDateBeforeStartDate() {
        TournamentModel t = buildValidTournament("BadDates");
        t.setStartDate(LocalDateTime.now().plusDays(20));
        t.setEndDate(LocalDateTime.now().plusDays(5));

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should throw exception when maxOfTeams is less than 2")
    @Test
    void shouldFailCreateWhenMaxTeamsLessThanTwo() {
        TournamentModel t = buildValidTournament("FewTeams");
        t.setMaxOfTeams(1);

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should throw exception when maxOfTeams is null")
    @Test
    void shouldFailCreateWhenMaxTeamsIsNull() {
        TournamentModel t = buildValidTournament("NullTeams");
        t.setMaxOfTeams(null);

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should throw exception when team cost is negative")
    @Test
    void shouldFailCreateWhenTeamCostIsNegative() {
        TournamentModel t = buildValidTournament("NegCost");
        t.setTeamCost(new BigDecimal("-1"));

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should allow creating a tournament with zero team cost")
    @Test
    void shouldAllowZeroTeamCost() {
        TournamentModel t = buildValidTournament("FreeCup");
        t.setTeamCost(BigDecimal.ZERO);

        assertNotNull(tournamentService.createTournament(t).getId());
    }

    @DisplayName("Should throw exception when a tournament with the same name already exists")
    @Test
    void shouldFailCreateWhenNameAlreadyExists() {
        tournamentService.createTournament(buildValidTournament("TechCup 2025-I"));

        assertThrows(RuntimeException.class,
                () -> tournamentService.createTournament(buildValidTournament("TechCup 2025-I")));
    }

    // ================= LIFECYCLE =================

    @DisplayName("Should move tournament from DRAFT to ACTIVE when starting")
    @Test
    void shouldStartTournamentFromDraft() {
        TournamentModel created = tournamentService.createTournament(buildValidTournament("StartMe"));

        tournamentService.startTournament(created.getId());

        assertEquals(TournamentStatusModel.ACTIVE, created.getStatus());
    }

    @DisplayName("Should move tournament from ACTIVE to IN_PROGRESS when starting again")
    @Test
    void shouldMoveFromActiveToInProgress() {
        TournamentModel created = tournamentService.createTournament(buildValidTournament("StartMe"));

        tournamentService.startTournament(created.getId());
        tournamentService.startTournament(created.getId());

        assertEquals(TournamentStatusModel.IN_PROGRESS, created.getStatus());
    }

    @DisplayName("Should finish tournament from IN_PROGRESS")
    @Test
    void shouldFinishTournamentFromInProgress() {
        TournamentModel created = tournamentService.createTournament(buildValidTournament("FinishMe"));

        tournamentService.startTournament(created.getId());
        tournamentService.startTournament(created.getId());

        tournamentService.finishTournament(created.getId());

        assertEquals(TournamentStatusModel.FINISHED, created.getStatus());
    }

    @DisplayName("Should not consider DRAFT as active")
    @Test
    void shouldNotBeActiveWhenDraft() {
        TournamentModel created = tournamentService.createTournament(buildValidTournament("DraftCup"));

        assertFalse(created.isActive());
    }

    @DisplayName("Should be active when tournament is ACTIVE")
    @Test
    void shouldBeActiveWhenActive() {
        TournamentModel created = tournamentService.createTournament(buildValidTournament("ActiveCup"));

        tournamentService.startTournament(created.getId());

        assertTrue(created.isActive());
    }

    @DisplayName("Should be active when tournament is IN_PROGRESS")
    @Test
    void shouldBeActiveWhenInProgress() {
        TournamentModel created = tournamentService.createTournament(buildValidTournament("ProgressCup"));

        tournamentService.startTournament(created.getId());
        tournamentService.startTournament(created.getId());

        assertTrue(created.isActive());
    }
}