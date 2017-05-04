package nf.co.emilianku.europeanfootbal.gui;

import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.StreamEncoder;
import com.bumptech.glide.load.resource.file.FileToStreamDecoder;
import com.caverock.androidsvg.SVG;

import java.io.InputStream;
import java.util.Locale;

import nf.co.emilianku.europeanfootbal.R;

/**
 * Created by emilio on 25.04.17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private MaterialDialog waitDialog;

    private GenericRequestBuilder getRequestBuilder() {
        return requestBuilder;
    }

    private GenericRequestBuilder requestBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        createBuilder();
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

    private void createBuilder() {
        if (requestBuilder == null) {
            requestBuilder = Glide.with(this)
                    .using(Glide.buildStreamModelLoader(Uri.class, this), InputStream.class)
                    .from(Uri.class)
                    .as(SVG.class)
                    .transcode(new SvgDrawableTranscoder(), PictureDrawable.class)
                    .sourceEncoder(new StreamEncoder())
                    .cacheDecoder(new FileToStreamDecoder<SVG>(new SvgDecoder()))
                    .decoder(new SvgDecoder())
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .animate(android.R.anim.fade_in)
                    .listener(new SvgSoftwareLayerSetter<Uri>());
        }
    }

    public void loadImageIntoView(String url, ImageView imageView) {
        // get file extension do it yourself way...
        int index = url.lastIndexOf(".");
        if (index > 0) {
            String extension = url.substring(index).toUpperCase(Locale.ENGLISH);
            if (extension.compareTo(".SVG") == 0) {
                Uri uri = Uri.parse(url);
                getRequestBuilder().diskCacheStrategy(DiskCacheStrategy.SOURCE)
                        .load(uri)
                        .into(imageView);
            } else {
                Glide.with(this).load(url).dontAnimate().into(imageView);
            }
        }
        else {
            // Use placeholders
            getRequestBuilder().diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .load(Uri.parse(url))
                    .into(imageView);
        }
    }
}
