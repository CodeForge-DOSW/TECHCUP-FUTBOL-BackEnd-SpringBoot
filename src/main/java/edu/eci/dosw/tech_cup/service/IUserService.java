package edu.eci.dosw.tech_cup.service;

import edu.eci.dosw.tech_cup.model.PlayerModel;
import edu.eci.dosw.tech_cup.model.UserRoleModel;

import java.util.List;

/**
 * Contrato de servicio para operaciones CRUD y autenticación de usuarios.
 *
 * <p>Las firmas reciben y devuelven {@link PlayerModel} — implementación concreta
 * de {@link UserRoleModel} — porque es el tipo que los controladores envían
 * en el cuerpo del request y el que MapStruct puede instanciar al mapear
 * desde {@code UserEntity}.</p>
 */
public interface IUserService {

    /**
     * Registra un nuevo usuario en el sistema.
     *
     * @param user datos del usuario a crear
     * @return el usuario creado con su id asignado por la base de datos
     * @throws RuntimeException si el email ya existe o los datos son inválidos
     */
    PlayerModel createUser(PlayerModel user);

    /**
     * Recupera un usuario por su identificador único.
     *
     * @param id identificador del usuario
     * @return el usuario encontrado
     * @throws RuntimeException si el usuario no existe
     */
    PlayerModel getUser(Long id);

    /**
     * Recupera todos los usuarios registrados.
     *
     * @return lista de usuarios (puede estar vacía)
     */
    List<UserRoleModel> getAllUsers();

    /**
     * Actualiza los datos de un usuario existente.
     *
     * <p>Solo actualiza los campos no nulos del payload recibido.</p>
     *
     * @param id          identificador del usuario a actualizar
     * @param updatedUser datos nuevos a aplicar
     * @return el usuario con los datos actualizados
     * @throws RuntimeException si el usuario no existe o el email ya está en uso
     */
    PlayerModel updateUser(Long id, PlayerModel updatedUser);

    /**
     * Desactiva la cuenta de un usuario (borrado lógico).
     *
     * @param id identificador del usuario
     * @throws RuntimeException si el usuario no existe
     */
    void deactivateUser(Long id);

    /**
     * Autentica un usuario verificando email, contraseña y estado activo.
     *
     * @param email    correo electrónico del usuario
     * @param password contraseña del usuario
     * @throws RuntimeException si las credenciales son inválidas o la cuenta está inactiva
     */
    void authenticate(String email, String password);
}