package edu.eci.dosw.tech_cup.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Permite construir instancias de TeamModel utilizando el patrón Builder.
 *
 * Esta clase facilita la creación de equipos paso a paso, permitiendo
 * configurar sus atributos antes de generar la instancia final.
 */
public class TeamBuilderModel {

    /** Nombre del equipo */
    private String name;

    /** Color del equipo */
    private String color;

    /** Logo del equipo */
    private String logo;

    /** Capitán del equipo */
    private PlayerModel captain;

    /** Lista de jugadores */
    private List<PlayerModel> players;

    /** Estado del equipo */
    private TeamStatusModel status;



    /**
     * Define el nombre del equipo.
     *
     * @param name nombre del equipo
     * @return instancia del builder
     */
    public TeamBuilderModel setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Define el color del equipo.
     *
     * @param color color del equipo
     * @return instancia del builder
     */
    public TeamBuilderModel setColor(String color) {
        this.color = color;
        return this;
    }

    /**
     * Define el logo del equipo.
     *
     * @param logo logo del equipo
     * @return instancia del builder
     */
    public TeamBuilderModel setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    /**
     * Define el capitán del equipo.
     *
     * @param captain jugador capitán
     * @return instancia del builder
     */
    public TeamBuilderModel setCaptain(PlayerModel captain) {
        this.captain = captain;
        return this;
    }

    /**
     * Agrega un jugador al equipo.
     *
     * @param player jugador a agregar
     * @return instancia del builder
     */
    public TeamBuilderModel addPlayer(PlayerModel player) {
        if (player == null) {
            return this;
        }
        if (players == null) {
            players = new ArrayList<>();
        }
        if (!players.contains(player)) {
            players.add(player);
        }
        return this;
    }

    /**
     * Define el estado del equipo.
     *
     * @param status estado del equipo
     * @return instancia del builder
     */
    public TeamBuilderModel setStatus(TeamStatusModel status) {
        this.status = status;
        return this;
    }

    /**
     * Construye la instancia final de TeamModel.
     *
     * @return equipo construido
     */
    public TeamModel build() {
        TeamModel team = new TeamModel();
        team.setName(name);
        team.setColor(color);
        team.setLogo(logo);
        team.setStatus(status == null ? TeamStatusModel.PENDING : status);
        if (players != null) {
            for (PlayerModel player : players) {
                team.addPlayer(player);
            }
        }
        if (captain != null) {
            team.addPlayer(captain);
            team.setCaptain(captain);
        }
        return team;
    }
}
