package es.unex.propuesta_proyecto.api;

import java.util.List;

import es.unex.propuesta_proyecto.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
//Esta clase es la que se encarga de invocar las llamadas a la API
public interface ClaseApi {

    @GET("b3731904-79f8-4025-a4b0-660fe681ca90/{id}")
    Call<List<Repo>> listRepos(@Path("id") int id);
}
