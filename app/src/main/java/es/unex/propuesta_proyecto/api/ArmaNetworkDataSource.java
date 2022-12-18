package es.unex.propuesta_proyecto.api;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import es.unex.propuesta_proyecto.model.Armas;

public class ArmaNetworkDataSource {
    private static final String LOG_TAG = ArmaNetworkDataSource.class.getSimpleName();
    private static ArmaNetworkDataSource sInstance;

    private final MutableLiveData<Armas[]> nDowloadedArmas;

    private ArmaNetworkDataSource(){nDowloadedArmas = new MutableLiveData<>();}

    public synchronized static ArmaNetworkDataSource getInstance(){
        Log.d(LOG_TAG, "Getting the network data source");
        if(sInstance == null){
            sInstance = new ArmaNetworkDataSource();
            Log.d(LOG_TAG, "Made new network dat source");
        }
        return sInstance;
    }

    public LiveData<Armas[]> getCurrentArma(){
        return nDowloadedArmas;
    }

    public void fetchArmas(int cod){
        Log.d(LOG_TAG, "Fetch armas started");
        AppExecutors.getInstance().networkIO().execute(new ReposNetworkLoaderRunnable(cod, repoArmas -> nDowloadedArmas.postValue(repoArmas.toArray(new Armas[0]))));
    }

}
