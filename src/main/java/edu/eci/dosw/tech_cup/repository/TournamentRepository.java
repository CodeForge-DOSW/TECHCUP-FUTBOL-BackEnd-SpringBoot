package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.TournamentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * JPA repository for {@link TournamentEntity}.
 *
 * <p>Provides CRUD operations inherited from {@link JpaRepository} and
 * domain-specific derived queries for tournament lookup and filtering.</p>
 */
public interface TournamentRepository extends JpaRepository<TournamentEntity, Long> {

    /**
     * Finds a tournament by its name (case-insensitive).
     * Used to validate name uniqueness before creation or update.
     *
     * @param name the tournament name to search
     * @return an Optional containing the tournament if found, empty otherwise
     */
    Optional<TournamentEntity> findByNameIgnoreCase(String name);

    /**
     * Checks whether a tournament with the given name already exists (case-insensitive).
     * Used for fast duplicate validation without loading the full entity.
     *
     * @param name the tournament name to check
     * @return true if a tournament with that name exists, false otherwise
     */
    boolean existsByNameIgnoreCase(String name);

    /**
     * Finds all tournaments with a given lifecycle status.
     * Used to filter by draft, active, in_progress or finished tournaments.
     *
     * @param status the lifecycle status value to filter by
     * @return list of tournaments matching the given status
     */
    List<TournamentEntity> findByStatus(String status);
}