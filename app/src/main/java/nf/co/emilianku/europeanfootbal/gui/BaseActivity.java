package nf.co.emilianku.europeanfootbal.gui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;

import nf.co.emilianku.europeanfootbal.R;

/**
 * Created by emilio on 25.04.17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private MaterialDialog waitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
    }

    protected abstract int getLayoutResourceId();

    protected void showWaitDialogImpl() {
        Log.d(this.getClass().getName(), "showWaitDialog called");
        if (waitDialog == null)
            waitDialog = new MaterialDialog.Builder(this)
                    .title(R.string.app_loading_page)
                    .content(R.string.app_please_wait)
                    .canceledOnTouchOutside(false)
                    .progress(true, 0)
                    .show();
    }

    protected void hideWaitDialogImpl() {
        Log.d(this.getClass().getName(), "hideWaitDialog called");
        if (waitDialog != null) {
            waitDialog.dismiss();
            waitDialog = null;
        }
    }
}
