package edu.eci.dosw.tech_cup.model;

import java.util.Objects;

/**
 * Representa una carrera académica dentro de la institución.
 *
 * Esta entidad permite identificar la formación académica de los usuarios,
 * siendo utilizada para validar reglas del sistema como elegibilidad
 * y composición de equipos en el torneo.
 */
public class CareerModel {

    /** Identificador único de la carrera */
    private Long id;

    /** Nombre de la carrera (ej: Ingeniería de Sistemas) */
    private String name;

    // ===================== MÉTODOS =====================

    /**
     * Obtiene el identificador de la carrera.
     *
     * @return id de la carrera
     */
    public Long getId() { return id; }

    /**
     * Obtiene el nombre de la carrera.
     *
     * @return nombre de la carrera
     */
    public String getName() { return name; }

    /**
     * Cambia el nombre de la carrera.
     *
     * @param name nuevo nombre
     */
    public void setName(String name) { this.name = name; }

    /**
     * Verifica si la carrera es válida.
     *
     * @return true si tiene nombre definido
     */
    public boolean isValid() { return false; }

    /**
     * Compara si dos carreras son iguales.
     *
     * @param other otra carrera
     * @return true si representan la misma carrera
     */
    public boolean sameCareer(CareerModel other) {
        return false;
    }
}