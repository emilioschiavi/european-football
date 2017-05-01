package nf.co.emilianku.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created by emilio on 23.04.17.
 */

public class DataContainer {

    HashMap<Integer, Competition> competitions;

    public DataContainer() {
        competitions = new HashMap<>();
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
}
