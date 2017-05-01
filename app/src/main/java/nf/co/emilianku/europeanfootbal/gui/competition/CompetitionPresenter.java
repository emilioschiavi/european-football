package nf.co.emilianku.europeanfootbal.gui.competition;

import javax.inject.Inject;

import nf.co.emilianku.domain.communication.DataProvider;
import nf.co.emilianku.domain.model.Competition;

/**
 * Created by emilio on 01.05.17.
 */

public class CompetitionPresenter {

    private final CompetitionView view;

    @Inject
    DataProvider dataProvider;

    public CompetitionPresenter(CompetitionView view){
        this.view = view;
        view.getApp().getObjectGraph().inject(this);
    }

    public void createData(int requestedId) {
        Competition competition = dataProvider.dataContainer.getCompetition(requestedId);
        assert competition != null;

        view.showCompetitionCaption(competition.getCaption());
        view.showCurrentMatchday(competition.getCurrentMatchday());
        view.showNumberOfGames(competition.getNumberOfGames());
        view.showNumberOfMatchdays(competition.getNumberOfMatchdays());
        view.showNumberOfTeams(competition.getNumberOfTeams());
    }
}
