package nf.co.emilianku.europeanfootbal.gui.leaguetable;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import nf.co.emilianku.domain.communication.DataProvider;
import nf.co.emilianku.domain.events.HttpResponseFailure;
import nf.co.emilianku.domain.events.HttpResponseOk;
import nf.co.emilianku.domain.model.Competition;
import nf.co.emilianku.domain.model.LeagueTableEntry;
import nf.co.emilianku.domain.processors.CompetitionsProcessor;
import nf.co.emilianku.domain.processors.LeagueTableProcessor;
import nf.co.emilianku.europeanfootbal.gui.competitions.CompetitionsView;

/**
 * Created by emilio on 23.04.17.
 */

public class LeagueTablePresenter {

    private final LeagueTableView view;

    private String url;

    @Inject DataProvider dataProvider;

    public LeagueTablePresenter(LeagueTableView view){
        this.view = view;
        view.getApp().getObjectGraph().inject(this);
    }

    public void onStart(String url) {
        this.url = url;
        EventBus.getDefault().register(this);

        if (!dataProvider.isRunning()) {
            List<LeagueTableEntry> leagueTableEntries = dataProvider.dataContainer.getLeagueTable(url);
            if (leagueTableEntries.size() == 0) {
                sendRequest();
            }
            else {
                view.hideWaitDialog();
                createNewPage(leagueTableEntries);
            }
        }
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(HttpResponseOk response) {
        view.hideWaitDialog();
        createNewPage(dataProvider.dataContainer.getLeagueTable(url));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(HttpResponseFailure response) {
        view.hideWaitDialog();
        view.showResponseFailure();
    }

    private void createNewPage(List<LeagueTableEntry> leagueTableEntries) {
        view.beginPage();
        for (LeagueTableEntry leagueTableEntry : leagueTableEntries) {
            view.addRow(leagueTableEntry);
        }

        view.endPage();
    }

    public void sendRequest() {
        Log.d(this.getClass().getName(), "sending request");
        dataProvider.sendRequest(url,
                new LeagueTableProcessor());
    }

    public void onResume() {
        Log.d(this.getClass().getName(), "onResume");
        if (dataProvider.isRunning()) {
            view.showWaitDialog();
        }
    }
}
