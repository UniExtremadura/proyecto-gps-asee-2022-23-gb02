package es.unex.propuesta_proyecto.model;

import android.app.Application;
import es.unex.propuesta_proyecto.api.AppContainer;

public class MyApplication extends Application {

    public AppContainer appContainer;

    @Override
    public void onCreate() {
        super.onCreate();
        appContainer = new AppContainer(this);
    }
}
