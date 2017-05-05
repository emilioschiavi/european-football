package nf.co.emilianku.europeanfootbal.gui.leaguetable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import javax.inject.Inject;

import nf.co.emilianku.domain.model.LeagueTableEntry;
import nf.co.emilianku.europeanfootbal.App;
import nf.co.emilianku.europeanfootbal.R;
import nf.co.emilianku.europeanfootbal.gui.BaseActivity;
import nf.co.emilianku.europeanfootbal.gui.SimpleDividerItemDecoration;

import static android.content.Intent.EXTRA_TITLE;
import static nf.co.emilianku.europeanfootbal.gui.competition.CompetitionActivity.EXTRA_COMPETITION_ID;
import static nf.co.emilianku.europeanfootbal.gui.competition.CompetitionActivity.EXTRA_URL;

public class LeagueTableActivity extends BaseActivity implements LeagueTableView {

    @Inject
    LeagueTablePresenter presenter;

    private LeagueTableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).getObjectGraph().plus(new LeagueTableModule(this)).inject(this);

        // Lookup the recyclerview in activity layout
        RecyclerView rvLeagueTable = (RecyclerView) findViewById(R.id.rvLeagueTable);
        // Create adapter
        adapter = new LeagueTableAdapter(this);
        // Attach the adapter to the recyclerview to populate items
        rvLeagueTable.setAdapter(adapter);
        // Set layout manager to position the items
        rvLeagueTable.setLayoutManager(new LinearLayoutManager(this));
        // Add divider decoration
        rvLeagueTable.addItemDecoration(new SimpleDividerItemDecoration(this));

        /*
        adapter.setOnItemClickListener(new CompetitionsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(this.getClass().getName(), "selected position: " + position);
                int requestedId = adapter.getItem(position).getId();
                startCompetitionActivity(requestedId);
            }
        });
        */
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_league_table;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(this.getClass().getName(), "onStart");
        Intent intent = getIntent();
        assert intent.hasExtra(EXTRA_URL);
        assert intent.hasExtra(EXTRA_TITLE);
        setTitle(intent.getStringExtra(EXTRA_TITLE));
        String url = intent.getStringExtra(EXTRA_URL);
        presenter.onStart(url);
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(this.getClass().getName(), "onStop");
        presenter.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(this.getClass().getName(), "onPause");
        hideWaitDialog();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public App getApp() {
        return (App) getApplication();
    }

    @Override
    public void showWaitDialog() {
        showWaitDialogImpl();
    }

    @Override
    public void hideWaitDialog() {
        hideWaitDialogImpl();
    }

    @Override
    public void beginPage() {
        adapter.beginPage();
    }

    @Override
    public void addRow(LeagueTableEntry entry) {
        adapter.addLeagueTableEntry(entry);
    }

    @Override
    public void endPage() {
        adapter.endPage();
    }

    @Override
    public void showResponseFailure() {
        Toast.makeText(this, R.string.network_failure, Toast.LENGTH_SHORT).show();
    }
}
