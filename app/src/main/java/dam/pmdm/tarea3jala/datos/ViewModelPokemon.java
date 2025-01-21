package dam.pmdm.tarea3jala.datos;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import dam.pmdm.tarea3jala.R;
import dam.pmdm.tarea3jala.api.ApiClient;
import dam.pmdm.tarea3jala.api.ApiService;
import dam.pmdm.tarea3jala.api.RespuestaLista;
import dam.pmdm.tarea3jala.bd.ControladoraBD;
import dam.pmdm.tarea3jala.modelos.Pokemon;
import dam.pmdm.tarea3jala.modelos.PokemonBD;
import dam.pmdm.tarea3jala.modelos.PokemonCapturado;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelPokemon extends ViewModel {
//    private final String URLIMAGEN = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    private MutableLiveData<ArrayList<Pokemon>> dataList;

    public ViewModelPokemon() {




        dataList = new MutableLiveData<>();
        //Aquí llamamos a la api y nos traemos la lista de pokemons. Lo metemos en dataList

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<RespuestaLista> call = apiService.obtenerListaPokemon();
        call.enqueue(new Callback<RespuestaLista>() {
            @Override
            public void onResponse(Call<RespuestaLista> call, Response<RespuestaLista> response) {

                RespuestaLista respuestaLista = response.body();
                ArrayList<Pokemon> lista = respuestaLista.getResults();
                dataList.setValue(lista);
            }
            @Override
            public void onFailure(Call<RespuestaLista> call, Throwable t) {
            }
        });

    }



//    public void capturarPokemon(Context context, int posicion) {
//
//        ArrayList<Pokemon> lista = getDataList().getValue();
//
//        ApiService service = ApiClient.getClient().create(ApiService.class);
//        Call<PokemonCapturado> call = service.find(lista.get(posicion).getName());
//        call.enqueue(new Callback<PokemonCapturado>() {
//            @Override
//            public void onResponse(Call<PokemonCapturado> call, Response<PokemonCapturado> response) {
//
//                PokemonCapturado pokemonCapturado = response.body();
//                // Formateamos los datos para crear un objeto PokemonDB que será lo que almacenemos
//                PokemonBD pokemonBD = new PokemonBD();
//                pokemonBD.setName(pokemonCapturado.getName());
//                pokemonBD.setId(pokemonCapturado.getId());
//                pokemonBD.setHeight(pokemonCapturado.getHeight());
//                pokemonBD.setWeight(pokemonCapturado.getWeight());
//                pokemonBD.setImage(URLIMAGEN + Integer.toString(pokemonCapturado.getId()) + ".png");
//                StringBuilder tip = new StringBuilder();
//                for (PokemonCapturado.Tipos i : pokemonCapturado.getTypes()) {
//                    tip.append(i.getType().getName()).append(" ");
//                }
//                tip.setLength(tip.length() - 1);
//                String tipForm = tip.toString();
//                pokemonBD.setTypes(tipForm.replaceAll(" ", "/"));
//                String emailUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();
//
//                // Lamamos a la controladora de BBDD para que guarde el objeto PokemonDB
//                ControladoraBD.guardarPokemonBD(pokemonBD, emailUser)
//                        .addOnCompleteListener(new OnCompleteListener<Boolean>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Boolean> task) {
//                                if (task.isSuccessful() && task.getResult()) {
//                                    // La operación fue exitosa
//                                    lista.get(posicion).setCapturado(true);
//                                    dataList.setValue(lista);
//                                    Toast.makeText(context, context.getText(R.string.msgCapturadoOk), Toast.LENGTH_SHORT).show();
//                                } else {
//                                    // Hubo un error
//                                    Toast.makeText(context, context.getText(R.string.msgCapturadoNoOk), Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });
//            }
//
//                @Override
//                public void onFailure (Call < PokemonCapturado > call, Throwable t){
//                }
//            });
//
//        }


        public LiveData<ArrayList<Pokemon>> getDataList () {
            return dataList;
        }


    }
