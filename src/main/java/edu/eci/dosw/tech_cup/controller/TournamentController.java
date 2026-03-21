package edu.eci.dosw.tech_cup.controller;

import edu.eci.dosw.tech_cup.model.Tournament;
import edu.eci.dosw.tech_cup.service.ITournamentService;
import edu.eci.dosw.tech_cup.service.TournamentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    private final ITournamentService tournamentService = new TournamentService();


    @PostMapping
    public ResponseEntity<?> createTournament(@org.springframework.web.bind.annotation.RequestBody Tournament tournament) {
        try {
            Tournament created = tournamentService.createTournament(tournament);
            return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        return ResponseEntity.ok(tournamentService.getAllTournaments());
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getTournament(@org.springframework.web.bind.annotation.PathVariable Long id) {
        try {
            return ResponseEntity.ok(tournamentService.getTournament(id));
        } catch (RuntimeException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTournament(@org.springframework.web.bind.annotation.PathVariable Long id,
                                              @org.springframework.web.bind.annotation.RequestBody Tournament tournament) {
        try {
            Tournament updated = tournamentService.updateTournament(id, tournament);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelTournament(@org.springframework.web.bind.annotation.PathVariable Long id) {
        try {
            tournamentService.cancelTournament(id);
            return ResponseEntity.ok("Tournament cancelled");
        } catch (RuntimeException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/start")
    public ResponseEntity<?> startTournament(@org.springframework.web.bind.annotation.PathVariable Long id) {
        try {
            tournamentService.startTournament(id);
            return ResponseEntity.ok("Tournament started");
        } catch (RuntimeException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/{id}/finish")
    public ResponseEntity<?> finishTournament(@org.springframework.web.bind.annotation.PathVariable Long id) {
        try {
            tournamentService.finishTournament(id);
            return ResponseEntity.ok("Tournament finished");
        } catch (RuntimeException e) {
            return ResponseEntity.status(org.springframework.http.HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}