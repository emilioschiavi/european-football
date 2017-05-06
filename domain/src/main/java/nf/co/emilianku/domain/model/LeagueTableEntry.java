package nf.co.emilianku.domain.model;

/**
 * Created by emilio on 02.05.17.
 */

public class LeagueTableEntry {

    private final int position;

    private final String teamName;

    private final int points;

    private final String crestURI;

    public LeagueTableEntry(int position, String teamName, int points, String crestURI) {

        this.position = position;
        this.teamName = teamName;
        this.points = points;
        this.crestURI = crestURI;
    }

    public int getPosition() {
        return position;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPoints() {
        return points;
    }

    public String getCrestURI() {
        return crestURI;
    }
}
