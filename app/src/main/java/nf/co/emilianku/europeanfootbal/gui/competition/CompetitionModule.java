package nf.co.emilianku.europeanfootbal.gui.competition;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emilio on 01.05.17.
 */

@Module(library = false, injects = {CompetitionActivity.class}, complete = true)
public class CompetitionModule
{
    private final CompetitionView view;

    public CompetitionModule(CompetitionView view){
        this.view = view;
    }

    @Provides
    public CompetitionPresenter providesPresenter() {

        return new CompetitionPresenter(view);
    }
}