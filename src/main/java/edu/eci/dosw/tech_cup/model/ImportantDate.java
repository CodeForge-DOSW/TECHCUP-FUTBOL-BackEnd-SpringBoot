import java.time.LocalDate;

public class ImportantDate {

    private Long id;
    private Tournament tournament;
    private String title;
    private String description;
    private LocalDate date;

    public ImportantDate() {}

    public ImportantDate(Long id, Tournament tournament, String title, String description, LocalDate date) {
        this.id = id;
        this.tournament = tournament;
        this.title = title;
        this.description = description;
        this.date = date;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Tournament getTournament() { return tournament; }
    public void setTournament(Tournament tournament) { this.tournament = tournament; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    @Override
    public String toString() {
        return "ImportantDate{id=" + id + ", title='" + title + "', date=" + date + "}";
    }
}
