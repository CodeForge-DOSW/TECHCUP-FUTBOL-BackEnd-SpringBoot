package edu.eci.dosw.tech_cup.model;

/**
 * Representa una regla dentro del torneo.
 *
 * Esta clase permite definir las condiciones y restricciones que deben
 * cumplirse durante el desarrollo del torneo, como requisitos de equipos,
 * comportamiento de jugadores o normas de juego.
 */
public class RuleModel {

    /** Descripción de la regla */
    private String description;


    /**
     * Obtiene la descripción de la regla.
     *
     * @return descripción de la regla
     */
    public String getDescription() { return description; }

    /**
     * Cambia la descripción de la regla.
     *
     * @param description nueva descripción
     */
    public void setDescription(String description) { this.description = description; }

    /**
     * Verifica si la regla es válida.
     *
     * @return true si la descripción no es nula ni vacía
     */
    public boolean isValid() { return false; }

    /**
     * Verifica si la regla aplica a un contexto específico.
     *
     * @return true si aplica
     */
    public boolean applies() { return isValid(); }
}