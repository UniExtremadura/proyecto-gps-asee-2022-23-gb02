package es.unex.propuesta_proyecto.api;

import java.util.List;

import es.unex.propuesta_proyecto.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
//Esta clase es la que se encarga de invocar las llamadas a la API
public interface ClaseApi {

    @GET("9f1c8e6c-000e-4efe-8cbc-4d15c23d2a71/{id}")
    Call<List<Repo>> listRepos(@Path("id") int id);
}
