package edu.eci.dosw.tech_cup.model;

public class Referee extends User {

    public Referee() {}

    public Referee(Long id, String name, String email, String password, Boolean status) {
        super(id, name, email, password, status);
    }

    public void registerMatchResults(Match match, int scoreHome, int scoreAway) {
        if (match == null) {
            throw new IllegalArgumentException("Match cannot be null");
        }
        match.setScoreHome(scoreHome);
        match.setScoreAway(scoreAway);
    }
}
