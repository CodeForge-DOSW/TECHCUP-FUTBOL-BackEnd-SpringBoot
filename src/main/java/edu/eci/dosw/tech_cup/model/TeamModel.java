package edu.eci.dosw.tech_cup.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Representa un equipo dentro del torneo.
 *
 * Esta clase encapsula la información del equipo, incluyendo sus jugadores,
 * capitán y estado, así como la validación de reglas para su participación
 * en el torneo.
 */
public class TeamModel {

    private static final int MAX_PLAYERS = 12;
    private static final int MIN_PLAYERS = 6;

    /** Identificador único del equipo */
    private Long id;

    /** Nombre del equipo */
    private String name;

    /** Color representativo del equipo */
    private String color;

    /** Logo del equipo */
    private String logo;

    /** Capitán del equipo */
    private PlayerModel captain;

    /** Lista de jugadores del equipo */
    private List<PlayerModel> players;

    /** Estado del equipo */
    private TeamStatusModel status;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }

    public PlayerModel getCaptain() { return captain; }
    public void setCaptain(PlayerModel captain) { this.captain = captain; }

    public List<PlayerModel> getPlayers() {
        return players == null ? new ArrayList<>() : players;
    }

    public TeamStatusModel getStatus() { return status; }
    public void setStatus(TeamStatusModel status) { this.status = status; }

    public void addPlayer(PlayerModel player) {
        if (players == null) players = new ArrayList<>();
        if (player != null && !players.contains(player)) {
            players.add(player);
        }
    }

    public void removePlayer(PlayerModel player) {
        if (players != null) players.remove(player);
    }

    public boolean isFull() {
        return players != null && players.size() >= MAX_PLAYERS;
    }

    public boolean hasMinimumPlayers() {
        return players != null && players.size() >= MIN_PLAYERS;
    }

    public boolean validateTeamComposition() {
        return hasValidComposition() && isCaptainInTeam();
    }

    public boolean isPlayerEligible(PlayerModel player) {
        return player != null && player.isValid();
    }

    public boolean isPlayerAvailable(PlayerModel player) {
        return player != null && player.isAvailable();
    }

    public boolean hasValidComposition() {
        return false;
    }

    public boolean isCaptainInTeam() {
        return captain != null && players != null && players.contains(captain);
    }

    public boolean canModifyRoster() {
        return false;
    }

    public boolean canParticipate() {
        return false;
    }
}