package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.TeamEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for {@link TeamEntity}.
 *
 * <p>Provides CRUD operations inherited from {@link JpaRepository} and
 * domain-specific derived queries for team lookup by tournament and captain.</p>
 */
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

    /**
     * Finds all teams registered in a specific tournament.
     * Used to list teams per tournament.
     *
     * @param tournamentId the tournament identifier to filter by
     * @return list of teams belonging to the given tournament
     */
    List<TeamEntity> findByTournament_TournamentId(Long tournamentId);

    /**
     * Finds a team by its captain user identifier.
     * Used to validate that a user is not already captain of another team.
     *
     * @param userId the captain user identifier
     * @return an Optional containing the team if found, empty otherwise
     */
    Optional<TeamEntity> findByCaptain_UserId(Long userId);

    /**
     * Checks whether a team with the given name already exists in a tournament.
     * Used to enforce unique team names within a tournament.
     *
     * @param name the team name to check
     * @param tournamentId the tournament identifier to scope the check
     * @return true if a team with that name exists in the tournament, false otherwise
     */
    boolean existsByNameAndTournament_TournamentId(String name, Long tournamentId);

    /**
     * Finds all active or inactive teams based on their status.
     *
     * @param status true to find active teams, false for inactive
     * @return list of teams matching the given status
     */
    List<TeamEntity> findByStatus(Boolean status);
}