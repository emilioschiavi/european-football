package nf.co.emilianku.europeanfootbal.gui.competitions.viewmodels;

import nf.co.emilianku.domain.model.Competition;

/**
 * Created by emilio on 25.04.17.
 */

public class CompetitionViewModel {

    private final Competition competition;

    public CompetitionViewModel(Competition competition) {
        this.competition = competition;
    }

    public int getId() {
        return competition.getId();
    }

    public String getCaption() {
        return competition.getCaption();
    }
}
