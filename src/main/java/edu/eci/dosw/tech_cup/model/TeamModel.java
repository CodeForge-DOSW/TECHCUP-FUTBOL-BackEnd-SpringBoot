package edu.eci.dosw.tech_cup.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

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

    /**
     * Obtiene el identificador del equipo.
     *
     * @return id del equipo
     */
    public Long getId() { return id; }

    /**
     * Obtiene el nombre del equipo.
     *
     * @return nombre del equipo
     */
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    /**
     * Obtiene el capitán del equipo.
     *
     * @return capitán
     */
    public PlayerModel getCaptain() { return captain; }

    public void setCaptain(PlayerModel captain) { this.captain = captain; }

    /**
     * Obtiene la lista de jugadores.
     *
     * @return lista de jugadores
     */
    public List<PlayerModel> getPlayers() {
        return null;
    }

    public void setColor(String color) { this.color = color; }

    public void setLogo(String logo) { this.logo = logo; }

    public void setStatus(TeamStatusModel status) { this.status = status; }

    /**
     * Agrega un jugador al equipo.
     *
     * @param player jugador a agregar
     */
    public void addPlayer(PlayerModel player) {

    }

    /**
     * Elimina un jugador del equipo.
     *
     * @param player jugador a eliminar
     */
    public void removePlayer(PlayerModel player) {

    }

    /**
     * Verifica si el equipo está completo.
     *
     * @return true si tiene el número máximo de jugadores
     */
    public boolean isFull() { return players != null && players.size() >= MAX_PLAYERS; }

    /**
     * Verifica si el equipo cumple con el número mínimo de jugadores.
     *
     * @return true si cumple el mínimo requerido
     */
    public boolean hasMinimumPlayers() { return players != null && players.size() >= MIN_PLAYERS; }

    /**
     * Valida la composición del equipo.
     *
     * @return true si cumple reglas del torneo
     */
    public boolean validateTeamComposition() { return hasValidComposition() && isCaptainInTeam(); }

    /**
     * Verifica si un jugador es elegible para el equipo.
     *
     * @param player jugador a validar
     * @return true si puede unirse
     */
    public boolean isPlayerEligible(PlayerModel player) { return player != null && player.isValid(); }

    /**
     * Verifica si un jugador está disponible.
     *
     * @param player jugador a validar
     * @return true si está disponible
     */
    public boolean isPlayerAvailable(PlayerModel player) { return player != null && player.isAvailable(); }

    /**
     * Verifica si la composición del equipo es válida.
     *
     * @return true si cumple todas las reglas
     */
    public boolean hasValidComposition() {
        return false;
    }

    /**
     * Verifica si el capitán es válido.
     *
     * @return true si el capitán pertenece al equipo
     */
    public boolean isCaptainInTeam() { return captain != null && players != null && players.contains(captain); }

    /**
     * Verifica si se puede modificar la plantilla del equipo.
     *
     * @return true si es editable
     */
    public boolean canModifyRoster() { return false;}

    /**
     * Verifica si el equipo puede participar en el torneo.
     *
     * @return true si cumple todos los requisitos
     */
    public boolean canParticipate() {
        return false;
    }
}
