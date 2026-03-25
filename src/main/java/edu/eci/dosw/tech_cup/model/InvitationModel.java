package edu.eci.dosw.tech_cup.model;

import java.time.LocalDateTime;

/**
 * Representa una invitación enviada a un jugador para unirse a un equipo.
 *
 * Esta clase permite gestionar el proceso de invitación, incluyendo
 * su estado, mensaje y fecha de creación.
 */
public class InvitationModel {

    /** Identificador único de la invitación */
    private Long id;

    /** Jugador que envía la invitación */
    private PlayerModel sender;

    /** Jugador que recibe la invitación */
    private PlayerModel receiver;

    /** Equipo al que se invita al jugador */
    private TeamModel team;

    /** Mensaje opcional de la invitación */
    private String message;

    /** Estado actual de la invitación */
    private InvitationStatusModel status;

    /** Fecha de creación de la invitación */
    private LocalDateTime createdAt;

    // ===================== MÉTODOS =====================

    /**
     * Acepta la invitación.
     *
     * Cambia el estado de la invitación a aceptada.
     */
    public void accept() { this.status = InvitationStatusModel.ACCEPTED; }

    /**
     * Rechaza la invitación.
     *
     * Cambia el estado de la invitación a rechazada.
     */
    public void reject() { this.status = InvitationStatusModel.REJECTED; }

    /**
     * Verifica si la invitación está pendiente.
     *
     * @return true si está en estado pendiente
     */
    public boolean isPending() { return false; }

    /**
     * Verifica si la invitación ha sido aceptada.
     *
     * @return true si está aceptada
     */
    public boolean isAccepted() { return false; }

    /**
     * Verifica si la invitación ha sido rechazada.
     *
     * @return true si está rechazada
     */
    public boolean isRejected() { return false; }

    /**
     * Verifica si la invitación es válida.
     *
     * @return true si tiene remitente, receptor y equipo definidos
     */
    public boolean isValid() {
        return false;
    }
}
