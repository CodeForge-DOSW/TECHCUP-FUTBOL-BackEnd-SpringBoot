package edu.eci.dosw.tech_cup.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa un jugador dentro del sistema.
 *
 * Esta clase encapsula la información personal y deportiva del usuario,
 * incluyendo sus posiciones preferidas, número de camiseta y estado
 * dentro del sistema.
 */
public class PlayerModel extends UserRoleModel {

    /**
     * Posiciones preferidas del jugador
     */
    private List<PositionModel> preferredPositions;

    /**
     * Número de camiseta
     */
    private int jerseyNumber;

    /**
     * URL de la foto del jugador
     */
    private String photoUrl;

    /**
     * Indica si el jugador está disponible
     */
    private boolean available;

    /**
     * Verifica si el jugador es válido.
     *
     * @return true si cumple las condiciones requeridas
     */
    public boolean isValid() {
        return false;
    }

    /**
     * Cambia la disponibilidad del jugador.
     *
     * @param status nuevo estado de disponibilidad
     */
    public void setAvailable(boolean status) {
        this.available = status;
    }

    public boolean isAvailable() {
        return available;
    }

    /**
     * Acepta una invitación a un equipo.
     *
     * @param invitation invitación recibida
     */
    public void acceptInvitation(InvitationModel invitation) {

    }

    /**
     * Rechaza una invitación a un equipo.
     *
     * @param invitation invitación recibida
     */
    public void rejectInvitation(InvitationModel invitation) {


    }

    /**
     * Envía el comprobante de pago para una inscripción.
     *
     * @param registration inscripción asociada
     * @param url          URL del comprobante
     */
    public void submitPaymentProof(RegistrationModel registration, String url) {

    }

    /**
     * Crea una alineación para un equipo en un partido.
     *
     * @param team  equipo
     * @param match partido
     * @return nueva alineación creada
     */
    public LineupModel createLineup(TeamModel team, MatchModel match) {
        return null;
    }

    public void setPreferredPositions(List<PositionModel> preferredPositions) {

    }
}

