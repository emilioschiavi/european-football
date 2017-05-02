package nf.co.emilianku.domain.model;

/**
 * Created by emilio on 02.05.17.
 */

public class LeagueTableEntry {

    private final String teamName;

    private final int points;

    public LeagueTableEntry(String teamName, int points) {
        this.teamName = teamName;
        this.points = points;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPoints() {
        return points;
    }
}
