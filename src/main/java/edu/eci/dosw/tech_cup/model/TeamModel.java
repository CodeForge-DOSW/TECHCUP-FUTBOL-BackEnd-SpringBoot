package edu.eci.dosw.tech_cup.model;

import java.util.List;

/**
 * Representa un equipo dentro del torneo.
 *
 * Esta clase encapsula la información del equipo, incluyendo sus jugadores,
 * capitán y estado, así como la validación de reglas para su participación
 * en el torneo.
 */
public class TeamModel {

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

    // ===================== MÉTODOS =====================

    /**
     * Obtiene el identificador del equipo.
     *
     * @return id del equipo
     */
    public Long getId() { return null; }

    /**
     * Obtiene el nombre del equipo.
     *
     * @return nombre del equipo
     */
    public String getName() { return null; }

    /**
     * Obtiene el capitán del equipo.
     *
     * @return capitán
     */
    public PlayerModel getCaptain() { return null; }

    /**
     * Obtiene la lista de jugadores.
     *
     * @return lista de jugadores
     */
    public List<PlayerModel> getPlayers() { return null; }

    /**
     * Agrega un jugador al equipo.
     *
     * @param player jugador a agregar
     */
    public void addPlayer(PlayerModel player) {}

    /**
     * Elimina un jugador del equipo.
     *
     * @param player jugador a eliminar
     */
    public void removePlayer(PlayerModel player) {}

    /**
     * Verifica si el equipo está completo.
     *
     * @return true si tiene el número máximo de jugadores
     */
    public boolean isFull() { return false; }

    /**
     * Verifica si el equipo cumple con el número mínimo de jugadores.
     *
     * @return true si cumple el mínimo requerido
     */
    public boolean hasMinimumPlayers() { return false; }

    /**
     * Valida la composición del equipo.
     *
     * @return true si cumple reglas del torneo
     */
    public boolean validateTeamComposition() { return false; }

    /**
     * Verifica si un jugador es elegible para el equipo.
     *
     * @param player jugador a validar
     * @return true si puede unirse
     */
    public boolean isPlayerEligible(PlayerModel player) { return false; }

    /**
     * Verifica si un jugador está disponible.
     *
     * @param player jugador a validar
     * @return true si está disponible
     */
    public boolean isPlayerAvailable(PlayerModel player) { return false; }

    /**
     * Verifica si la composición del equipo es válida.
     *
     * @return true si cumple todas las reglas
     */
    public boolean hasValidComposition() { return false; }

    /**
     * Verifica si el capitán es válido.
     *
     * @return true si el capitán pertenece al equipo
     */
    public boolean isCaptainInTeam() { return false; }

    /**
     * Verifica si se puede modificar la plantilla del equipo.
     *
     * @return true si es editable
     */
    public boolean canModifyRoster() { return false; }

    /**
     * Verifica si el equipo puede participar en el torneo.
     *
     * @return true si cumple todos los requisitos
     */
    public boolean canParticipate() { return false; }
}
