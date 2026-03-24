package edu.eci.dosw.tech_cup.model;

import java.util.List;

/**
 * Representa la afiliación institucional de un usuario dentro del sistema.
 *
 * Esta clase permite identificar el tipo de vínculo del usuario con la institución
 * (estudiante, egresado, etc.) y las carreras a las que pertenece.
 *
 * Es fundamental para validar reglas del torneo como elegibilidad y
 * composición de equipos.
 */
public class AffiliationModel {

    /** Tipo de afiliación del usuario */
    private AffiliationTypeModel type;

    /** Lista de carreras a las que pertenece el usuario */
    private List<CareerModel> careers;

    // ===================== MÉTODOS =====================

    /**
     * Verifica si el usuario es estudiante.
     *
     * @return true si es estudiante
     */
    public boolean isStudent() { return false; }

    /**
     * Verifica si el usuario es egresado.
     *
     * @return true si es egresado
     */
    public boolean isGraduate() { return false; }

    /**
     * Verifica si el usuario pertenece al personal administrativo.
     *
     * @return true si es administrativo
     */
    public boolean isAdministrative() { return false; }

    /**
     * Verifica si el usuario pertenece a una carrera específica.
     *
     * @param career carrera a validar
     * @return true si pertenece a la carrera
     */
    public boolean belongsToCareer(CareerModel career) { return false; }

    /**
     * Verifica si el usuario pertenece a alguna de las carreras registradas.
     *
     * @param careers lista de carreras
     * @return true si pertenece al menos a una
     */
    public boolean belongsToAnyCareer(List<CareerModel> careers) { return false; }

    /**
     * Agrega una carrera a la afiliación.
     *
     * @param career carrera a agregar
     */
    public void addCareer(CareerModel career) {}

    /**
     * Elimina una carrera de la afiliación.
     *
     * @param career carrera a eliminar
     */
    public void removeCareer(CareerModel career) {}

    /**
     * Cambia el tipo de afiliación del usuario.
     *
     * @param type nuevo tipo de afiliación
     */
    public void changeType(AffiliationTypeModel type) {}
}
