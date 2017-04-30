package nf.co.emilianku.europeanfootbal.gui.competitions;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import nf.co.emilianku.domain.model.Competition;
import nf.co.emilianku.europeanfootbal.App;
import nf.co.emilianku.europeanfootbal.R;
import nf.co.emilianku.europeanfootbal.gui.BaseActivity;

public class CompetitionsActivity extends BaseActivity implements CompetitionsView {

    @Inject
    CompetitionsPresenter presenter;

    private CompetitionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.available_competitions));
        Log.d(this.getClass().getName(), "onCreate");
        ((App) getApplication()).getObjectGraph().plus(new CompetitionsModule(this)).inject(this);

        // Lookup the recyclerview in activity layout
        RecyclerView rvCompetitions = (RecyclerView) findViewById(R.id.rvCompetitions);
        // Create adapter
        adapter = new CompetitionsAdapter(this);
        // Attach the adapter to the recyclerview to populate items
        rvCompetitions.setAdapter(adapter);
        // Set layout manager to position the items
        rvCompetitions.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnItemClickListener(new CompetitionsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d(this.getClass().getName(), "selected position: " + position);
            }
        });
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_competitions;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(this.getClass().getName(), "onStart");
        presenter.onStart();
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
    public void addRow(Competition competition) {
        adapter.addCompetition(competition);
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
