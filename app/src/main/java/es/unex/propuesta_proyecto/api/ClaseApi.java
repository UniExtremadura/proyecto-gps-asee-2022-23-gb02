package es.unex.propuesta_proyecto.api;

import java.util.List;

import es.unex.propuesta_proyecto.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
//Esta clase es la que se encarga de invocar las llamadas a la API
public interface ClaseApi {

    @GET("fef1303d-5cf1-49e1-85da-f2e15f9fdb6b/{id}")
    Call<List<Repo>> listRepos(@Path("id") int id);
}
