package es.unex.propuesta_proyecto.api;

import java.io.IOException;
import java.util.List;

import es.unex.propuesta_proyecto.model.RepoArmas;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReposNetworkLoaderRunnable implements Runnable{

    private final OnReposLoadedListener mOnReposLoadedListener;
    private final int mCode;

    public ReposNetworkLoaderRunnable(int cod, OnReposLoadedListener onReposLoadedListener) {
                mOnReposLoadedListener = onReposLoadedListener;
                mCode = cod;
    }

    @Override
    public void run() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sheet.best/api/sheets/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClaseApi clase = retrofit.create(ClaseApi.class);
        try {
            List<RepoArmas> repos = clase.listRepos(mCode).execute().body();
            AppExecutors.getInstance().mainThread().execute(() -> mOnReposLoadedListener.onReposLoaded(repos));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}