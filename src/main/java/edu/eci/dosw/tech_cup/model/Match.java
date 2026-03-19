package edu.eci.dosw.tech_cup.model;

import java.time.LocalDateTime;

public class Match {

    private Long id;
    private Tournament tournament;
    private Team homeTeam;
    private Team awayTeam;
    private MatchPhase phase;
    private MatchStatus status;
    private String field;
    private LocalDateTime date;
    private int scoreHome;
    private int scoreAway;



    public Match(Tournament tournament, Team homeTeam, Team awayTeam,
                 MatchPhase phase, String field, LocalDateTime date) {
        this.tournament = tournament;
        this.homeTeam   = homeTeam;
        this.awayTeam   = awayTeam;
        this.phase      = phase;
        this.field      = field;
        this.date       = date;
        this.scoreHome  = 0;
        this.scoreAway  = 0;
        this.status     = MatchStatus.SCHEDULED;
    }



    public String getScore() {
        return scoreHome + " - " + scoreAway;
    }

    public boolean isFinished() {
        return MatchStatus.FINISHED.equals(this.status);
    }



    public void start() {
        if (this.status != MatchStatus.SCHEDULED) {
            throw new IllegalStateException(
                    "El partido solo puede iniciar si está SCHEDULED. Estado actual: " + this.status
            );
        }
        this.status = MatchStatus.IN_PROGRESS;
    }

    public void registerResult(int scoreHome, int scoreAway) {
        if (this.status != MatchStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Solo se puede registrar resultado si está IN_PROGRESS. Estado actual: " + this.status
            );
        }
        if (scoreHome < 0 || scoreAway < 0) {
            throw new IllegalArgumentException("Los goles no pueden ser negativos.");
        }
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
        this.status    = MatchStatus.FINISHED;
    }

    public void cancel() {
        if (this.status == MatchStatus.FINISHED) {
            throw new IllegalStateException("No se puede cancelar un partido ya finalizado.");
        }
        this.status = MatchStatus.CANCELLED;
    }

    public Team getWinner() {
        if (!isFinished()) return null;
        if (scoreHome > scoreAway) return homeTeam;
        if (scoreAway > scoreHome) return awayTeam;
        return null;
    }

    public boolean isDraw() {
        return isFinished() && scoreHome == scoreAway;
    }



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public Team getHomeTeam() { return homeTeam; }
    public void setHomeTeam(Team homeTeam) { this.homeTeam = homeTeam; }
    public Team getAwayTeam() { return awayTeam; }
    public void setAwayTeam(Team awayTeam) { this.awayTeam = awayTeam; }
    public MatchPhase getPhase() { return phase; }
    public void setPhase(MatchPhase phase) { this.phase = phase; }
    public MatchStatus getStatus() { return status; }
    public void setStatus(MatchStatus status) { this.status = status; }
    public String getField() { return field; }
    public void setField(String field) { this.field = field; }
    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }
    public int getScoreHome() { return scoreHome; }
    public void setScoreHome(int scoreHome) { this.scoreHome = scoreHome; }
    public int getScoreAway() { return scoreAway; }
    public void setScoreAway(int scoreAway) { this.scoreAway = scoreAway; }
}