package es.unex.propuesta_proyecto.api;


import java.util.List;
import es.unex.propuesta_proyecto.model.RepoArmas;

public interface OnReposLoadedListener {
    public void onReposLoaded(List<RepoArmas> repoArmas);
}
