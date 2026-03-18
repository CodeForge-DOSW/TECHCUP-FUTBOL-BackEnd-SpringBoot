import java.time.LocalDate;

public class TeamInvitation {

    private Long id;
    private Team team;
    private User player;
    private InvitationStatus status;
    private LocalDate sentDate;

    public TeamInvitation() {}

    public TeamInvitation(Long id, Team team, User player, InvitationStatus status, LocalDate sentDate) {
        this.id = id;
        this.team = team;
        this.player = player;
        this.status = status;
        this.sentDate = sentDate;
    }

    public void accept() {
        if (this.status != InvitationStatus.PENDING) {
            throw new IllegalStateException("Only PENDING invitations can be accepted.");
        }
        this.status = InvitationStatus.ACCEPTED;
        if (team != null && player != null) {
            team.addPlayer(player);
        }
    }

    public void reject() {
        if (this.status != InvitationStatus.PENDING) {
            throw new IllegalStateException("Only PENDING invitations can be rejected.");
        }
        this.status = InvitationStatus.REJECTED;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Team getTeam() { return team; }
    public void setTeam(Team team) { this.team = team; }
    public User getPlayer() { return player; }
    public void setPlayer(User player) { this.player = player; }
    public InvitationStatus getStatus() { return status; }
    public void setStatus(InvitationStatus status) { this.status = status; }
    public LocalDate getSentDate() { return sentDate; }
    public void setSentDate(LocalDate sentDate) { this.sentDate = sentDate; }

    @Override
    public String toString() {
        return "TeamInvitation{id=" + id + ", player=" + (player != null ? player.getEmail() : null) + ", status=" + status + "}";
    }
}