package edu.eci.dosw.tech_cup.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.service.ITournamentService;
import edu.eci.dosw.tech_cup.service.TournamentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
@Tag(name = "Tournaments", description = "Endpoints for tournament management operations")
public class TournamentController {

    private final ITournamentService tournamentService = new TournamentService();

    @PostMapping
    @Operation(summary = "Create tournament", description = "Registers a new tournament in the system")
    public ResponseEntity<?> createTournament(@RequestBody Tournament tournament) {
        try {
            Tournament created = tournamentService.createTournament(tournament);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping
    @Operation(summary = "List tournaments", description = "Retrieves all registered tournaments")
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        return ResponseEntity.ok(tournamentService.getAllTournaments());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get tournament by ID", description = "Retrieves a tournament by identifier")
    public ResponseEntity<?> getTournament(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(tournamentService.getTournament(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update tournament", description = "Updates information for an existing tournament")
    public ResponseEntity<?> updateTournament(@PathVariable Long id,
                                              @RequestBody Tournament tournament) {
        try {
            Tournament updated = tournamentService.updateTournament(id, tournament);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel tournament", description = "Cancels an existing tournament")
    public ResponseEntity<?> cancelTournament(@PathVariable Long id) {
        try {
            tournamentService.cancelTournament(id);
            return ResponseEntity.ok("Tournament cancelled");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

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