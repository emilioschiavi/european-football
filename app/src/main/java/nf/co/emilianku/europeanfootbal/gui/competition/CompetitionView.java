package nf.co.emilianku.europeanfootbal.gui.competition;

import nf.co.emilianku.europeanfootbal.App;

/**
 * Created by emilio on 01.05.17.
 */

public interface CompetitionView {

    App getApp();

    void showCompetitionCaption(String currentCompetition);

    void showCurrentMatchday(int currentMatchday);

    void showNumberOfMatchdays(int numberOfMatchdays);

    void showNumberOfTeams(int numberOfTeams);

    void showNumberOfGames(int numberOfGames);
}
