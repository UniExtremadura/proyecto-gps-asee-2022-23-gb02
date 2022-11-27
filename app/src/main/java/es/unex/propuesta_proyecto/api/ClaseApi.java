package es.unex.propuesta_proyecto.api;

import java.util.List;

import es.unex.propuesta_proyecto.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
//Esta clase es la que se encarga de invocar las llamadas a la API
public interface ClaseApi {

    @GET("02e3de90-5cea-44e2-9fb1-ebb3271a935e/{id}")
    Call<List<Repo>> listRepos(@Path("id") int id);
}
