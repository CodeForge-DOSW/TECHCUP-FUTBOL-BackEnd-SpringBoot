package edu.eci.dosw.tech_cup.model;

import java.time.LocalDateTime;

/**
 * Representa un organizador del torneo.
 *
 * Es responsable de gestionar el ciclo de vida del torneo, incluyendo
 * su creación, inicio, finalización y la revisión de inscripciones.
 */
public class OrganizerModel extends UserRoleModel {

    /**
     * Crea un nuevo torneo.
     *
     * @param startDate fecha de inicio
     * @param endDate fecha de fin
     * @param maxTeams número máximo de equipos
     * @param costPerTeam costo por equipo
     * @return nuevo torneo creado
     */
    public TournamentModel createTournament(
            LocalDateTime startDate,
            LocalDateTime endDate,
            int maxTeams,
            double costPerTeam
    ) {
        return null;
    }

    /**
     * Inicia un torneo.
     *
     * @param tournament torneo a iniciar
     */
    public void startTournament(TournamentModel tournament) {

    }

    /**
     * Finaliza un torneo.
     *
     * @param tournament torneo a finalizar
     */
    public void finishTournament(TournamentModel tournament) {

    }

    /**
     * Obtiene un torneo por su identificador.
     *
     * @param id identificador del torneo
     * @return torneo encontrado
     */
    public TournamentModel getTournament(Long id) {
        return null;
    }

    /**
     * Revisa una inscripción de equipo.
     *
     * @param registration inscripción a revisar
     */
    public void reviewRegistration(RegistrationModel registration) {

    }
}
