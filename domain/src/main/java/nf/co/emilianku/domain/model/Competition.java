package nf.co.emilianku.domain.model;

/**
 * Created by emilio on 25.04.17.
 */

public class Competition {

    private final String urlTeams;

    private final String urlFixtures;

    private final String urlLeagueTable;

    private final int id;

    private final String caption;

    private final int currentMatchday;

    private final int numberOfMatchdays;

    private final int numberOfTeams;

    private final int numberOfGames;

    public Competition(String urlTeams, String urlFixtures, String urlLeagueTable, int id, String caption, int currentMatchday, int numberOfMatchdays, int numberOfTeams, int numberOfGames) {
        this.urlTeams = urlTeams;
        this.urlFixtures = urlFixtures;
        this.urlLeagueTable = urlLeagueTable;
        this.id = id;
        this.caption = caption;
        this.currentMatchday = currentMatchday;
        this.numberOfMatchdays = numberOfMatchdays;
        this.numberOfTeams = numberOfTeams;
        this.numberOfGames = numberOfGames;
    }

    public String getUrlTeams() {
        return urlTeams;
    }

    public String getUrlFixtures() {
        return urlFixtures;
    }

    public String getUrlLeagueTable() {
        return urlLeagueTable;
    }

    public int getId() {
        return id;
    }

    public String getCaption() {
        return caption;
    }

    public int getCurrentMatchday() {
        return currentMatchday;
    }

    public int getNumberOfMatchdays() {
        return numberOfMatchdays;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }
}
