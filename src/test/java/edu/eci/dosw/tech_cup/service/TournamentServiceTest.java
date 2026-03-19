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
    

    @DisplayName("Should create a tournament with all valid fields")
    @Test
    void shouldCreateTournamentWithValidData() {
        Tournament tournament = buildValidTournament("TechCup 2025-I");

        Tournament result = tournamentService.createTournament(tournament);

        assertNotNull(result.getId());
        assertEquals("TechCup 2025-I", result.getName());
        assertEquals(TournamentStatus.DRAFT.name(), result.getStatus());
    }

    @DisplayName("Should assign DRAFT as initial status when creating a tournament")
    @Test
    void shouldAssignDraftStatusOnCreate() {
        Tournament tournament = buildValidTournament("TechCup Draft");

        Tournament result = tournamentService.createTournament(tournament);

        assertEquals(TournamentStatus.DRAFT.name(), result.getStatus());
    }

    @DisplayName("Should auto-assign an ID when creating a tournament")
    @Test
    void shouldAutoAssignIdOnCreate() {
        Tournament t1 = buildValidTournament("Cup A");
        Tournament t2 = buildValidTournament("Cup B");

        Tournament r1 = tournamentService.createTournament(t1);
        Tournament r2 = tournamentService.createTournament(t2);

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
        Tournament t = buildValidTournament(null);
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
    }

    @DisplayName("Should throw exception when name is blank")
    @Test
    void shouldFailCreateWhenNameIsBlank() {
        Tournament t = buildValidTournament("   ");
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
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
        Tournament result = tournamentService.createTournament(t);
        assertNotNull(result.getId());
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
        assertEquals(created.getId(), found.getId());
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

        List<Tournament> result = tournamentService.getAllTournaments();

        assertEquals(3, result.size());
    }

    @DisplayName("Should return empty list when no tournaments exist")
    @Test
    void shouldReturnEmptyListWhenNoTournaments() {
        assertTrue(tournamentService.getAllTournaments().isEmpty());
    }

    @DisplayName("Should return a defensive copy in getAllTournaments")
    @Test
    void shouldReturnDefensiveCopyInGetAll() {
        tournamentService.createTournament(buildValidTournament("Cup A"));

        List<Tournament> list = tournamentService.getAllTournaments();
        list.clear();

        assertEquals(1, tournamentService.getAllTournaments().size());
    }


    @DisplayName("Should update tournament name successfully when in DRAFT")
    @Test
    void shouldUpdateTournamentName() {
        Tournament created = tournamentService.createTournament(buildValidTournament("Old Name"));

        Tournament update = new Tournament();
        update.setName("New Name");

        Tournament result = tournamentService.updateTournament(created.getId(), update);

        assertEquals("New Name", result.getName());
    }

    @DisplayName("Should update tournament dates successfully when in DRAFT")
    @Test
    void shouldUpdateTournamentDates() {
        Tournament created = tournamentService.createTournament(buildValidTournament("DateCup"));

        LocalDateTime newStart = LocalDateTime.now().plusDays(30);
        LocalDateTime newEnd   = LocalDateTime.now().plusDays(40);

        Tournament update = new Tournament();
        update.setStartDate(newStart);
        update.setEndDate(newEnd);

        Tournament result = tournamentService.updateTournament(created.getId(), update);

        assertEquals(newStart, result.getStartDate());
        assertEquals(newEnd, result.getEndDate());
    }

    @DisplayName("Should update maxOfTeams successfully when in DRAFT")
    @Test
    void shouldUpdateMaxOfTeams() {
        Tournament created = tournamentService.createTournament(buildValidTournament("TeamsCup"));

        Tournament update = new Tournament();
        update.setMaxOfTeams(16);

        Tournament result = tournamentService.updateTournament(created.getId(), update);

        assertEquals(16, result.getMaxOfTeams());
    }

    @DisplayName("Should update team cost successfully when in DRAFT")
    @Test
    void shouldUpdateTeamCost() {
        Tournament created = tournamentService.createTournament(buildValidTournament("CostCup"));

        Tournament update = new Tournament();
        update.setTeamCost(new BigDecimal("75000"));

        Tournament result = tournamentService.updateTournament(created.getId(), update);

        assertEquals(new BigDecimal("75000"), result.getTeamCost());
    }

    @DisplayName("Should keep the same ID after update")
    @Test
    void shouldKeepSameIdAfterUpdate() {
        Tournament created = tournamentService.createTournament(buildValidTournament("IdCup"));

        Tournament update = new Tournament();
        update.setName("IdCup Updated");

        Tournament result = tournamentService.updateTournament(created.getId(), update);

        assertEquals(created.getId(), result.getId());
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
        assertThrows(RuntimeException.class,
                () -> tournamentService.updateTournament(999L, update));
    }

    @DisplayName("Should throw exception when updating a tournament that is not in DRAFT")
    @Test
    void shouldFailUpdateWhenTournamentIsNotDraft() {
        Tournament created = tournamentService.createTournament(buildValidTournament("ActiveCup"));
        tournamentService.startTournament(created.getId());

        Tournament update = new Tournament();
        update.setName("ActiveCup Renamed");

        assertThrows(RuntimeException.class,
                () -> tournamentService.updateTournament(created.getId(), update));
    }

    @DisplayName("Should throw exception when updating with a blank name")
    @Test
    void shouldFailUpdateWithBlankName() {
        Tournament created = tournamentService.createTournament(buildValidTournament("BlankName"));

        Tournament update = new Tournament();
        update.setName("   ");

        assertThrows(RuntimeException.class,
                () -> tournamentService.updateTournament(created.getId(), update));
    }

    @DisplayName("Should throw exception when updating with a duplicate name")
    @Test
    void shouldFailUpdateWithDuplicateName() {
        tournamentService.createTournament(buildValidTournament("Cup Alpha"));
        Tournament cup2 = tournamentService.createTournament(buildValidTournament("Cup Beta"));

        Tournament update = new Tournament();
        update.setName("Cup Alpha");

        assertThrows(RuntimeException.class,
                () -> tournamentService.updateTournament(cup2.getId(), update));
    }

    @DisplayName("Should throw exception when updating end date before the current start date")
    @Test
    void shouldFailUpdateWhenNewEndDateBeforeStartDate() {
        Tournament created = tournamentService.createTournament(buildValidTournament("DateOrderCup"));

        Tournament update = new Tournament();
        update.setEndDate(LocalDateTime.now().plusDays(1)); 

        assertThrows(RuntimeException.class,
                () -> tournamentService.updateTournament(created.getId(), update));
    }

    @DisplayName("Should throw exception when updating maxOfTeams to less than 2")
    @Test
    void shouldFailUpdateWithMaxTeamsLessThanTwo() {
        Tournament created = tournamentService.createTournament(buildValidTournament("SmallCup"));

        Tournament update = new Tournament();
        update.setMaxOfTeams(1);

        assertThrows(RuntimeException.class,
                () -> tournamentService.updateTournament(created.getId(), update));
    }

    @DisplayName("Should throw exception when updating team cost to a negative value")
    @Test
    void shouldFailUpdateWithNegativeTeamCost() {
        Tournament created = tournamentService.createTournament(buildValidTournament("CostError"));

        Tournament update = new Tournament();
        update.setTeamCost(new BigDecimal("-100"));

        assertThrows(RuntimeException.class,
                () -> tournamentService.updateTournament(created.getId(), update));
    }


    @DisplayName("Should cancel a tournament in DRAFT status")
    @Test
    void shouldCancelDraftTournament() {
        Tournament created = tournamentService.createTournament(buildValidTournament("CancelMe"));

        tournamentService.cancelTournament(created.getId());

        assertThrows(RuntimeException.class,
                () -> tournamentService.getTournament(created.getId()));
    }

    @DisplayName("Should cancel a tournament in ACTIVE status")
    @Test
    void shouldCancelActiveTournament() {
        Tournament created = tournamentService.createTournament(buildValidTournament("ActiveCancel"));
        tournamentService.startTournament(created.getId());

        tournamentService.cancelTournament(created.getId());

        assertThrows(RuntimeException.class,
                () -> tournamentService.getTournament(created.getId()));
    }

    @DisplayName("Should throw exception when cancelling a tournament that is IN_PROGRESS")
    @Test
    void shouldFailCancelWhenTournamentIsInProgress() {
        Tournament created = tournamentService.createTournament(buildValidTournament("InProgressCup"));
        created.setStatus(TournamentStatus.IN_PROGRESS.name());

        assertThrows(RuntimeException.class,
                () -> tournamentService.cancelTournament(created.getId()));
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

    // ═══════════════════════════════════════════════════════════════════════════
    // LIFECYCLE: startTournament / finishTournament
    // ═══════════════════════════════════════════════════════════════════════════

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

        assertThrows(RuntimeException.class,
                () -> tournamentService.startTournament(created.getId()));
    }

    @DisplayName("Should throw exception when finishing a tournament that is not IN_PROGRESS")
    @Test
    void shouldFailFinishWhenTournamentIsNotInProgress() {
        Tournament created = tournamentService.createTournament(buildValidTournament("NotInProgress"));

        assertThrows(RuntimeException.class,
                () -> tournamentService.finishTournament(created.getId()));
    }

    @DisplayName("Should throw exception when starting a non-existing tournament")
    @Test
    void shouldFailStartWhenTournamentNotFound() {
        assertThrows(RuntimeException.class, () -> tournamentService.startTournament(999L));
    }

    @DisplayName("Should throw exception when finishing a non-existing tournament")
    @Test
    void shouldFailFinishWhenTournamentNotFound() {
        assertThrows(RuntimeException.class, () -> tournamentService.finishTournament(999L));
    }

    @DisplayName("An active or in-progress tournament should be considered active")
    @Test
    void shouldBeActiveWhenActiveOrInProgress() {
        Tournament created = tournamentService.createTournament(buildValidTournament("IsActiveCup"));
        tournamentService.startTournament(created.getId());

        assertTrue(created.isActive());
    }

    @DisplayName("A DRAFT tournament should not be considered active")
    @Test
    void shouldNotBeActiveWhenDraft() {
        Tournament created = tournamentService.createTournament(buildValidTournament("DraftCup"));

        assertFalse(created.isActive());
    }
}