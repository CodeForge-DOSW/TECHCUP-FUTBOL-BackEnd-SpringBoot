package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.entity.TournamentEntity;
import edu.eci.dosw.tech_cup.mapper.TournamentMapper;
import edu.eci.dosw.tech_cup.model.TournamentModel;
import edu.eci.dosw.tech_cup.model.TournamentStatusModel;
import edu.eci.dosw.tech_cup.repository.TournamentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link TournamentService}.
 *
 * <p>This test suite validates tournament creation rules, lifecycle transitions,
 * and retrieval behavior by using mocked repository and mapper dependencies.</p>
 */
@ExtendWith(MockitoExtension.class)
public class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private TournamentMapper tournamentMapper;

    @InjectMocks
    private TournamentService tournamentService;

    /**
     * Shared future start date used by valid tournament fixtures.
     */
    private static final LocalDate FUTURE_START = LocalDate.now().plusDays(10);

    /**
     * Shared future end date used by valid tournament fixtures.
     */
    private static final LocalDate FUTURE_END   = LocalDate.now().plusDays(20);


    /**
     * Builds a tournament model with valid default data.
     *
     * @param name tournament name to assign
     * @return a valid tournament model ready for service operations
     */
    private TournamentModel buildValidTournament(String name) {
        TournamentModel t = new TournamentModel();
        t.setName(name);
        t.setStartDate(FUTURE_START);
        t.setEndDate(FUTURE_END);
        t.setMaxOfTeams(8);
        t.setTeamCost(new BigDecimal("50000"));
        return t;
    }

    /**
     * Builds a tournament entity for mocked repository responses.
     *
     * @param id persisted tournament identifier
     * @param name tournament name
     * @param status stored lifecycle status
     * @return a tournament entity configured for tests
     */
    private TournamentEntity entityWith(Long id, String name, String status) {
        TournamentEntity e = new TournamentEntity();
        e.setTournamentId(id);
        e.setName(name);
        e.setStartDate(FUTURE_START);
        e.setEndDate(FUTURE_END);
        e.setNumberOfTeams(8);
        e.setTeamCost(new BigDecimal("50000"));
        e.setStatus(status);
        return e;
    }

    /**
     * Builds a tournament model for mocked mapper responses.
     *
     * @param id tournament identifier
     * @param name tournament name
     * @param status lifecycle status enum
     * @return a tournament model configured for assertions
     */
    private TournamentModel modelWith(Long id, String name, TournamentStatusModel status) {
        TournamentModel m = new TournamentModel();
        m.setId(id);
        m.setName(name);
        m.setStartDate(FUTURE_START);
        m.setEndDate(FUTURE_END);
        m.setMaxOfTeams(8);
        m.setTeamCost(new BigDecimal("50000"));
        m.setStatus(status);
        return m;
    }

    /**
     * Verifies that a tournament is created successfully when all required fields are valid.
     */
    @DisplayName("Should create a tournament with all valid fields")
    @Test
    void shouldCreateTournamentWithValidData() {
        TournamentModel input = buildValidTournament("TechCup 2025-I");
        TournamentEntity savedEntity = entityWith(1L, "TechCup 2025-I", "draft");
        TournamentModel expectedModel = modelWith(1L, "TechCup 2025-I", TournamentStatusModel.DRAFT);

        when(tournamentRepository.existsByNameIgnoreCase("TechCup 2025-I")).thenReturn(false);
        when(tournamentMapper.toEntity(any())).thenReturn(savedEntity);
        when(tournamentRepository.save(savedEntity)).thenReturn(savedEntity);
        when(tournamentMapper.toModel(savedEntity)).thenReturn(expectedModel);

        TournamentModel result = tournamentService.createTournament(input);

        assertNotNull(result.getId());
        assertEquals("TechCup 2025-I", result.getName());
        assertEquals(TournamentStatusModel.DRAFT, result.getStatus());
    }

    /**
     * Verifies that newly created tournaments always start in DRAFT status.
     */
    @DisplayName("Should assign DRAFT as initial status when creating a tournament")
    @Test
    void shouldAssignDraftStatusOnCreate() {
        TournamentModel input = buildValidTournament("TechCup Draft");
        TournamentEntity savedEntity = entityWith(1L, "TechCup Draft", "draft");
        TournamentModel expectedModel = modelWith(1L, "TechCup Draft", TournamentStatusModel.DRAFT);

        when(tournamentRepository.existsByNameIgnoreCase("TechCup Draft")).thenReturn(false);
        when(tournamentMapper.toEntity(any())).thenReturn(savedEntity);
        when(tournamentRepository.save(savedEntity)).thenReturn(savedEntity);
        when(tournamentMapper.toModel(savedEntity)).thenReturn(expectedModel);

        TournamentModel result = tournamentService.createTournament(input);

        assertEquals(TournamentStatusModel.DRAFT, result.getStatus());
    }

    /**
     * Verifies that separate tournament creations produce distinct identifiers.
     */
    @DisplayName("Should auto-assign different IDs to each tournament")
    @Test
    void shouldAutoAssignIdOnCreate() {
        TournamentEntity entityA = entityWith(1L, "Cup A", "draft");
        TournamentEntity entityB = entityWith(2L, "Cup B", "draft");
        TournamentModel modelA = modelWith(1L, "Cup A", TournamentStatusModel.DRAFT);
        TournamentModel modelB = modelWith(2L, "Cup B", TournamentStatusModel.DRAFT);

        when(tournamentRepository.existsByNameIgnoreCase("Cup A")).thenReturn(false);
        when(tournamentRepository.existsByNameIgnoreCase("Cup B")).thenReturn(false);
        when(tournamentMapper.toEntity(any()))
                .thenReturn(entityA)
                .thenReturn(entityB);
        when(tournamentRepository.save(entityA)).thenReturn(entityA);
        when(tournamentRepository.save(entityB)).thenReturn(entityB);
        when(tournamentMapper.toModel(entityA)).thenReturn(modelA);
        when(tournamentMapper.toModel(entityB)).thenReturn(modelB);

        TournamentModel r1 = tournamentService.createTournament(buildValidTournament("Cup A"));
        TournamentModel r2 = tournamentService.createTournament(buildValidTournament("Cup B"));

        assertNotEquals(r1.getId(), r2.getId());
    }

    /**
     * Verifies that creation fails when the tournament payload is null.
     */
    @DisplayName("Should throw exception when creating a null tournament")
    @Test
    void shouldFailCreateWhenTournamentIsNull() {
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(null));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    /**
     * Verifies that creation fails when the tournament name is null.
     */
    @DisplayName("Should throw exception when name is null")
    @Test
    void shouldFailCreateWhenNameIsNull() {
        assertThrows(RuntimeException.class,
                () -> tournamentService.createTournament(buildValidTournament(null)));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    /**
     * Verifies that creation fails when the tournament name is blank.
     */
    @DisplayName("Should throw exception when name is blank")
    @Test
    void shouldFailCreateWhenNameIsBlank() {
        assertThrows(RuntimeException.class,
                () -> tournamentService.createTournament(buildValidTournament("   ")));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    /**
     * Verifies that creation fails when the start date is missing.
     */
    @DisplayName("Should throw exception when start date is null")
    @Test
    void shouldFailCreateWhenStartDateIsNull() {
        TournamentModel t = buildValidTournament("NullStart");
        t.setStartDate(null);

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    /**
     * Verifies that creation fails when the end date is missing.
     */
    @DisplayName("Should throw exception when end date is null")
    @Test
    void shouldFailCreateWhenEndDateIsNull() {
        TournamentModel t = buildValidTournament("NullEnd");
        t.setEndDate(null);

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    /**
     * Verifies that creation fails when the end date is before the start date.
     */
    @DisplayName("Should throw exception when end date is before start date")
    @Test
    void shouldFailCreateWhenEndDateBeforeStartDate() {
        TournamentModel t = buildValidTournament("BadDates");
        t.setStartDate(LocalDate.now().plusDays(20));
        t.setEndDate(LocalDate.now().plusDays(5));

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    /**
     * Verifies that creation fails when the maximum team count is less than two.
     */
    @DisplayName("Should throw exception when maxOfTeams is less than 2")
    @Test
    void shouldFailCreateWhenMaxTeamsLessThanTwo() {
        TournamentModel t = buildValidTournament("FewTeams");
        t.setMaxOfTeams(1);

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    /**
     * Verifies that creation fails when the maximum team count is not provided.
     */
    @DisplayName("Should throw exception when maxOfTeams is null")
    @Test
    void shouldFailCreateWhenMaxTeamsIsNull() {
        TournamentModel t = buildValidTournament("NullTeams");
        t.setMaxOfTeams(null);

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    /**
     * Verifies that creation fails when the team registration cost is negative.
     */
    @DisplayName("Should throw exception when team cost is negative")
    @Test
    void shouldFailCreateWhenTeamCostIsNegative() {
        TournamentModel t = buildValidTournament("NegCost");
        t.setTeamCost(new BigDecimal("-1"));

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    /**
     * Verifies that creation allows a zero registration cost.
     */
    @DisplayName("Should allow creating a tournament with zero team cost")
    @Test
    void shouldAllowZeroTeamCost() {
        TournamentModel input = buildValidTournament("FreeCup");
        input.setTeamCost(BigDecimal.ZERO);

        TournamentEntity savedEntity = entityWith(1L, "FreeCup", "draft");
        TournamentModel expectedModel = modelWith(1L, "FreeCup", TournamentStatusModel.DRAFT);

        when(tournamentRepository.existsByNameIgnoreCase("FreeCup")).thenReturn(false);
        when(tournamentMapper.toEntity(any())).thenReturn(savedEntity);
        when(tournamentRepository.save(savedEntity)).thenReturn(savedEntity);
        when(tournamentMapper.toModel(savedEntity)).thenReturn(expectedModel);

        assertNotNull(tournamentService.createTournament(input).getId());
    }

    /**
     * Verifies that creation fails when another tournament already uses the same name.
     */
    @DisplayName("Should throw exception when a tournament with the same name already exists")
    @Test
    void shouldFailCreateWhenNameAlreadyExists() {
        when(tournamentRepository.existsByNameIgnoreCase("TechCup 2025-I")).thenReturn(true);

        assertThrows(RuntimeException.class,
                () -> tournamentService.createTournament(buildValidTournament("TechCup 2025-I")));

        verify(tournamentRepository, never()).save(any());
    }

    /**
     * Verifies that starting a draft tournament moves it to ACTIVE.
     */
    @DisplayName("Should move tournament from DRAFT to ACTIVE when starting")
    @Test
    void shouldStartTournamentFromDraft() {
        TournamentEntity entity = entityWith(1L, "StartMe", "draft");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(tournamentRepository.save(entity)).thenReturn(entity);

        tournamentService.startTournament(1L);

        assertEquals("active", entity.getStatus());
    }

    /**
     * Verifies that starting an active tournament advances it to IN_PROGRESS.
     */
    @DisplayName("Should move tournament from ACTIVE to IN_PROGRESS when starting again")
    @Test
    void shouldMoveFromActiveToInProgress() {
        TournamentEntity entity = entityWith(1L, "StartMe", "draft");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(tournamentRepository.save(entity)).thenReturn(entity);

        tournamentService.startTournament(1L); // draft → active
        tournamentService.startTournament(1L); // active → in_progress

        assertEquals("in_progress", entity.getStatus());
    }

    /**
     * Verifies that a tournament can be finished only after reaching IN_PROGRESS.
     */
    @DisplayName("Should finish tournament from IN_PROGRESS")
    @Test
    void shouldFinishTournamentFromInProgress() {
        TournamentEntity entity = entityWith(1L, "FinishMe", "draft");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(tournamentRepository.save(entity)).thenReturn(entity);

        tournamentService.startTournament(1L);  // draft → active
        tournamentService.startTournament(1L);  // active → in_progress
        tournamentService.finishTournament(1L); // in_progress → finished

        assertEquals("finished", entity.getStatus());
    }

    /**
     * Verifies that a tournament in DRAFT status is not considered running.
     */
    @DisplayName("Should not consider DRAFT as active")
    @Test
    void shouldNotBeActiveWhenDraft() {
        TournamentEntity entity = entityWith(1L, "DraftCup", "draft");
        TournamentModel draftModel = modelWith(1L, "DraftCup", TournamentStatusModel.DRAFT);

        when(tournamentRepository.existsByNameIgnoreCase("DraftCup")).thenReturn(false);
        when(tournamentMapper.toEntity(any())).thenReturn(entity);
        when(tournamentRepository.save(entity)).thenReturn(entity);
        when(tournamentMapper.toModel(entity)).thenReturn(draftModel);

        TournamentModel created = tournamentService.createTournament(buildValidTournament("DraftCup"));

        assertFalse(created.isRunning());
    }

    /**
     * Verifies that a tournament in ACTIVE status is considered running.
     */
    @DisplayName("Should be active when tournament is ACTIVE")
    @Test
    void shouldBeActiveWhenActive() {
        TournamentEntity entity = entityWith(1L, "ActiveCup", "draft");
        TournamentModel activeModel = modelWith(1L, "ActiveCup", TournamentStatusModel.ACTIVE);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(tournamentRepository.save(entity)).thenReturn(entity);
        when(tournamentMapper.toModel(entity)).thenReturn(activeModel);

        tournamentService.startTournament(1L);

        TournamentModel result = tournamentService.getTournament(1L);
        assertTrue(result.isRunning());
    }

    /**
     * Verifies that a tournament in IN_PROGRESS status is considered running.
     */
    @DisplayName("Should be active when tournament is IN_PROGRESS")
    @Test
    void shouldBeActiveWhenInProgress() {
        TournamentEntity entity = entityWith(1L, "ProgressCup", "draft");
        TournamentModel inProgressModel = modelWith(1L, "ProgressCup", TournamentStatusModel.IN_PROGRESS);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(tournamentRepository.save(entity)).thenReturn(entity);
        when(tournamentMapper.toModel(entity)).thenReturn(inProgressModel);

        tournamentService.startTournament(1L);
        tournamentService.startTournament(1L);

        TournamentModel result = tournamentService.getTournament(1L);
        assertTrue(result.isRunning());
    }

    // =========================================================
    // getTournament
    // =========================================================

    @DisplayName("Should return tournament by id")
    @Test
    void shouldGetTournamentById() {
        TournamentEntity entity = entityWith(1L, "TechCup", "draft");
        TournamentModel model = modelWith(1L, "TechCup", TournamentStatusModel.DRAFT);

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(tournamentMapper.toModel(entity)).thenReturn(model);

        TournamentModel result = tournamentService.getTournament(1L);

        assertEquals("TechCup", result.getName());
        assertEquals(TournamentStatusModel.DRAFT, result.getStatus());
    }

    @DisplayName("Should throw exception when tournament not found by id")
    @Test
    void shouldFailGetTournamentNotFound() {
        when(tournamentRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> tournamentService.getTournament(999L));
    }

    // =========================================================
    // getAllTournaments
    // =========================================================

    @DisplayName("Should return all tournaments")
    @Test
    void shouldGetAllTournaments() {
        TournamentEntity e1 = entityWith(1L, "Cup A", "draft");
        TournamentEntity e2 = entityWith(2L, "Cup B", "active");
        TournamentModel m1 = modelWith(1L, "Cup A", TournamentStatusModel.DRAFT);
        TournamentModel m2 = modelWith(2L, "Cup B", TournamentStatusModel.ACTIVE);

        when(tournamentRepository.findAll()).thenReturn(List.of(e1, e2));
        when(tournamentMapper.toModel(e1)).thenReturn(m1);
        when(tournamentMapper.toModel(e2)).thenReturn(m2);

        List<TournamentModel> result = tournamentService.getAllTournaments();

        assertEquals(2, result.size());
    }

    @DisplayName("Should return empty list when no tournaments exist")
    @Test
    void shouldReturnEmptyListWhenNoTournaments() {
        when(tournamentRepository.findAll()).thenReturn(List.of());

        assertTrue(tournamentService.getAllTournaments().isEmpty());
    }

    // =========================================================
    // updateTournament
    // =========================================================

    @DisplayName("Should update tournament name successfully")
    @Test
    void shouldUpdateTournamentName() {
        TournamentEntity existing = entityWith(1L, "Old Name", "draft");
        TournamentEntity saved   = entityWith(1L, "New Name", "draft");
        TournamentModel result   = modelWith(1L, "New Name", TournamentStatusModel.DRAFT);

        TournamentModel payload = new TournamentModel();
        payload.setName("New Name");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(tournamentRepository.findByNameIgnoreCase("New Name")).thenReturn(Optional.empty());
        when(tournamentRepository.save(existing)).thenReturn(saved);
        when(tournamentMapper.toModel(saved)).thenReturn(result);

        TournamentModel updated = tournamentService.updateTournament(1L, payload);

        assertEquals("New Name", updated.getName());
    }

    @DisplayName("Should update tournament cost and dates")
    @Test
    void shouldUpdateTournamentCostAndDates() {
        TournamentEntity existing = entityWith(1L, "Cup", "active");
        TournamentEntity saved    = entityWith(1L, "Cup", "active");
        TournamentModel result    = modelWith(1L, "Cup", TournamentStatusModel.ACTIVE);

        TournamentModel payload = new TournamentModel();
        payload.setEndDate(FUTURE_END.plusDays(5));
        payload.setTeamCost(new BigDecimal("75000"));

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(tournamentRepository.save(existing)).thenReturn(saved);
        when(tournamentMapper.toModel(saved)).thenReturn(result);

        assertNotNull(tournamentService.updateTournament(1L, payload));
    }

    @DisplayName("Should throw exception when updating with null payload")
    @Test
    void shouldFailUpdateWhenPayloadIsNull() {
        assertThrows(RuntimeException.class, () -> tournamentService.updateTournament(1L, null));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    @DisplayName("Should throw exception when updating a non-existing tournament")
    @Test
    void shouldFailUpdateWhenTournamentNotFound() {
        TournamentModel payload = new TournamentModel();
        payload.setName("Anything");

        when(tournamentRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> tournamentService.updateTournament(999L, payload));
    }

    @DisplayName("Should throw exception when updating a FINISHED tournament")
    @Test
    void shouldFailUpdateFinishedTournament() {
        TournamentEntity finished = entityWith(1L, "Done", "finished");
        TournamentModel payload   = new TournamentModel();
        payload.setName("New Name");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(finished));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> tournamentService.updateTournament(1L, payload));
        assertTrue(ex.getMessage().contains("FINISHED"));
    }

    @DisplayName("Should throw exception when new name is already used by another tournament")
    @Test
    void shouldFailUpdateWhenNameTakenByOther() {
        TournamentEntity existing = entityWith(1L, "My Cup", "draft");
        TournamentEntity other    = entityWith(2L, "Taken Name", "draft");

        TournamentModel payload = new TournamentModel();
        payload.setName("Taken Name");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(tournamentRepository.findByNameIgnoreCase("Taken Name"))
                .thenReturn(Optional.of(other));

        assertThrows(RuntimeException.class, () -> tournamentService.updateTournament(1L, payload));
    }

    @DisplayName("Should throw exception when updated end date is before current start date")
    @Test
    void shouldFailUpdateWhenEndDateBeforeStartDate() {
        TournamentEntity existing = entityWith(1L, "Cup", "draft");

        TournamentModel payload = new TournamentModel();
        payload.setEndDate(LocalDate.now().minusDays(5));

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(existing));

        assertThrows(RuntimeException.class, () -> tournamentService.updateTournament(1L, payload));
    }

    @DisplayName("Should throw exception when updated team cost is negative")
    @Test
    void shouldFailUpdateWhenNegativeTeamCost() {
        TournamentEntity existing = entityWith(1L, "Cup", "draft");

        TournamentModel payload = new TournamentModel();
        payload.setTeamCost(new BigDecimal("-500"));

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(existing));

        assertThrows(RuntimeException.class, () -> tournamentService.updateTournament(1L, payload));
    }

    @DisplayName("Should allow updating name to the same name of the same tournament")
    @Test
    void shouldAllowUpdateWithSameNameForSameTournament() {
        TournamentEntity existing = entityWith(1L, "My Cup", "draft");
        TournamentEntity saved    = entityWith(1L, "My Cup", "draft");
        TournamentModel result    = modelWith(1L, "My Cup", TournamentStatusModel.DRAFT);

        TournamentModel payload = new TournamentModel();
        payload.setName("My Cup");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(existing));
        // findByNameIgnoreCase returns the same tournament (id = 1), so no conflict
        when(tournamentRepository.findByNameIgnoreCase("My Cup"))
                .thenReturn(Optional.of(existing));
        when(tournamentRepository.save(existing)).thenReturn(saved);
        when(tournamentMapper.toModel(saved)).thenReturn(result);

        assertNotNull(tournamentService.updateTournament(1L, payload));
    }

    // =========================================================
    // cancelTournament
    // =========================================================

    @DisplayName("Should cancel a DRAFT tournament successfully")
    @Test
    void shouldCancelDraftTournament() {
        TournamentEntity entity = entityWith(1L, "ToBeCancelled", "draft");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(entity));

        tournamentService.cancelTournament(1L);

        verify(tournamentRepository).delete(entity);
    }

    @DisplayName("Should throw exception when cancelling a non-DRAFT tournament")
    @Test
    void shouldFailCancelNonDraftTournament() {
        TournamentEntity active = entityWith(1L, "Running", "active");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(active));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> tournamentService.cancelTournament(1L));
        assertTrue(ex.getMessage().contains("DRAFT"));
        verify(tournamentRepository, never()).delete(any());
    }

    @DisplayName("Should throw exception when cancelling a non-existing tournament")
    @Test
    void shouldFailCancelTournamentNotFound() {
        when(tournamentRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> tournamentService.cancelTournament(999L));
    }

    // =========================================================
    // startTournament – error paths
    // =========================================================

    @DisplayName("Should throw exception when starting a non-existing tournament")
    @Test
    void shouldFailStartTournamentNotFound() {
        when(tournamentRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> tournamentService.startTournament(999L));
    }

    @DisplayName("Should throw exception when trying to start a FINISHED tournament")
    @Test
    void shouldFailStartFromFinishedStatus() {
        TournamentEntity finished = entityWith(1L, "Done", "finished");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(finished));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> tournamentService.startTournament(1L));
        assertTrue(ex.getMessage().contains("cannot be started"));
    }

    // =========================================================
    // finishTournament – error paths
    // =========================================================

    @DisplayName("Should throw exception when finishing a non-existing tournament")
    @Test
    void shouldFailFinishTournamentNotFound() {
        when(tournamentRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> tournamentService.finishTournament(999L));
    }

    @DisplayName("Should throw exception when finishing a DRAFT tournament")
    @Test
    void shouldFailFinishFromDraftStatus() {
        TournamentEntity draft = entityWith(1L, "NotStarted", "draft");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(draft));

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> tournamentService.finishTournament(1L));
        assertTrue(ex.getMessage().contains("IN_PROGRESS"));
    }

    @DisplayName("Should throw exception when finishing an ACTIVE tournament")
    @Test
    void shouldFailFinishFromActiveStatus() {
        TournamentEntity active = entityWith(1L, "ActiveCup", "active");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(active));

        assertThrows(RuntimeException.class, () -> tournamentService.finishTournament(1L));
    }
}