package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.model.TournamentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TournamentServiceTest {

    private ITournamentService tournamentService;

    private static final LocalDateTime FUTURE_START = LocalDateTime.now().plusDays(10);
    private static final LocalDateTime FUTURE_END   = LocalDateTime.now().plusDays(20);

    @BeforeEach
    void setUp() {
        tournamentService = new TournamentService();
    }
    

    private Tournament buildValidTournament(String name) {
        Tournament t = new Tournament();
        t.setName(name);
        t.setStartDate(FUTURE_START);
        t.setEndDate(FUTURE_END);
        t.setMaxOfTeams(8);
        t.setTeamCost(new BigDecimal("50000"));
        return t;
    }

    //CREATE
    

    @DisplayName("Should create a tournament with all valid fields")
    @Test
    void shouldCreateTournamentWithValidData() {
        Tournament result = tournamentService.createTournament(buildValidTournament("TechCup 2025-I"));
        assertNotNull(result.getId());
        assertEquals("TechCup 2025-I", result.getName());
        assertEquals(TournamentStatus.DRAFT.name(), result.getStatus());
    }

    @DisplayName("Should assign DRAFT as initial status when creating a tournament")
    @Test
    void shouldAssignDraftStatusOnCreate() {
        Tournament result = tournamentService.createTournament(buildValidTournament("TechCup Draft"));
        assertEquals(TournamentStatus.DRAFT.name(), result.getStatus());
    }

    @DisplayName("Should auto-assign different IDs to each tournament")
    @Test
    void shouldAutoAssignIdOnCreate() {
        Tournament r1 = tournamentService.createTournament(buildValidTournament("Cup A"));
        Tournament r2 = tournamentService.createTournament(buildValidTournament("Cup B"));
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
        Tournament t = buildValidTournament("NullStart");
        t.setStartDate(null);
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should throw exception when end date is null")
    @Test
    void shouldFailCreateWhenEndDateIsNull() {
        Tournament t = buildValidTournament("NullEnd");
        t.setEndDate(null);
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should throw exception when end date is before start date")
    @Test
    void shouldFailCreateWhenEndDateBeforeStartDate() {
        Tournament t = buildValidTournament("BadDates");
        t.setStartDate(LocalDateTime.now().plusDays(20));
        t.setEndDate(LocalDateTime.now().plusDays(5));
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should throw exception when maxOfTeams is less than 2")
    @Test
    void shouldFailCreateWhenMaxTeamsLessThanTwo() {
        Tournament t = buildValidTournament("FewTeams");
        t.setMaxOfTeams(1);
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should throw exception when maxOfTeams is null")
    @Test
    void shouldFailCreateWhenMaxTeamsIsNull() {
        Tournament t = buildValidTournament("NullTeams");
        t.setMaxOfTeams(null);
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should throw exception when team cost is negative")
    @Test
    void shouldFailCreateWhenTeamCostIsNegative() {
        Tournament t = buildValidTournament("NegCost");
        t.setTeamCost(new BigDecimal("-1"));
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should allow creating a tournament with zero team cost")
    @Test
    void shouldAllowZeroTeamCost() {
        Tournament t = buildValidTournament("FreeCup");
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


    @DisplayName("Should return a tournament by its ID")
    @Test
    void shouldGetTournamentById() {
        Tournament created = tournamentService.createTournament(buildValidTournament("Cup Read"));
        Tournament found = tournamentService.getTournament(created.getId());
        assertEquals("Cup Read", found.getName());
    }

    @DisplayName("Should throw exception when tournament ID does not exist")
    @Test
    void shouldFailGetWhenTournamentNotFound() {
        assertThrows(RuntimeException.class, () -> tournamentService.getTournament(999L));
    }

    @DisplayName("Should return all created tournaments")
    @Test
    void shouldGetAllTournaments() {
        tournamentService.createTournament(buildValidTournament("Cup 1"));
        tournamentService.createTournament(buildValidTournament("Cup 2"));
        tournamentService.createTournament(buildValidTournament("Cup 3"));
        assertEquals(3, tournamentService.getAllTournaments().size());
    }

    @DisplayName("Should return empty list when no tournaments exist")
    @Test
    void shouldReturnEmptyListWhenNoTournaments() {
        assertTrue(tournamentService.getAllTournaments().isEmpty());
    }

    //UPDATE


    @DisplayName("Should update tournament name successfully when in DRAFT")
    @Test
    void shouldUpdateTournamentName() {
        Tournament created = tournamentService.createTournament(buildValidTournament("Old Name"));
        Tournament update = new Tournament();
        update.setName("New Name");
        assertEquals("New Name", tournamentService.updateTournament(created.getId(), update).getName());
    }

    @DisplayName("Should throw exception when updating a FINISHED tournament")
    @Test
    void shouldFailUpdateWhenTournamentIsFinished() {
        Tournament created = tournamentService.createTournament(buildValidTournament("FinishedCup"));
        created.setStatus(TournamentStatus.FINISHED.name());
        Tournament update = new Tournament();
        update.setName("Renamed");
        assertThrows(RuntimeException.class,
                () -> tournamentService.updateTournament(created.getId(), update));
    }

    @DisplayName("Should throw exception when updating with null payload")
    @Test
    void shouldFailUpdateWithNullPayload() {
        Tournament created = tournamentService.createTournament(buildValidTournament("NullUpdate"));
        assertThrows(RuntimeException.class,
                () -> tournamentService.updateTournament(created.getId(), null));
    }

    @DisplayName("Should throw exception when updating a non-existing tournament")
    @Test
    void shouldFailUpdateWhenTournamentNotFound() {
        Tournament update = new Tournament();
        update.setName("Ghost Cup");
        assertThrows(RuntimeException.class, () -> tournamentService.updateTournament(999L, update));
    }

    @DisplayName("Should keep the same ID after update")
    @Test
    void shouldKeepSameIdAfterUpdate() {
        Tournament created = tournamentService.createTournament(buildValidTournament("IdCup"));
        Tournament update = new Tournament();
        update.setName("IdCup Updated");
        assertEquals(created.getId(), tournamentService.updateTournament(created.getId(), update).getId());
    }


    @DisplayName("Should cancel a tournament in DRAFT status")
    @Test
    void shouldCancelDraftTournament() {
        Tournament created = tournamentService.createTournament(buildValidTournament("CancelMe"));
        tournamentService.cancelTournament(created.getId());
        assertThrows(RuntimeException.class, () -> tournamentService.getTournament(created.getId()));
    }

    @DisplayName("Should throw exception when cancelling a tournament that is not DRAFT")
    @Test
    void shouldFailCancelWhenTournamentIsNotDraft() {
        Tournament created = tournamentService.createTournament(buildValidTournament("ActiveCup"));
        tournamentService.startTournament(created.getId()); // pasa a ACTIVE
        assertThrows(RuntimeException.class, () -> tournamentService.cancelTournament(created.getId()));
    }

    @DisplayName("Should throw exception when cancelling a non-existing tournament")
    @Test
    void shouldFailCancelWhenTournamentNotFound() {
        assertThrows(RuntimeException.class, () -> tournamentService.cancelTournament(999L));
    }

    @DisplayName("Should reduce tournament count by one after cancellation")
    @Test
    void shouldDecreaseTournamentCountAfterCancel() {
        tournamentService.createTournament(buildValidTournament("Keep"));
        Tournament toCancel = tournamentService.createTournament(buildValidTournament("Remove"));
        tournamentService.cancelTournament(toCancel.getId());
        assertEquals(1, tournamentService.getAllTournaments().size());
    }

    //LIFECYCLE

    @DisplayName("Should change status to ACTIVE when starting a DRAFT tournament")
    @Test
    void shouldStartTournamentFromDraft() {
        Tournament created = tournamentService.createTournament(buildValidTournament("StartMe"));
        tournamentService.startTournament(created.getId());
        assertEquals(TournamentStatus.ACTIVE.name(), created.getStatus());
    }

    @DisplayName("Should throw exception when starting a tournament that is not in DRAFT")
    @Test
    void shouldFailStartWhenTournamentIsNotDraft() {
        Tournament created = tournamentService.createTournament(buildValidTournament("AlreadyActive"));
        tournamentService.startTournament(created.getId());
        assertThrows(RuntimeException.class, () -> tournamentService.startTournament(created.getId()));
    }

    @DisplayName("Should change status to FINISHED when finishing an IN_PROGRESS tournament")
    @Test
    void shouldFinishTournamentFromInProgress() {
        Tournament created = tournamentService.createTournament(buildValidTournament("FinishMe"));
        created.setStatus(TournamentStatus.IN_PROGRESS.name());
        tournamentService.finishTournament(created.getId());
        assertEquals(TournamentStatus.FINISHED.name(), created.getStatus());
    }

    @DisplayName("Should throw exception when finishing a tournament that is not IN_PROGRESS")
    @Test
    void shouldFailFinishWhenTournamentIsNotInProgress() {
        Tournament created = tournamentService.createTournament(buildValidTournament("NotInProgress"));
        assertThrows(RuntimeException.class, () -> tournamentService.finishTournament(created.getId()));
    }

    @DisplayName("A DRAFT tournament should not be considered active")
    @Test
    void shouldNotBeActiveWhenDraft() {
        Tournament created = tournamentService.createTournament(buildValidTournament("DraftCup"));
        assertFalse(created.isActive());
    }

    @DisplayName("An ACTIVE tournament should be considered active")
    @Test
    void shouldBeActiveWhenActive() {
        Tournament created = tournamentService.createTournament(buildValidTournament("IsActiveCup"));
        tournamentService.startTournament(created.getId());
        assertTrue(created.isActive());
    }
}