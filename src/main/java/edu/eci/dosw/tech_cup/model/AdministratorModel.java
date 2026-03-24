package edu.eci.dosw.tech_cup.model;

import java.util.List;

/**
 * Representa un administrador del sistema.
 *
 * Es responsable de gestionar elementos académicos como las carreras,
 * así como de asignar roles a los usuarios dentro del sistema.
 */
public class AdministratorModel {

    /** Identificador único del administrador */
    private Long id;

    /** Nombre del administrador */
    private String name;

    /** Correo electrónico del administrador */
    private String email;

    // ===================== MÉTODOS =====================

    /**
     * Cambia el rol de un usuario dentro del sistema.
     *
     * @param user usuario al que se le asigna el rol
     * @param role nuevo rol del usuario
     */
    public void changeUserRole(PlayerModel user, UserRoleModel role) {}

    /**
     * Crea una nueva carrera académica.
     *
     * @param name nombre de la carrera
     * @return nueva instancia de CareerModel
     */
    public CareerModel createCareer(String name) { return null; }

    /**
     * Actualiza la información de una carrera existente.
     *
     * @param career carrera a actualizar
     */
    public void updateCareer(CareerModel career) {}

    /**
     * Elimina una carrera del sistema.
     *
     * @param career carrera a eliminar
     */
    public void deleteCareer(CareerModel career) {}

    /**
     * Obtiene la lista de todas las carreras registradas.
     *
     * @return lista de carreras
     */
    public List<CareerModel> getCareers() { return null; }
}