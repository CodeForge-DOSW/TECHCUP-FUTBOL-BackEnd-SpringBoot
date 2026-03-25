package edu.eci.dosw.tech_cup.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TournamentModel {

    private Long id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer maxOfTeams;
    private BigDecimal teamCost;
    private TournamentStatusModel status;

    // =========================
    // BUSINESS LOGIC
    // =========================

    /**
     * Inicia el torneo.
     * Regla: solo se puede iniciar si está en ACTIVE.
     * Cambia a IN_PROGRESS.
     */
    public void start() {
        if (!status.canStart()) {
            throw new RuntimeException("Tournament cannot start unless it is ACTIVE");
        }
        this.status = TournamentStatusModel.IN_PROGRESS;
    }

    /**
     * Finaliza el torneo.
     * Regla: solo si está en IN_PROGRESS.
     */
    public void finish() {
        if (!status.isInProgress()) {
            throw new RuntimeException("Tournament must be IN_PROGRESS to finish");
        }
        this.status = TournamentStatusModel.FINISHED;
    }

    /**
     * Indica si el torneo está activo en el sistema.
     * (Activo = ACTIVE o IN_PROGRESS)
     */
    public boolean isActive() {
        return status == TournamentStatusModel.ACTIVE
                || status == TournamentStatusModel.IN_PROGRESS;
    }

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getMaxOfTeams() {
        return maxOfTeams;
    }

    public void setMaxOfTeams(Integer maxOfTeams) {
        this.maxOfTeams = maxOfTeams;
    }

    public BigDecimal getTeamCost() {
        return teamCost;
    }

    public void setTeamCost(BigDecimal teamCost) {
        this.teamCost = teamCost;
    }

    public TournamentStatusModel getStatus() {
        return status;
    }

    public void setStatus(TournamentStatusModel status) {
        this.status = status;
    }
}