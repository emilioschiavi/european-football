package nf.co.emilianku.europeanfootbal.gui.leaguetable.viewmodels;

import nf.co.emilianku.domain.model.LeagueTableEntry;

/**
 * Created by emilio on 25.04.17.
 */

public class LeagueTableViewModel {

    private final LeagueTableEntry leagueTableEntry;

    public LeagueTableViewModel(LeagueTableEntry leagueTableEntry) {
        this.leagueTableEntry = leagueTableEntry;
    }

    public String getTeamName() {
        return leagueTableEntry.getTeamName();
    }

    public int getPoints() {
        return leagueTableEntry.getPoints();
    }

    public String getCrestURI() {
        return leagueTableEntry.getCrestURI();
    }
}
