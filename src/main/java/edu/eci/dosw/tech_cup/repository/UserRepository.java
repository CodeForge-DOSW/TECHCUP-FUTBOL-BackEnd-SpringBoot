package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * JPA repository for {@link UserEntity}.
 *
 * <p>Provides CRUD operations inherited from {@link JpaRepository} and
 * domain-specific derived queries for user lookup and authentication.</p>
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Finds a user by their unique email address.
     * Used for authentication and duplicate email validation.
     *
     * @param email the email address to search
     * @return the user if found, null otherwise
     */
    UserEntity findByEmail(String email);

    /**
     * Finds a user by their identification document.
     * Used to validate uniqueness during registration.
     *
     * @param identification the identification value to search
     * @return an Optional containing the user if found, empty otherwise
     */
    Optional<UserEntity> findByIdentification(String identification);

    /**
     * Checks whether a user with the given email already exists.
     * Used for fast duplicate validation without loading the full entity.
     *
     * @param email the email address to check
     * @return true if a user with that email exists, false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Finds all active or inactive users based on their status.
     *
     * @param status true to find active users, false for inactive
     * @return list of users matching the given status
     */
    java.util.List<UserEntity> findByStatus(Boolean status);
}