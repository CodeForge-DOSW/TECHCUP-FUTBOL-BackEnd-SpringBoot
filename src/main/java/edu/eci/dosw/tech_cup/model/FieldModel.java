package edu.eci.dosw.tech_cup.model;

import java.util.Objects;

/**
 * Representa una cancha donde se disputan los partidos del torneo.
 *
 * Esta entidad permite identificar la ubicación física de los encuentros,
 * siendo utilizada para organizar la logística de los partidos.
 */
public class FieldModel {

    /** Identificador único de la cancha */
    private Long id;

    /** Nombre de la cancha */
    private String name;

    /** Ubicación de la cancha */
    private String location;

    // ===================== MÉTODOS =====================

    /**
     * Obtiene el identificador de la cancha.
     *
     * @return id de la cancha
     */
    public Long getId() { return id; }

    /**
     * Obtiene el nombre de la cancha.
     *
     * @return nombre de la cancha
     */
    public String getName() { return name; }

    /**
     * Cambia el nombre de la cancha.
     *
     * @param name nuevo nombre
     */
    public void setName(String name) { this.name = name; }

    /**
     * Obtiene la ubicación de la cancha.
     *
     * @return ubicación
     */
    public String getLocation() { return location; }

    /**
     * Cambia la ubicación de la cancha.
     *
     * @param location nueva ubicación
     */
    public void setLocation(String location) { this.location = location; }

    /**
     * Verifica si la cancha es válida.
     *
     * @return true si tiene nombre y ubicación definidos
     */
    public boolean isValid() {
        return false;
    }

    /**
     * Compara si dos canchas son iguales.
     *
     * @param other otra cancha
     * @return true si representan la misma cancha
     */
    public boolean sameField(FieldModel other) {
        return false;
    }
}
