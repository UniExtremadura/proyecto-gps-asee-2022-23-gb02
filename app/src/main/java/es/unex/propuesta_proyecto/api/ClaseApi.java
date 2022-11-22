package es.unex.propuesta_proyecto.api;

import java.util.List;

import es.unex.propuesta_proyecto.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClaseApi {

    @GET("59469b68-af70-412d-a7e1-b2bf646be612/{id}")
    Call<List<Repo>> listRepos(@Path("id") int id);
}
