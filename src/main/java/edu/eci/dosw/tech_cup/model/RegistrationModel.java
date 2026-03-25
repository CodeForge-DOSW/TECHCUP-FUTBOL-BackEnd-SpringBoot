package edu.eci.dosw.tech_cup.model;

import java.time.LocalDateTime;

/**
 * Representa la inscripción de un equipo a un torneo.
 *
 * Esta clase gestiona el proceso de registro, incluyendo el envío
 * del comprobante de pago, el estado de la inscripción y su validación
 * por parte del organizador.
 */
public class RegistrationModel {

    /** Identificador único de la inscripción */
    private Long id;

    /** Equipo que se inscribe */
    private TeamModel team;

    /** Torneo al que se realiza la inscripción */
    private TournamentModel tournament;

    /** URL del comprobante de pago */
    private String paymentProofUrl;

    /** Estado actual de la inscripción */
    private RegistrationStatusModel status;

    /** Fecha de creación de la inscripción */
    private LocalDateTime createdAt;

    /**
     * Envía el comprobante de pago de la inscripción.
     *
     * @param url URL del comprobante
     */
    public void submitProof(String url) {
    }

    /**
     * Marca la inscripción como en revisión.
     */
    public void markInReview() {  }

    /**
     * Aprueba la inscripción.
     */
    public void approve() { }

    /**
     * Rechaza la inscripción.
     */
    public void reject() {  }

    /**
     * Verifica si la inscripción está aprobada.
     *
     * @return true si el estado es APPROVED
     */
    public boolean isApproved() { return false; }
}
