package dam.pmdm.tarea3jala.api;

import dam.pmdm.tarea3jala.modelos.PokemonCapturado;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("pokemon?offset=0&limit=150")
    Call<RespuestaLista> obtenerListaPokemon();

   @GET("pokemon/{name}")
   Call<PokemonCapturado> find(@Path("name") String name);

}
