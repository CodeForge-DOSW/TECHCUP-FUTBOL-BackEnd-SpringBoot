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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TournamentServiceTest {

    @Mock
    private TournamentRepository tournamentRepository;

    @Mock
    private TournamentMapper tournamentMapper;

    @InjectMocks
    private TournamentService tournamentService;

    // LocalDate reemplaza LocalDateTime — TournamentModel y TournamentEntity usan LocalDate
    private static final LocalDate FUTURE_START = LocalDate.now().plusDays(10);
    private static final LocalDate FUTURE_END   = LocalDate.now().plusDays(20);

    // ─── Helpers ────────────────────────────────────────────────────────────

    /**
     * Construye un TournamentModel con todos los campos válidos.
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
     * Construye una TournamentEntity con id y status para usar como
     * respuesta simulada del repositorio.
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
     * Construye un TournamentModel con id y status para usar como
     * respuesta simulada del mapper.
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

    // ─── CREATE ─────────────────────────────────────────────────────────────

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

    @DisplayName("Should throw exception when creating a null tournament")
    @Test
    void shouldFailCreateWhenTournamentIsNull() {
        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(null));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    @DisplayName("Should throw exception when name is null")
    @Test
    void shouldFailCreateWhenNameIsNull() {
        assertThrows(RuntimeException.class,
                () -> tournamentService.createTournament(buildValidTournament(null)));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    @DisplayName("Should throw exception when name is blank")
    @Test
    void shouldFailCreateWhenNameIsBlank() {
        assertThrows(RuntimeException.class,
                () -> tournamentService.createTournament(buildValidTournament("   ")));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    @DisplayName("Should throw exception when start date is null")
    @Test
    void shouldFailCreateWhenStartDateIsNull() {
        TournamentModel t = buildValidTournament("NullStart");
        t.setStartDate(null);

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    @DisplayName("Should throw exception when end date is null")
    @Test
    void shouldFailCreateWhenEndDateIsNull() {
        TournamentModel t = buildValidTournament("NullEnd");
        t.setEndDate(null);

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    @DisplayName("Should throw exception when end date is before start date")
    @Test
    void shouldFailCreateWhenEndDateBeforeStartDate() {
        TournamentModel t = buildValidTournament("BadDates");
        t.setStartDate(LocalDate.now().plusDays(20));
        t.setEndDate(LocalDate.now().plusDays(5));

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    @DisplayName("Should throw exception when maxOfTeams is less than 2")
    @Test
    void shouldFailCreateWhenMaxTeamsLessThanTwo() {
        TournamentModel t = buildValidTournament("FewTeams");
        t.setMaxOfTeams(1);

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    @DisplayName("Should throw exception when maxOfTeams is null")
    @Test
    void shouldFailCreateWhenMaxTeamsIsNull() {
        TournamentModel t = buildValidTournament("NullTeams");
        t.setMaxOfTeams(null);

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

    @DisplayName("Should throw exception when team cost is negative")
    @Test
    void shouldFailCreateWhenTeamCostIsNegative() {
        TournamentModel t = buildValidTournament("NegCost");
        t.setTeamCost(new BigDecimal("-1"));

        assertThrows(RuntimeException.class, () -> tournamentService.createTournament(t));
        verifyNoInteractions(tournamentRepository, tournamentMapper);
    }

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

    @DisplayName("Should throw exception when a tournament with the same name already exists")
    @Test
    void shouldFailCreateWhenNameAlreadyExists() {
        when(tournamentRepository.existsByNameIgnoreCase("TechCup 2025-I")).thenReturn(true);

        assertThrows(RuntimeException.class,
                () -> tournamentService.createTournament(buildValidTournament("TechCup 2025-I")));

        verify(tournamentRepository, never()).save(any());
    }

    // ─── LIFECYCLE ──────────────────────────────────────────────────────────

    @DisplayName("Should move tournament from DRAFT to ACTIVE when starting")
    @Test
    void shouldStartTournamentFromDraft() {
        TournamentEntity entity = entityWith(1L, "StartMe", "draft");

        when(tournamentRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(tournamentRepository.save(entity)).thenReturn(entity);

        tournamentService.startTournament(1L);

        assertEquals("active", entity.getStatus());
    }

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
}