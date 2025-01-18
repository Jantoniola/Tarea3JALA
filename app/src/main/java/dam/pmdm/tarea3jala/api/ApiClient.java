package dam.pmdm.tarea3jala.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String URL="https://pokeapi.co/api/v2/";
    private static Retrofit retrofit=null;

    public static Retrofit getClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

}
