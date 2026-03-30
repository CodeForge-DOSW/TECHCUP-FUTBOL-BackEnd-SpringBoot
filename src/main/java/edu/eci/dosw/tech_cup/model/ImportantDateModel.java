package edu.eci.dosw.tech_cup.model;

import java.time.LocalDateTime;

/**
 * Representa una fecha importante dentro del torneo.
 *
 * Esta clase permite registrar eventos clave como inicio del torneo,
 * cierre de inscripciones, fechas límite, entre otros.
 */
public class ImportantDateModel {

    /** Nombre o descripción de la fecha importante */
    private String name;

    /** Fecha y hora del evento */
    private LocalDateTime date;

    // ===================== MÉTODOS =====================

    /**
     * Obtiene el nombre de la fecha importante.
     *
     * @return nombre o descripción
     */
    public String getName() { return name; }

    /**
     * Cambia el nombre de la fecha importante.
     *
     * @param name nuevo nombre
     */
    public void setName(String name) { this.name = name; }

    /**
     * Obtiene la fecha del evento.
     *
     * @return fecha y hora
     */
    public LocalDateTime getDate() { return date; }

    /**
     * Cambia la fecha del evento.
     *
     * @param date nueva fecha
     */
    public void setDate(LocalDateTime date) { this.date = date; }

    /**
     * Verifica si la fecha es válida.
     *
     * @return true si tiene nombre y fecha definidos
     */
    public boolean isValid() { return name != null && !name.trim().isEmpty() && date != null; }

    /**
     * Verifica si la fecha ya ocurrió.
     *
     * @return true si la fecha es anterior al momento actual
     */
    public boolean hasPassed() { return date != null && date.isBefore(LocalDateTime.now()); }

    /**
     * Verifica si la fecha está próxima a ocurrir.
     *
     * @return true si está cerca en el tiempo
     */
    public boolean isUpcoming() {
        return false;
    }
}
