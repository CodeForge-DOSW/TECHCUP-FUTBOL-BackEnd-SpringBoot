package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.TeamResponseModel;
import edu.eci.dosw.tech_cup.service.ITeamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller that exposes CRUD operations for teams.
 */
@RestController
@RequestMapping("/api/teams")
@Tag(name = "Teams", description = "Endpoints for team management operations")
public class TeamController {

    private final ITeamService teamService;

    public TeamController(ITeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * Creates a new team.
     *
     * @param team request payload with team data (tournamentId, captainId, name, etc.)
     * @return 201 with the created team; 400 when validation fails
     */
    @PostMapping
    @Operation(summary = "Create team", description = "Registers a new team. Send tournamentId and captainId as IDs.")
    public ResponseEntity<?> createTeam(@RequestBody TeamResponseModel team) {
        try {
            TeamResponseModel created = teamService.createTeam(team);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Retrieves all teams, optionally filtered by tournament.
     *
     * @param tournamentId optional filter
     * @return 200 with the team list
     */
    @GetMapping
    @Operation(summary = "List teams", description = "Retrieves all teams. Use ?tournamentId= to filter by tournament.")
    public ResponseEntity<List<TeamResponseModel>> getAllTeams(
            @RequestParam(required = false) Long tournamentId) {
        if (tournamentId != null) {
            return ResponseEntity.ok(teamService.getTeamsByTournament(tournamentId));
        }
        return ResponseEntity.ok(teamService.getAllTeams());
    }

    /**
     * Retrieves one team by its id.
     *
     * @param id unique team identifier
     * @return 200 with the team; 404 when not found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get team by ID", description = "Retrieves a team by identifier")
    public ResponseEntity<?> getTeam(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(teamService.getTeam(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    /**
     * Updates an existing team.
     *
     * @param id   unique team identifier
     * @param team payload with updated data
     * @return 200 with the updated team; 400 when invalid
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update team", description = "Updates information for an existing team")
    public ResponseEntity<?> updateTeam(@PathVariable Long id,
                                        @RequestBody TeamResponseModel team) {
        try {
            TeamResponseModel updated = teamService.updateTeam(id, team);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Deactivates a team.
     *
     * @param id unique team identifier
     * @return 200 when deactivation succeeds; 404 when not found
     */
    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate team", description = "Deactivates an existing team (logical delete)")
    public ResponseEntity<?> deactivateTeam(@PathVariable Long id) {
        try {
            teamService.deactivateTeam(id);
            return ResponseEntity.ok("Team deactivated");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}