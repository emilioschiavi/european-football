package nf.co.emilianku.europeanfootbal;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import nf.co.emilianku.domain.communication.DataProvider;
import nf.co.emilianku.domain.model.DataContainer;
import nf.co.emilianku.europeanfootbal.gui.competition.CompetitionPresenter;
import nf.co.emilianku.europeanfootbal.gui.competitions.CompetitionsPresenter;

/**
 * Created by emilio on 23.04.17.
 */

@Module(library = false, injects = {CompetitionsPresenter.class, CompetitionPresenter.class}, complete = true)
public class AppModule {

    @Provides
    @Singleton
    public DataProvider providesDataProvider() {

        return new DataProvider();
    }
}