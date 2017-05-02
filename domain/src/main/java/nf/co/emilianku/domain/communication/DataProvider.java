package nf.co.emilianku.domain.communication;

import android.os.AsyncTask;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import nf.co.emilianku.domain.events.HttpResponseFailure;
import nf.co.emilianku.domain.events.HttpResponseOk;
import nf.co.emilianku.domain.model.DataContainer;
import nf.co.emilianku.domain.processors.ResponseProcessor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by emilio on 23.04.17.
 */

public class DataProvider {

    public boolean isRunning() {
        return running;
    }

    private boolean running = false;
    public final DataContainer dataContainer;

    public DataProvider() {
        Log.d(this.getClass().getName(), "ctor");
        dataContainer = new DataContainer();
    }

    public void sendRequest(String url, final ResponseProcessor processor) {
        AsyncTask<String, Integer, String[]> task = new AsyncTask<String, Integer, String[]>() {

            @Override
            protected void onPostExecute(String[] args) {
                super.onPostExecute(args);
                running = false;
                boolean result = processor.process(dataContainer, args[0], args[1]);
                if (result) {
                    EventBus.getDefault().post(new HttpResponseOk());
                }
                else {
                    EventBus.getDefault().post(new HttpResponseFailure());
                }
            }

            @Override
            protected String[] doInBackground(String... params) {
                return getResponse(params[0]);
            }
        };

        running = true;
        task.execute(url);
    }

    private String[] getResponse(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return new String[] {url, response.body().string()};
        }
        catch(Exception exception) {
            return null;
        }
    }
}
