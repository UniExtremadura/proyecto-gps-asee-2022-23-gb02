package es.unex.propuesta_proyecto.api;


import android.util.Log;

import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.unex.propuesta_proyecto.dao.DaoJuego;
import es.unex.propuesta_proyecto.model.Armas;
import es.unex.propuesta_proyecto.model.RepoArmas;

public class ArmasRepository {

    private static final String LOG_TAG = ArmasRepository.class.getSimpleName();

    private static ArmasRepository sInstance;
    private final DaoJuego nArmasDao;
    private final ArmaNetworkDataSource nArmaNetworkDataSource;
    private final AppExecutors nExecutors = AppExecutors.getInstance();
    private final MutableLiveData<String> userFilterLiveData = new MutableLiveData<>();
    private final Map<String, Long> lastUpdateTimeMillisMap = new HashMap<>();
    private static final long MIN_TIME_FROM_LAST_FETCH_MILLIS = 30000;

    private ArmasRepository(DaoJuego armasDao, ArmaNetworkDataSource armaNetworkDataSource){
        nArmasDao = armasDao;
        nArmaNetworkDataSource = armaNetworkDataSource;
        LiveData<Armas[]> networkData = nArmaNetworkDataSource.getCurrentArma();
        networkData.observeForever(newArmasFromNetwork -> {
            nExecutors.diskIO().execute(() -> {
                if(newArmasFromNetwork.length > 0){
                    nArmasDao.borrarArmaPorNombreyId(newArmasFromNetwork[0].getId(), newArmasFromNetwork[0].getUsuario());
                }
                nArmasDao.insertarArmas((Armas) Arrays.asList(newArmasFromNetwork));
                Log.d(LOG_TAG, "Nuevos valores inssertados en Room");
            });
        });
    }

    public synchronized  static ArmasRepository getInstance(DaoJuego dao, ArmaNetworkDataSource nds){
        Log.d(LOG_TAG, "Getting the repository");
        if(sInstance == null){
            sInstance = new ArmasRepository(dao, nds);
            Log.d(LOG_TAG, "Made new repository");
        }
        return sInstance;
    }

    public void setUsername(final int cod, String username){
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if(isFetchNeeded(username)){
                    doFetchArma(cod, username);
                }
            }
        });
    }

    public void doFetchArma(int id, String username){
        Log.d(LOG_TAG, "Fetching Repos from Github");
        AppExecutors.getInstance().diskIO().execute(() -> {
            nArmasDao.borrarArmaPorNombreyId(id, username);
            nArmaNetworkDataSource.fetchArmas(id);
            lastUpdateTimeMillisMap.put(username, System.currentTimeMillis());
        });
    }


    public LiveData<List<RepoArmas>> getCurrentArma() {
        return Transformations.switchMap(userFilterLiveData, new Function<String, LiveData<List<RepoArmas>>>() {
            @Override
            public LiveData<List<RepoArmas>> apply(String input) {
                return nArmasDao.obtenerArmasPorNombreUsuarioRep(input);
            }
        });
    }

    private boolean isFetchNeeded(String username) {
        Long lastFetchTimeMillis = lastUpdateTimeMillisMap.get(username);
        lastFetchTimeMillis = lastFetchTimeMillis == null ? 0L : lastFetchTimeMillis;
        long timeFromLastFetch = System.currentTimeMillis() - lastFetchTimeMillis;
        return nArmasDao.getNumberArmasUsuario(username)== 0 || timeFromLastFetch > MIN_TIME_FROM_LAST_FETCH_MILLIS;
    }
}
