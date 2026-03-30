package edu.eci.dosw.tech_cup.model;

import java.util.List;

/**
 * Representa un administrador del sistema.
 *
 * Es responsable de gestionar elementos académicos como las carreras,
 * así como de asignar roles a los usuarios dentro del sistema.
 */
public class AdministratorModel extends UserRoleModel {

    /**
     * Cambia el rol de un usuario dentro del sistema.
     *
     * @param user usuario al que se le asigna el rol
     * @param role nuevo rol del usuario
     */
    public void changeUserRole(PlayerModel user, UserRoleModel role) {

    }

    /**
     * Crea una nueva carrera académica.
     *
     * @param name nombre de la carrera
     * @return nueva instancia de CareerModel
     */
    public CareerModel createCareer(String name) {
        return null;
    }

    /**
     * Actualiza la información de una carrera existente.
     *
     * @param career carrera a actualizar
     */
    public void updateCareer(CareerModel career) {

    }

    /**
     * Elimina una carrera del sistema.
     *
     * @param career carrera a eliminar
     */
    public void deleteCareer(CareerModel career) {

    }

    /**
     * Obtiene la lista de todas las carreras registradas.
     *
     * @return lista de carreras
     */
    public List<CareerModel> getCareers() { return null; }

    /**
     * Busca una carrera por su nombre.
     *
     * @param name nombre de la carrera a buscar
     * @return carrera encontrada o null si no existe
     */
    public CareerModel findCareerByName(String name) {
        return null;
    }

    /**
     * Verifica si una carrera ya existe en el sistema.
     *
     * @param name nombre de la carrera
     * @return true si la carrera existe
     */
    public boolean careerExists(String name) {
        return false;
    }

    /**
     * Asigna una carrera a un usuario del sistema.
     *
     * @param user usuario al que se asigna la carrera
     * @param career carrera a asignar
     */
    public void assignCareerToUser(PlayerModel user, CareerModel career) {

    }

    /**
     * Remueve el rol actual de un usuario.
     *
     * @param user usuario al que se le remueve el rol
     */
    public void removeUserRole(PlayerModel user) {

    }

    /**
     * Obtiene los usuarios que tienen un rol específico.
     *
     * @param role rol a consultar
     * @return lista de usuarios con el rol indicado
     */
    public List<PlayerModel> getUsersByRole(UserRoleModel role) {
        return null;
    }
}