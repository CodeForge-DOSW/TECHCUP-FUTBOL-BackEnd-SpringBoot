package edu.eci.dosw.tech_cup.model;

import java.util.List;

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

    // ===================== MÉTODOS =====================

    /**
     * Define el nombre del equipo.
     *
     * @param name nombre del equipo
     * @return instancia del builder
     */
    public TeamBuilderModel setName(String name) { return null; }

    /**
     * Define el color del equipo.
     *
     * @param color color del equipo
     * @return instancia del builder
     */
    public TeamBuilderModel setColor(String color) { return null; }

    /**
     * Define el logo del equipo.
     *
     * @param logo logo del equipo
     * @return instancia del builder
     */
    public TeamBuilderModel setLogo(String logo) { return null; }

    /**
     * Define el capitán del equipo.
     *
     * @param captain jugador capitán
     * @return instancia del builder
     */
    public TeamBuilderModel setCaptain(PlayerModel captain) { return null; }

    /**
     * Agrega un jugador al equipo.
     *
     * @param player jugador a agregar
     * @return instancia del builder
     */
    public TeamBuilderModel addPlayer(PlayerModel player) { return null; }

    /**
     * Define el estado del equipo.
     *
     * @param status estado del equipo
     * @return instancia del builder
     */
    public TeamBuilderModel setStatus(TeamStatusModel status) { return null; }

    /**
     * Construye la instancia final de TeamModel.
     *
     * @return equipo construido
     */
    public TeamModel build() { return null; }
}
