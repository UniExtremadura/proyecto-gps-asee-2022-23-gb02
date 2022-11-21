package es.unex.propuesta_proyecto.api;

import java.util.List;

import es.unex.propuesta_proyecto.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClaseApi {

    @GET("d327317d-6f06-4916-84b6-23f8fa0a6afa/{id}")
    Call<List<Repo>> listRepos(@Path("id") int id);
}
