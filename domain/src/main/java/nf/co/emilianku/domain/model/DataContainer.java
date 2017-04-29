package nf.co.emilianku.domain.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by emilio on 23.04.17.
 */

public class DataContainer {

    private List<Competition> competitions;

    public DataContainer() {
        competitions = new ArrayList<>();
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void addCompetition(Competition competition) {
        competitions.add(competition);
    }

    public void clearCompetitions() {
        competitions.clear();
    }
}
