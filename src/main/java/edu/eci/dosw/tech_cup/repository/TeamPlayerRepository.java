package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.TeamPlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for {@link TeamPlayerEntity}.
 */
public interface TeamPlayerRepository extends JpaRepository<TeamPlayerEntity, Long> {

    /**
     * Finds all players belonging to a specific team.
     */
    List<TeamPlayerEntity> findByTeam_TeamId(Long teamId);

    /**
     * Checks if a user is already a member of a specific team.
     */
    boolean existsByTeam_TeamIdAndUser_UserId(Long teamId, Long userId);

    /**
     * Finds a specific membership record.
     */
    Optional<TeamPlayerEntity> findByTeam_TeamIdAndUser_UserId(Long teamId, Long userId);
}