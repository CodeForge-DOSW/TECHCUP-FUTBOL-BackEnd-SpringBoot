package edu.eci.dosw.tech_cup.model;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a team within the tournament domain.
 *
 * <p>This model encapsulates team identity, captain, player roster, and status,
 * along with helper methods used to validate roster rules and participation conditions.</p>
 */
public class TeamModel {

    private static final int MAX_PLAYERS = 12;
    private static final int MIN_PLAYERS = 6;

    /** Unique identifier of the team. */
    private Long id;

    /** Team name. */
    private String name;

    /** Representative team color. */
    private String color;

    /** Team logo. */
    private String logo;

    /** Team captain. */
    private PlayerModel captain;

    /** List of players belonging to the team. */
    private List<PlayerModel> players;

    /** Current team status. */
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

    /**
     * Adds a player to the team if the player is not already present.
     *
     * @param player player to add
     */
    public void addPlayer(PlayerModel player) {
        if (players == null) players = new ArrayList<>();
        if (player != null && !players.contains(player)) {
            players.add(player);
        }
    }

    /**
     * Removes a player from the team roster.
     *
     * @param player player to remove
     */
    public void removePlayer(PlayerModel player) {
        if (players != null) players.remove(player);
    }

    /**
     * Indicates whether the team has reached the maximum roster size.
     *
     * @return {@code true} if the team is full
     */
    public boolean isFull() {
        return players != null && players.size() >= MAX_PLAYERS;
    }

    /**
     * Indicates whether the team has the minimum number of players required.
     *
     * @return {@code true} if the minimum roster size is satisfied
     */
    public boolean hasMinimumPlayers() {
        return players != null && players.size() >= MIN_PLAYERS;
    }

    /**
     * Validates the team composition according to roster and captain rules.
     *
     * @return {@code true} if the team composition is valid
     */
    public boolean validateTeamComposition() {
        return hasValidComposition() && isCaptainInTeam();
    }

    /**
     * Indicates whether a player satisfies the eligibility rules of the team.
     *
     * @param player player to evaluate
     * @return {@code true} if the player is eligible
     */
    public boolean isPlayerEligible(PlayerModel player) {
        return player != null && player.isValid();
    }

    /**
     * Indicates whether a player is currently available for team participation.
     *
     * @param player player to evaluate
     * @return {@code true} if the player is available
     */
    public boolean isPlayerAvailable(PlayerModel player) {
        return player != null && player.isAvailable();
    }

    /**
     * Indicates whether the roster composition is valid according to domain rules.
     *
     * @return {@code true} if the roster composition is valid
     */
    public boolean hasValidComposition() {
        return false;
    }

    /**
     * Indicates whether the assigned captain is included in the team roster.
     *
     * @return {@code true} if the captain belongs to the team player list
     */
    public boolean isCaptainInTeam() {
        return captain != null && players != null && players.contains(captain);
    }

    /**
     * Indicates whether the roster can still be modified.
     *
     * @return {@code true} if roster changes are allowed
     */
    public boolean canModifyRoster() {
        return false;
    }

    /**
     * Indicates whether the team can participate in the tournament.
     *
     * @return {@code true} if the team satisfies participation requirements
     */
    public boolean canParticipate() {
        return false;
    }
}
