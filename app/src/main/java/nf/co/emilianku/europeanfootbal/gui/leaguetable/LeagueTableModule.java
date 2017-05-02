package nf.co.emilianku.europeanfootbal.gui.leaguetable;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emilio on 23.04.17.
 */

@Module(library = false, injects = {LeagueTableActivity.class}, complete = true)
public class LeagueTableModule
{
    private final LeagueTableView view;

    public LeagueTableModule(LeagueTableView view){
        this.view = view;
    }

    @Provides
    public LeagueTablePresenter providesPresenter() {

        return new LeagueTablePresenter(view);
    }
}