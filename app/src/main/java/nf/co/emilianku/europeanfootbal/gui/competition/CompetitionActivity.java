package nf.co.emilianku.europeanfootbal.gui.competition;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import nf.co.emilianku.europeanfootbal.App;
import nf.co.emilianku.europeanfootbal.R;
import nf.co.emilianku.europeanfootbal.gui.BaseActivity;
import nf.co.emilianku.europeanfootbal.gui.leaguetable.LeagueTableActivity;

import static android.content.Intent.EXTRA_TITLE;

public class CompetitionActivity extends BaseActivity implements CompetitionView {

    public final static String EXTRA_COMPETITION_ID = "EXTRA_COMPETITION_ID";
    public static final String EXTRA_URL = "EXTRA_URL";

    @Inject
    CompetitionPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getObjectGraph().plus(new CompetitionModule(this)).inject(this);

        Intent intent = getIntent();
        assert intent.hasExtra(EXTRA_COMPETITION_ID);
        assert intent.hasExtra(EXTRA_TITLE);
        setTitle(intent.getStringExtra(EXTRA_TITLE));
        int requestedId = intent.getIntExtra(EXTRA_COMPETITION_ID, 0);
        presenter.createData(requestedId);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_competition;
    }

    @Override
    public App getApp() {
        return (App) getApplication();
    }

    @Override
    public void showCompetitionCaption(String competitionCaption) {
        ((TextView)findViewById(R.id.tvCompetitionCaption)).setText(competitionCaption);
    }

    @Override
    public void showCurrentMatchday(int currentMatchday) {
        ((TextView)findViewById(R.id.tvCurrentMatchday)).setText(String.valueOf(currentMatchday));
    }

    @Override
    public void showNumberOfMatchdays(int numberOfMatchdays) {
        ((TextView)findViewById(R.id.tvNumberOfMatchdays)).setText(String.valueOf(numberOfMatchdays));
    }

    @Override
    public void showNumberOfTeams(int numberOfTeams) {
        ((TextView)findViewById(R.id.tvNumberOfTeams)).setText(String.valueOf(numberOfTeams));
    }

    @Override
    public void showNumberOfGames(int numberOfGames) {
        ((TextView)findViewById(R.id.tvNumberOfGames)).setText(String.valueOf(numberOfGames));
    }

    @Override
    public void navigateTo(String url) {
        Intent intent = new Intent(this, LeagueTableActivity.class);
        intent.putExtra(EXTRA_URL, url);
        intent.putExtra(EXTRA_TITLE, getTitle());
        startActivity(intent);
    }

    public void goToTeams(View view) {
        Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_SHORT).show();
    }

    public void goToLeagueTable(View view) {
        Intent intent = getIntent();
        assert intent.hasExtra(EXTRA_COMPETITION_ID);
        int requestedId = intent.getIntExtra(EXTRA_COMPETITION_ID, 0);
        presenter.goToLeagueTable(requestedId);
    }

    public void goToResults(View view) {
        Toast.makeText(this, R.string.not_implemented, Toast.LENGTH_SHORT).show();
    }
}
