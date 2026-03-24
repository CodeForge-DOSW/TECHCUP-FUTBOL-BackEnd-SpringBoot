package edu.eci.dosw.tech_cup.model;


/**
 * Representa la información académica de un jugador dentro del sistema.
 *
 * Esta clase encapsula la afiliación institucional del usuario, su carrera
 * y su estado académico, permitiendo validar reglas del torneo como
 * elegibilidad y composición de equipos.
 */
public class AcademicInfoModel {

    /** Identificador único de la información académica */
    private Long id;

    /** Tipo de afiliación del usuario */
    private AffiliationTypeModel affiliationType;

    /** Carrera académica del usuario */
    private CareerModel career;

    /** Semestre actual del usuario */
    private Integer semester;

    /** Código institucional o identificador académico (opcional) */
    private String institutionalId;

    /** Indica si la información ha sido verificada */
    private boolean verified;

    // ===================== MÉTODOS =====================

    /**
     * Verifica si el usuario es estudiante activo.
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
     * Verifica si la información académica está validada.
     *
     * @return true si está verificada
     */
    public boolean isVerified() { return false; }

    /**
     * Marca la información académica como verificada.
     */
    public void verify() {}

    /**
     * Cambia la carrera del usuario.
     *
     * @param career nueva carrera
     */
    public void changeCareer(CareerModel career) {}

    /**
     * Cambia el tipo de afiliación.
     *
     * @param type nuevo tipo de afiliación
     */
    public void changeAffiliation(AffiliationTypeModel type) {}

    /**
     * Cambia el semestre del usuario.
     *
     * @param semester nuevo semestre
     */
    public void changeSemester(Integer semester) {}

    /**
     * Verifica si el usuario pertenece a una carrera específica.
     *
     * @param career carrera a validar
     * @return true si pertenece a la carrera
     */
    public boolean belongsToCareer(CareerModel career) { return false; }

    /**
     * Verifica si el semestre del usuario es válido.
     *
     * @return true si el semestre es mayor a 0
     */
    public boolean isValidSemester() { return false; }
}
