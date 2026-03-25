package edu.eci.dosw.tech_cup.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa un árbitro dentro del sistema.
 *
 * Esta clase permite gestionar los partidos asignados al árbitro,
 * así como registrar resultados y consultar detalles de los encuentros.
 */
public class RefereeModel extends UserRoleModel {

    /** Lista de partidos asignados al árbitro */
    private List<MatchModel> matches;


    /**
     * Registra los resultados de los partidos asignados.
     *
     * Permite al árbitro actualizar información relevante del partido,
     * como marcador y eventos.
     */
    public void registerMatchResults() {
        if (matches == null) {
            return;
        }
        for (MatchModel match : matches) {
            if (match != null && match.getStatus() != null && match.getStatus().isInProgress()) {
                match.setStatus(MatchStatusModel.FINISHED);
            }
        }
    }

    /**
     * Obtiene la lista de partidos asignados al árbitro.
     *
     * @return lista de partidos
     */
    public List<MatchModel> getAssignedMatches() {
        return matches == null ? new ArrayList<>() : new ArrayList<>(matches);
    }

    /**
     * Obtiene los detalles de un partido específico.
     *
     * @param match partido a consultar
     * @return partido con sus detalles
     */
    public MatchModel getMatchDetails(MatchModel match) {
        if (match == null || matches == null || !matches.contains(match)) {
            return null;
        }
        return match;
    }
}
