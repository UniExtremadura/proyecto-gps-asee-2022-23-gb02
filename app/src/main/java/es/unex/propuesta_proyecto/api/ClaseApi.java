package es.unex.propuesta_proyecto.api;

import java.util.List;

import es.unex.propuesta_proyecto.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
//Esta clase es la que se encarga de invocar las llamadas a la API
public interface ClaseApi {

    @GET("aa5a92e4-3623-4a80-b9b6-b6f53bb4acae/{id}")
    Call<List<Repo>> listRepos(@Path("id") int id);
}
