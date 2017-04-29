package nf.co.emilianku.europeanfootbal;

import android.app.Application;

import dagger.ObjectGraph;

/**
 * Created by emilio on 23.04.17.
 */

public class App extends Application {
    private ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(new Object[] {new AppModule()});
    }

    public ObjectGraph getObjectGraph() {
        return objectGraph;
    }
}