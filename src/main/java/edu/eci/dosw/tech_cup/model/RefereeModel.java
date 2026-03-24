package edu.eci.dosw.tech_cup.model;

import java.util.List;

/**
 * Representa un árbitro dentro del sistema.
 *
 * Esta clase permite gestionar los partidos asignados al árbitro,
 * así como registrar resultados y consultar detalles de los encuentros.
 */
public class RefereeModel {

    /** Lista de partidos asignados al árbitro */
    private List<MatchModel> matches;

    // ===================== MÉTODOS =====================

    /**
     * Registra los resultados de los partidos asignados.
     *
     * Permite al árbitro actualizar información relevante del partido,
     * como marcador y eventos.
     */
    public void registerMatchResults() {}

    /**
     * Obtiene la lista de partidos asignados al árbitro.
     *
     * @return lista de partidos
     */
    public List<MatchModel> getAssignedMatches() { return null; }

    /**
     * Obtiene los detalles de un partido específico.
     *
     * @param match partido a consultar
     * @return partido con sus detalles
     */
    public MatchModel getMatchDetails(MatchModel match) { return null; }
}
