import java.util.ArrayList;
import java.util.List;

public class Field {

    private Long id;
    private String name;
    private String location;
    private List<Match> matches = new ArrayList<>();

    public Field() {}

    public Field(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public List<Match> getMatches() { return matches; }
    public void setMatches(List<Match> matches) { this.matches = matches; }

    @Override
    public String toString() {
        return "Field{id=" + id + ", name='" + name + "', location='" + location + "'}";
    }
}
