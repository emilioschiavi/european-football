package nf.co.emilianku.europeanfootbal.gui.leaguetable;

import nf.co.emilianku.domain.model.Competition;
import nf.co.emilianku.domain.model.LeagueTableEntry;
import nf.co.emilianku.europeanfootbal.App;

/**
 * Created by emilio on 23.04.17.
 */

public interface LeagueTableView {

    App getApp();

    void showWaitDialog();

    void hideWaitDialog();

    void beginPage();

    void addRow(LeagueTableEntry entry);

    void endPage();

    void showResponseFailure();
}
