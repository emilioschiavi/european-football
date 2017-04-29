package nf.co.emilianku.europeanfootbal.gui.competitions;

import nf.co.emilianku.domain.model.Competition;
import nf.co.emilianku.europeanfootbal.App;

/**
 * Created by emilio on 23.04.17.
 */

public interface CompetitionsView {

    App getApp();

    void showWaitDialog();

    void hideWaitDialog();

    void beginPage();

    void addRow(Competition competition);

    void endPage();

    void showResponseFailure();
}
