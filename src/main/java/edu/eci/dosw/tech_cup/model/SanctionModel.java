package edu.eci.dosw.tech_cup.model;

/**
 * Representa una sanción dentro del torneo.
 *
 * Esta clase permite definir penalizaciones aplicables a jugadores o equipos
 * cuando incumplen las reglas del sistema.
 */
public class SanctionModel {

    /** Descripción de la sanción */
    private String description;

    /** Penalización asociada a la sanción */
    private String penalty;

    // ===================== MÉTODOS =====================

    /**
     * Obtiene la descripción de la sanción.
     *
     * @return descripción
     */
    public String getDescription() { return null; }

    /**
     * Cambia la descripción de la sanción.
     *
     * @param description nueva descripción
     */
    public void setDescription(String description) {}

    /**
     * Obtiene la penalización.
     *
     * @return penalización
     */
    public String getPenalty() { return null; }

    /**
     * Cambia la penalización.
     *
     * @param penalty nueva penalización
     */
    public void setPenalty(String penalty) {}

    /**
     * Verifica si la sanción es válida.
     *
     * @return true si tiene descripción y penalización
     */
    public boolean isValid() { return false; }

    /**
     * Verifica si la sanción es grave.
     *
     * @return true si implica una penalización severa
     */
    public boolean isSevere() { return false; }
}