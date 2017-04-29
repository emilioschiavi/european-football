package nf.co.emilianku.europeanfootbal.gui.competitions;

import dagger.Module;
import dagger.Provides;

/**
 * Created by emilio on 23.04.17.
 */

@Module(library = false, injects = {CompetitionsActivity.class}, complete = true)
public class CompetitionsModule
{
    private final CompetitionsView view;

    public CompetitionsModule(CompetitionsView view){
        this.view = view;
    }

    @Provides
    public CompetitionsPresenter providesPresenter() {

        return new CompetitionsPresenter(view);
    }
}