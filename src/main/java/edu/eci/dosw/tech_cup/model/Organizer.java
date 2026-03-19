package edu.eci.dosw.tech_cup.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Organizer extends User {

    public Organizer() {}

    public Organizer(Long id, String name, String email, String password, Boolean status) {
        super(id, name, email, password, status);
    }

    public Tournament createTournament(String name, LocalDateTime startDate,
                                       LocalDateTime endDate, int maxTeams,
                                       BigDecimal teamCost) {
        Tournament tournament = new Tournament();
        tournament.setName(name);
        tournament.setStartDate(startDate);
        tournament.setEndDate(endDate);
        tournament.setMaxOfTeams(maxTeams);
        tournament.setTeamCost(teamCost);
        return tournament;
    }
}
