package es.unex.propuesta_proyecto.api;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import es.unex.propuesta_proyecto.model.RepoArmas;

public class ArmasViewModel extends ViewModel {

    private final ArmasRepository mRepository;
    private final LiveData<List<RepoArmas>> mRepos;
    private String mUsername = "";
    private int id;

    public ArmasViewModel(ArmasRepository repository) {
        mRepository = repository;
        mRepos = mRepository.getCurrentArma();
    }

    public void setUsername(final int codigo,String username){
        mUsername = username;
        id = codigo;
        mRepository.setUsername(codigo,username);
    }

    public void onRefresh() {
        mRepository.doFetchArma(id,mUsername);
    }

    public LiveData<List<RepoArmas>> getRepos() {
        return mRepos;
    }

}