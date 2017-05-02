package nf.co.emilianku.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by emilio on 23.04.17.
 */

public class DataContainer {

    private HashMap<Integer, Competition> competitions;

    private HashMap<String, List<LeagueTableEntry>> leagueTables;

    public DataContainer() {
        competitions = new HashMap<>();
        leagueTables = new HashMap<>();
    }

    public Collection<Competition> getCompetitions() {
        return competitions.values();
    }

    public void addCompetition(Competition competition) {
        competitions.put(competition.getId(), competition);
    }

    public Competition getCompetition(int id) {
        return competitions.get(id);
    }

    public void clearCompetitions() {
        competitions.clear();
    }

    public List<LeagueTableEntry> getLeagueTable(String url) {
        if (leagueTables.get(url) == null) {
            leagueTables.put(url, new ArrayList<LeagueTableEntry>());
        }

        return leagueTables.get(url);
    }

    public void addLeagueTableEntry(String url, LeagueTableEntry entry) {
        getLeagueTable(url).add(entry);
    }
}
