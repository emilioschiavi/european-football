package nf.co.emilianku.europeanfootbal.gui.competitions;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import nf.co.emilianku.domain.communication.DataProvider;
import nf.co.emilianku.domain.events.HttpResponseFailure;
import nf.co.emilianku.domain.events.HttpResponseOk;
import nf.co.emilianku.domain.model.Competition;
import nf.co.emilianku.domain.processors.CompetitionsProcessor;

/**
 * Created by emilio on 23.04.17.
 */

public class CompetitionsPresenter {

    public static final String HTTP_API_FOOTBALL_DATA_ORG_V1_COMPETITIONS = "http://api.football-data.org/v1/competitions/";
    private final CompetitionsView view;

    @Inject DataProvider dataProvider;

    public CompetitionsPresenter(CompetitionsView view){
        this.view = view;
        view.getApp().getObjectGraph().inject(this);
    }

    public void onStart() {
        EventBus.getDefault().register(this);

        if (!dataProvider.isRunning()) {
            List<Competition> competitions = dataProvider.dataContainer.getCompetitions();
            if (competitions.size() == 0) {
                sendRequest();
            }
            else {
                view.hideWaitDialog();
                createNewPage(competitions);
            }
        }
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(HttpResponseOk response) {
        view.hideWaitDialog();
        createNewPage(dataProvider.dataContainer.getCompetitions());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(HttpResponseFailure response) {
        view.hideWaitDialog();
        view.showResponseFailure();
    }

    private void createNewPage(List<Competition> competitions) {
        view.beginPage();
        for (Competition competition : competitions) {
            view.addRow(competition);
        }

        view.endPage();
    }

    public void sendRequest() {
        Log.d(this.getClass().getName(), "sending request");
        dataProvider.sendRequest(HTTP_API_FOOTBALL_DATA_ORG_V1_COMPETITIONS,
                new CompetitionsProcessor());
    }

    public void onResume() {
        Log.d(this.getClass().getName(), "onResume");
        if (dataProvider.isRunning()) {
            view.showWaitDialog();
        }
    }
}
