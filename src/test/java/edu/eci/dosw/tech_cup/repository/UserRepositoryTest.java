package edu.eci.dosw.tech_cup.repository;

import edu.eci.dosw.tech_cup.entity.UserEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // ─── Helper ─────────────────────────────────────────────────────────────

    private UserEntity buildUser(String email, String identification) {
        UserEntity user = new UserEntity();
        user.setFirstName("Juan");
        user.setLastName("Pérez");
        user.setEmail(email);
        user.setPasswordUser("password123");
        user.setIdentification(identification);
        user.setDateBirth(LocalDate.of(2000, 1, 15));
        user.setGender("M");
        user.setStatus(true);
        return user;
    }

    // ─── 1. Prueba de guardado ───────────────────────────────────────────────

    @DisplayName("Should save a user and assign an auto-generated id")
    @Test
    void shouldSaveUser() {
        UserEntity user = buildUser("juan@mail.escuelaing.edu.co", "1001");

        UserEntity saved = userRepository.save(user);

        assertNotNull(saved.getUserId());
        assertEquals("juan@mail.escuelaing.edu.co", saved.getEmail());
        assertTrue(saved.getStatus());
    }

    // ─── 2. Prueba de consulta ───────────────────────────────────────────────

    @DisplayName("Should find a user by email")
    @Test
    void shouldFindByEmail() {
        userRepository.save(buildUser("ana@mail.escuelaing.edu.co", "1002"));

        Optional<UserEntity> found = userRepository.findByEmail("ana@mail.escuelaing.edu.co");

        assertTrue(found.isPresent());
        assertEquals("Juan", found.map(UserEntity::getFirstName).orElse(""));
    }

    @DisplayName("Should return true when email already exists")
    @Test
    void shouldDetectExistingEmail() {
        userRepository.save(buildUser("pedro@mail.escuelaing.edu.co", "1003"));

        assertTrue(userRepository.existsByEmail("pedro@mail.escuelaing.edu.co"));
        assertFalse(userRepository.existsByEmail("noexiste@mail.com"));
    }

    @DisplayName("Should find users by status")
    @Test
    void shouldFindByStatus() {
        UserEntity active = buildUser("activo@mail.escuelaing.edu.co", "1004");
        UserEntity inactive = buildUser("inactivo@mail.escuelaing.edu.co", "1005");
        inactive.setStatus(false);

        userRepository.save(active);
        userRepository.save(inactive);

        List<UserEntity> activeUsers = userRepository.findByStatus(true);
        List<UserEntity> inactiveUsers = userRepository.findByStatus(false);

        assertEquals(1, activeUsers.size());
        assertEquals(1, inactiveUsers.size());
    }

    @DisplayName("Should find a user by identification")
    @Test
    void shouldFindByIdentification() {
        userRepository.save(buildUser("carlos@mail.escuelaing.edu.co", "9999"));

        Optional<UserEntity> found = userRepository.findByIdentification("9999");

        assertTrue(found.isPresent());
        assertEquals("9999", found.get().getIdentification());
    }

    // ─── 3. Prueba de relación (herencia SINGLE_TABLE) ───────────────────────

    @DisplayName("Should persist multiple users in the same table with unique emails")
    @Test
    void shouldSaveMultipleUsersWithUniqueEmails() {
        userRepository.save(buildUser("u1@mail.escuelaing.edu.co", "2001"));
        userRepository.save(buildUser("u2@mail.escuelaing.edu.co", "2002"));
        userRepository.save(buildUser("u3@mail.escuelaing.edu.co", "2003"));

        List<UserEntity> all = userRepository.findAll();

        assertEquals(3, all.size());
    }

    // ─── 4. Prueba de actualización y eliminación ────────────────────────────

    @DisplayName("Should update user status to inactive")
    @Test
    void shouldUpdateUserStatus() {
        UserEntity user = userRepository.save(buildUser("update@mail.escuelaing.edu.co", "3001"));

        user.setStatus(false);
        UserEntity updated = userRepository.save(user);

        assertFalse(updated.getStatus());
    }

    @DisplayName("Should delete a user by id")
    @Test
    void shouldDeleteUser() {
        UserEntity user = userRepository.save(buildUser("delete@mail.escuelaing.edu.co", "4001"));
        Long id = user.getUserId();

        userRepository.deleteById(id);

        assertFalse(userRepository.findById(id).isPresent());
    }
}