package es.unex.propuesta_proyecto.api;

import java.util.List;

import es.unex.propuesta_proyecto.model.Repo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
//Esta clase es la que se encarga de invocar las llamadas a la API
public interface ClaseApi {

    @GET("cad19ebb-f914-43b1-b1ec-3c4689631e27/{id}")
    Call<List<Repo>> listRepos(@Path("id") int id);
}
