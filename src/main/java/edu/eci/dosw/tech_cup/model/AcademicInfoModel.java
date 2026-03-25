package edu.eci.dosw.tech_cup.model;


/**
 * Representa la información académica de un jugador dentro del sistema.
 *
 * Esta clase encapsula la afiliación institucional del usuario, su carrera
 * y su estado académico, permitiendo validar reglas del torneo como
 * elegibilidad y composición de equipos.
 */
public class AcademicInfoModel {

    /**
     *  Semestre academico cursado
     */
    private String semester;

    /**
     * Obtiene el semestre academico registrado.
     *
     * @return semestre academico
     */
    public String getSemester() {
        return null;
    }

    /**
     * Asigna el semestre academico del jugador.
     *
     * @param semester semestre academico
     */
    public void setSemester(String semester) {

    }

    /**
     * Verifica si existe informacion de semestre.
     *
     * @return true si tiene semestre registrado
     */
    public boolean hasSemester() {
        return false;
    }

    /**
     * Verifica si el formato del semestre es valido.
     *
     * @return true si el semestre cumple el formato esperado
     */
    public boolean isValidSemester() {
        return false;
    }

    /**
     * Indica si la informacion academica esta completa.
     *
     * @return true si cumple los datos minimos requeridos
     */
    public boolean isComplete() {
        return false;
    }

    /**
     * Verifica si la informacion academica es valida para el torneo.
     *
     * @return true si cumple reglas de elegibilidad
     */
    public boolean isEligibleForTournament() {
        return false;
    }

}
