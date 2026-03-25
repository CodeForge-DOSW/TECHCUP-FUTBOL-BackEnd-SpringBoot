package edu.eci.dosw.tech_cup.model;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Representa la alineación de un equipo para un partido.
 *
 * Esta clase permite gestionar los jugadores titulares y suplentes,
 * así como la asignación de posiciones en el campo, validando que
 * la formación cumpla con las reglas del sistema.
 */
public class LineupModel {

    private static final int STARTERS_REQUIRED = 6;
    private static final int MAX_SUBSTITUTES = 6;

    /** Identificador único de la alineación */
    private Long id;

    /** Equipo al que pertenece la alineación */
    private TeamModel team;

    /** Partido en el que se utiliza la alineación */
    private MatchModel match;

    /** Jugadores titulares */
    private List<PlayerModel> starters;

    /** Jugadores suplentes */
    private List<PlayerModel> substitutes;

    /** Asignación de posiciones a jugadores */
    private Map<PlayerModel, PositionModel> assignments;

    /** Tipo de formación táctica */
    private FormationTypeModel formation;

    /**
     * Verifica si la alineación es válida.
     *
     * @return true si cumple todas las reglas
     */
    public boolean isValidLineup() {
        return false;
    }

    /**
     * Verifica si tiene la cantidad válida de jugadores titulares.
     *
     * @return true si cumple la cantidad requerida
     */
    public boolean hasValidStarters() { return false; }

    /**
     * Verifica si existe un portero en la alineación.
     *
     * @return true si hay portero
     */
    public boolean hasValidGoalkeeper() {
        return false;
    }

    /**
     * Verifica si la formación es válida.
     *
     * @return true si la formación es correcta
     */
    public boolean validateFormation() {
        return false;
    }

    /**
     * Verifica que no haya jugadores duplicados.
     *
     * @return true si no hay duplicados
     */
    public boolean hasNoDuplicates() {
        return false;
    }

    /**
     * Verifica si los suplentes son válidos.
     *
     * @return true si cumplen las reglas
     */
    public boolean hasValidSubstitutes() { return false; }

    /**
     * Agrega un jugador titular.
     *
     * @param player jugador a agregar
     */
    public void addStarter(PlayerModel player) {

    }

    /**
     * Agrega un jugador suplente.
     *
     * @param player jugador a agregar
     */
    public void addSubstitute(PlayerModel player) {

    }

    /**
     * Asigna una posición a un jugador.
     *
     * @param player jugador
     * @param position posición asignada
     */
    public void assignPosition(PlayerModel player, PositionModel position) {

    }

    /**
     * Elimina un jugador titular.
     *
     * @param player jugador a eliminar
     */
    public void removeStarter(PlayerModel player) {

    }

    /**
     * Elimina un jugador suplente.
     *
     * @param player jugador a eliminar
     */
    public void removeSubstitute(PlayerModel player) {

    }

    /**
     * Verifica si un jugador está en la alineación.
     *
     * @param player jugador a verificar
     * @return true si está en titulares o suplentes
     */
    public boolean isPlayerInLineup(PlayerModel player) {
        return false;
    }

    /**
     * Verifica si la alineación puede ser modificada.
     *
     * @return true si es editable
     */
    public boolean isEditable() {
       return false;
    }
}
