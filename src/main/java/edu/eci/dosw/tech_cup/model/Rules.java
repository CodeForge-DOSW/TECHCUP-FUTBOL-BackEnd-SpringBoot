public class Rules {

    private Long id;
    private Tournament tournament;
    private String description;

    public Rules() {}

    public Rules(Long id, Tournament tournament, String description) {
        this.id = id;
        this.tournament = tournament;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return "Rules{id=" + id + ", description='" + description + "'}";
    }
}
