package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.TournamentModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import edu.eci.dosw.tech_cup.service.ITournamentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller that exposes CRUD and state-transition operations for tournaments.
 *
 * <p>This controller manages tournament lifecycle including creation, updates, cancellation,
 * and state transitions (start/finish). All business logic is delegated to {@link ITournamentService}.</p>
 */
@RestController
@RequestMapping("/api/tournaments")
@Tag(name = "Tournaments", description = "Endpoints for tournament management operations")
public class TournamentController {

    /**
     * Service that executes tournament-related use cases.
     */
    private final ITournamentService tournamentService;

    /**
     * Builds the controller with its service dependency.
     *
     * @param tournamentService injected tournament service implementation
     */
    public TournamentController(ITournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    /**
     * Creates a new tournament.
     *
     * @param tournament request payload with tournament data
     * @return 201 with the created tournament; 400 with an error message when validation fails
     */
    @PostMapping
    @Operation(summary = "Create tournament", description = "Registers a new tournament in the system")
    public ResponseEntity<?> createTournament(@RequestBody Tournament tournament) {
        try {
            TournamentModel created = tournamentService.createTournament(tournament);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Retrieves all tournaments.
     *
     * @return 200 with the complete tournament list
     */
    @GetMapping
    @Operation(summary = "List tournaments", description = "Retrieves all registered tournaments")
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        return ResponseEntity.ok(tournamentService.getAllTournaments());
    }

    /**
     * Retrieves one tournament by its id.
     *
     * @param id unique tournament identifier
     * @return 200 with the tournament when found; 404 with an error message when not found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get tournament by ID", description = "Retrieves a tournament by identifier")
    public ResponseEntity<?> getTournament(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tournamentService.getTournament(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Updates an existing tournament.
     *
     * @param id unique tournament identifier
     * @param tournament payload containing updated tournament data
     * @return 200 with the updated tournament; 400 with an error message when the update is invalid
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update tournament", description = "Updates information for an existing tournament")
    public ResponseEntity<?> updateTournament(@PathVariable Long id,
                                              @RequestBody TournamentModel tournament) {
        try {
            TournamentModel updated = tournamentService.updateTournament(id, tournament);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Cancels a tournament.
     *
     * @param id unique tournament identifier
     * @return 204 No Content when cancellation succeeds; 400 with an error message when it fails
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel tournament", description = "Cancels an existing tournament")
    public ResponseEntity<?> cancelTournament(@PathVariable Long id) {
        try {
            tournamentService.cancelTournament(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Transitions a tournament to the started state.
     *
     * @param id unique tournament identifier
     * @return 200 with the updated tournament when successful; 400 with an error message when it fails
     */
    @PutMapping("/{id}/start")
    @Operation(summary = "Start tournament", description = "Transitions the tournament to the started state")
    public ResponseEntity<?> startTournament(@PathVariable Long id) {
        try {
            tournamentService.startTournament(id);
            return ResponseEntity.ok("Tournament started");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Transitions a tournament to the finished state.
     *
     * @param id unique tournament identifier
     * @return 200 with the updated tournament when successful; 400 with an error message when it fails
     */
    @PutMapping("/{id}/finish")
    @Operation(summary = "Finish tournament", description = "Transitions the tournament to the finished state")
    public ResponseEntity<?> finishTournament(@PathVariable Long id) {
        try {
            tournamentService.finishTournament(id);
            return ResponseEntity.ok("Tournament finished");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}