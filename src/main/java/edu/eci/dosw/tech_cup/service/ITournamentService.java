package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.Tournament;

import java.util.List;

public interface ITournamentService {

    // CREATE
    Tournament createTournament(Tournament tournament);

    // READ
    Tournament getTournament(Long id);
    List<Tournament> getAllTournaments();

    // UPDATE
    Tournament updateTournament(Long id, Tournament updatedTournament);

    // DELETE (CANCELAR)
    void cancelTournament(Long id);

    // LIFECYCLE
    void startTournament(Long id);
    void finishTournament(Long id);
}