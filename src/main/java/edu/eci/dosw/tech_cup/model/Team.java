package edu.eci.dosw.tech_cup.model;

import java.util.ArrayList;
import java.util.List;

public class Team {

    private static final int MIN_PLAYERS = 7;
    private static final int MAX_PLAYERS = 12;

    private Long id;
    private String name;
    private String color;
    private String logo;
    private Tournament tournament;
    private List<Player> players;
    private Player captain;
    private TeamStatus status;

    public Team(String name, String color, String logo, Tournament tournament, Player captain) {
        this.name       = name;
        this.color      = color;
        this.logo       = logo;
        this.tournament = tournament;
        this.captain    = captain;
        this.players    = new ArrayList<>();
        this.status     = TeamStatus.PENDING;

        // El capitán es automáticamente el primer jugador del equipo
        this.players.add(captain);
    }



    public Long getId() { return id; }

    public String getName() { return name; }

    public TeamStatus getStatus() { return status; }

    public List<Player> getPlayers() { return players; }

    public Player getCaption() { return captain; }



    public void addPlayer(Player player) {
        if (players.size() >= MAX_PLAYERS) {
            throw new IllegalStateException(
                    "El equipo ya tiene el máximo de " + MAX_PLAYERS + " jugadores."
            );
        }
        if (players.contains(player)) {
            throw new IllegalArgumentException(
                    "El jugador ya pertenece a este equipo."
            );
        }
        players.add(player);
    }

    public void removePlayer(Player player) {
        if (player.equals(captain)) {
            throw new IllegalArgumentException(
                    "No se puede eliminar al capitán del equipo."
            );
        }
        if (!players.contains(player)) {
            throw new IllegalArgumentException(
                    "El jugador no pertenece a este equipo."
            );
        }
        players.remove(player);
    }

    public boolean isReadyToPlay() {
        return players.size() >= MIN_PLAYERS;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }

    public void changeCaptain(Player newCaptain) {
        if (!players.contains(newCaptain)) {
            throw new IllegalArgumentException(
                    "El nuevo capitán debe ser un jugador del equipo."
            );
        }
        this.captain = newCaptain;
    }



    // ─── Getters y Setters ────────────────────────────────────────────────────

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getLogo() { return logo; }
    public void setLogo(String logo) { this.logo = logo; }
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public void setPlayers(List<Player> players) { this.players = players; }
    public Player getCaptain() { return captain; }
    public void setCaptain(Player captain) { this.captain = captain; }
    public void setStatus(TeamStatus status) { this.status = status; }
}
