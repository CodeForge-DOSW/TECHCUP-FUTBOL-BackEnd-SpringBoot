package edu.eci.dosw.tech_cup.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a match within the tournament.
 *
 * <p>This model encapsulates all information related to a fixture, including
 * participating teams, score, lineups, events, venue, and match status.</p>
 */
public class MatchModel {

    /** Unique identifier of the match. */
    private Long id;

    /** Tournament to which the match belongs. */
    private TournamentModel tournament;

    /** Home team. */
    private TeamModel homeTeam;

    /** Away team. */
    private TeamModel awayTeam;

    /** Tournament phase of the match. */
    private MatchPhaseModel phase;

    /** Current match status. */
    private MatchStatusModel status;

    /** Field where the match is played. */
    private FieldModel field;

    /** Match date and time. */
    private LocalDateTime date;

    /** Score of the home team. */
    private int scoreHome;

    /** Score of the away team. */
    private int scoreAway;

    /** Assigned referee. */
    private RefereeModel referee;

    /** Home team lineup. */
    private LineupModel homeLineup;

    /** Away team lineup. */
    private LineupModel awayLineup;

    /** List of events recorded during the match. */
    private List<MatchEventModel> events;

    /**
     * Returns the score of the match as formatted text.
     *
     * @return score string, for example {@code "2 - 1"}
     */
    public String getScore() { return ""; }

    /**
     * Indicates whether the match has finished.
     *
     * @return {@code true} if the status is finished
     */
    public boolean isFinished() { return false; }

    /**
     * Returns the lineup associated with the opposite team.
     *
     * @param team team used as reference
     * @return corresponding opponent lineup
     */
    public LineupModel getOpponentLineup(TeamModel team) {
        return null;
    }

    /**
     * Indicates whether the match can start.
     *
     * @return {@code true} if the match satisfies the conditions to start
     */
    public boolean canStartMatch() {
        return false;
    }

    /**
     * Returns the winning team of the match.
     *
     * @return winning team, or {@code null} if the match is a draw
     */
    public TeamModel getWinner() {
        return null;
    }

    /**
     * Indicates whether the match ended in a draw.
     *
     * @return {@code true} if both scores are equal
     */
    public boolean isDraw() { return false; }

    /**
     * Indicates whether the match contains valid required data.
     *
     * @return {@code true} if teams, date, and field are defined
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
