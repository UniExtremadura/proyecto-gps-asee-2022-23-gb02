package es.unex.propuesta_proyecto.api;

import java.util.List;

import es.unex.propuesta_proyecto.model.RepoArmas;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ClaseApi {

    @GET("8bc68137-0895-40da-837d-87d02a32d391/{id}")
    Call<List<RepoArmas>> listRepos(@Path("id") int id);
}
