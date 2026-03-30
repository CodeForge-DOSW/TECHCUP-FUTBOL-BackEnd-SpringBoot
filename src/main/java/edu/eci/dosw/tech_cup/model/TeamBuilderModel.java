package edu.eci.dosw.tech_cup.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Builds {@link TeamModel} instances using the Builder pattern.
 *
 * <p>This helper allows a team to be configured step by step before creating
 * the final model instance.</p>
 */
public class TeamBuilderModel {

    /** Team name. */
    private String name;

    /** Team color. */
    private String color;

    /** Team logo. */
    private String logo;

    /** Team captain. */
    private PlayerModel captain;

    /** Player list. */
    private List<PlayerModel> players;

    /** Team status. */
    private TeamStatusModel status;



    /**
     * Sets the team name.
     *
     * @param name team name
     * @return current builder instance
     */
    public TeamBuilderModel setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the team color.
     *
     * @param color team color
     * @return current builder instance
     */
    public TeamBuilderModel setColor(String color) {
        this.color = color;
        return this;
    }

    /**
     * Sets the team logo.
     *
     * @param logo team logo
     * @return current builder instance
     */
    public TeamBuilderModel setLogo(String logo) {
        this.logo = logo;
        return this;
    }

    /**
     * Sets the team captain.
     *
     * @param captain captain player
     * @return current builder instance
     */
    public TeamBuilderModel setCaptain(PlayerModel captain) {
        this.captain = captain;
        return this;
    }

    /**
     * Adds a player to the team being built.
     *
     * @param player player to add
     * @return current builder instance
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
     * Sets the team status.
     *
     * @param status team status
     * @return current builder instance
     */
    public TeamBuilderModel setStatus(TeamStatusModel status) {
        this.status = status;
        return this;
    }

    /**
     * Builds the final {@link TeamModel} instance.
     *
     * @return constructed team
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
