package es.unex.propuesta_proyecto.api;


import java.util.List;
import es.unex.propuesta_proyecto.model.Repo;

public interface OnReposLoadedListener {
    public void onReposLoaded(List<Repo> repos);
}
