
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String identification;
    private Integer age;
    private String gender;
    private String photo;
    private UserType userType;
    private Integer semester;
    private LocalDate registrationDate;
    private Boolean status;

    private List<UserRole> userRoles = new ArrayList<>();
    private SportsProfile sportsProfile;
    private List<PlayerPosition> playerPositions = new ArrayList<>();

    public User() {}

    public User(Long id, String firstName, String lastName, String email,
                String password, String identification, Integer age,
                String gender, String photo, UserType userType,
                Integer semester, LocalDate registrationDate, Boolean status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.identification = identification;
        this.age = age;
        this.gender = gender;
        this.photo = photo;
        this.userType = userType;
        this.semester = semester;
        this.registrationDate = registrationDate;
        this.status = status;
    }

    public String getFullName() { return firstName + " " + lastName; }
    public boolean isActive() { return Boolean.TRUE.equals(status); }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getIdentification() { return identification; }
    public void setIdentification(String identification) { this.identification = identification; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getPhoto() { return photo; }
    public void setPhoto(String photo) { this.photo = photo; }
    public UserType getUserType() { return userType; }
    public void setUserType(UserType userType) { this.userType = userType; }
    public Integer getSemester() { return semester; }
    public void setSemester(Integer semester) { this.semester = semester; }
    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
    public List<UserRole> getUserRoles() { return userRoles; }
    public void setUserRoles(List<UserRole> userRoles) { this.userRoles = userRoles; }
    public SportsProfile getSportsProfile() { return sportsProfile; }
    public void setSportsProfile(SportsProfile sportsProfile) { this.sportsProfile = sportsProfile; }
    public List<PlayerPosition> getPlayerPositions() { return playerPositions; }
    public void setPlayerPositions(List<PlayerPosition> playerPositions) { this.playerPositions = playerPositions; }

    @Override
    public String toString() {
        return "User{id=" + id + ", fullName='" + getFullName() + "', email='" + email + "', userType=" + userType + "}";
    }
}