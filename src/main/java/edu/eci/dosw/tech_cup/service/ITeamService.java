package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.TeamResponseModel;

import java.util.List;

/**
 * Service contract for team-related business operations.
 */
public interface ITeamService {

    /**
     * Creates a new team linked to a tournament and captain.
     *
     * @param team the team data to persist
     * @return the created team with assigned id
     */
    TeamResponseModel createTeam(TeamResponseModel team);

    /**
     * Retrieves a team by its id.
     *
     * @param id unique team identifier
     * @return the team if found
     */
    TeamResponseModel getTeam(Long id);

    /**
     * Retrieves all teams.
     *
     * @return list of all teams
     */
    List<TeamResponseModel> getAllTeams();

    /**
     * Retrieves all teams belonging to a specific tournament.
     *
     * @param tournamentId the tournament identifier
     * @return list of teams in the tournament
     */
    List<TeamResponseModel> getTeamsByTournament(Long tournamentId);

    /**
     * Updates an existing team's properties.
     *
     * @param id   unique team identifier
     * @param team payload with fields to update
     * @return the updated team
     */
    TeamResponseModel updateTeam(Long id, TeamResponseModel team);

    /**
     * Deactivates a team (logical delete).
     *
     * @param id unique team identifier
     */
    void deactivateTeam(Long id);
}