package edu.eci.dosw.tech_cup.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

/**
 * Representa un partido dentro del torneo.
 *
 * Esta clase encapsula toda la información relacionada con un encuentro,
 * incluyendo los equipos participantes, marcador, alineaciones, eventos
 * y estado del partido.
 */
public class MatchModel {

    /** Identificador único del partido */
    private Long id;

    /** Torneo al que pertenece el partido */
    private TournamentModel tournament;

    /** Equipo local */
    private TeamModel homeTeam;

    /** Equipo visitante */
    private TeamModel awayTeam;

    /** Fase del torneo */
    private MatchPhaseModel phase;

    /** Estado actual del partido */
    private MatchStatusModel status;

    /** Cancha donde se juega el partido */
    private FieldModel field;

    /** Fecha y hora del partido */
    private LocalDateTime date;

    /** Marcador del equipo local */
    private int scoreHome;

    /** Marcador del equipo visitante */
    private int scoreAway;

    /** Árbitro asignado */
    private RefereeModel referee;

    /** Alineación del equipo local */
    private LineupModel homeLineup;

    /** Alineación del equipo visitante */
    private LineupModel awayLineup;

    /** Lista de eventos del partido */
    private List<MatchEventModel> events;

    /**
     * Obtiene el marcador del partido en formato texto.
     *
     * @return marcador como string (ej: "2 - 1")
     */
    public String getScore() { return ""; }

    /**
     * Verifica si el partido ha finalizado.
     *
     * @return true si está en estado FINISHED
     */
    public boolean isFinished() { return false; }

    /**
     * Obtiene la alineación de un equipo específico.
     *
     * @param team equipo a consultar
     * @return alineación correspondiente
     */
    public LineupModel getOpponentLineup(TeamModel team) {
        return null;
    }

    /**
     * Verifica si el partido puede iniciar.
     *
     * @return true si cumple condiciones para iniciar
     */
    public boolean canStartMatch() {
        return false;
    }

    /**
     * Obtiene el equipo ganador del partido.
     *
     * @return equipo ganador o null si hay empate
     */
    public TeamModel getWinner() {
        return null;
    }

    /**
     * Verifica si el partido terminó en empate.
     *
     * @return true si los marcadores son iguales
     */
    public boolean isDraw() { return false; }

    /**
     * Verifica si el partido es válido.
     *
     * @return true si tiene equipos, fecha y cancha definidos
     */
    public boolean isValid() {
        return false;
    }

    public MatchStatusModel getStatus() { return status; }

    public TeamModel getHomeTeam() { return homeTeam; }

    public TeamModel getAwayTeam() { return awayTeam; }

    public LocalDateTime getDate() { return date; }

    public List<MatchEventModel> getEvents() {
        return null;
    }

    public int getScoreHome() { return scoreHome; }

    public int getScoreAway() { return scoreAway; }

    public void setTournament(TournamentModel tournament) { this.tournament = tournament; }

    public void setHomeTeam(TeamModel homeTeam) { this.homeTeam = homeTeam; }

    public void setAwayTeam(TeamModel awayTeam) { this.awayTeam = awayTeam; }

    public void setPhase(MatchPhaseModel phase) { this.phase = phase; }

    public void setStatus(MatchStatusModel status) { this.status = status; }

    public void setField(FieldModel field) { this.field = field; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public void setScoreHome(int scoreHome) { this.scoreHome = scoreHome; }

    public void setScoreAway(int scoreAway) { this.scoreAway = scoreAway; }

    public void setReferee(RefereeModel referee) { this.referee = referee; }

    public void setHomeLineup(LineupModel homeLineup) { this.homeLineup = homeLineup; }

    public void setAwayLineup(LineupModel awayLineup) { this.awayLineup = awayLineup; }
}
