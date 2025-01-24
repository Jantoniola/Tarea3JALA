package dam.pmdm.tarea3jala.datos;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;

import dam.pmdm.tarea3jala.R;
import dam.pmdm.tarea3jala.api.ApiClient;
import dam.pmdm.tarea3jala.api.ApiService;
import dam.pmdm.tarea3jala.bd.ControladoraBD;
import dam.pmdm.tarea3jala.modelos.Pokemon;
import dam.pmdm.tarea3jala.modelos.PokemonBD;
import dam.pmdm.tarea3jala.modelos.PokemonCapturado;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelCapturados extends ViewModel {
    private MutableLiveData<ArrayList<PokemonBD>> dataListCapturados;
    private final String URLIMAGEN = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/home/";
    private HashMap<String, String> tiposEsp;

    public ViewModelCapturados() {
        dataListCapturados = new MutableLiveData<>();
        iniciarMapaTipos();
        //Aquí llamamos a la controladora de BD y almacenamos la lista de pokemon capturados
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ControladoraBD.leerPokemonCapturados(user.getEmail(), ListCapturados -> {

            dataListCapturados.setValue(ListCapturados);

        });
    }

    public LiveData<ArrayList<PokemonBD>> getDataList() {
        return dataListCapturados;
    }

    private PokemonBD obtenerPokemon(ArrayList<PokemonBD> lista, String id) {
        for (PokemonBD poke : lista) {
            if (Integer.toString(poke.getId()).equals(id)) {
                return poke;
            }
        }
        return null;
    }

    public void capturarPokemon(Context context, String nombre) {

        ArrayList<Pokemon> lista = new ArrayList<>();

        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<PokemonCapturado> call = service.find(nombre);
        call.enqueue(new Callback<PokemonCapturado>() {
            @Override
            public void onResponse(Call<PokemonCapturado> call, Response<PokemonCapturado> response) {

                PokemonCapturado pokemonCapturado = response.body();
                // Formateamos los datos para crear un objeto PokemonDB que será lo que almacenemos
                PokemonBD pokemonBD = new PokemonBD();
                pokemonBD.setName(pokemonCapturado.getName());
                pokemonBD.setId(pokemonCapturado.getId());
                pokemonBD.setHeight(pokemonCapturado.getHeight());
                pokemonBD.setWeight(pokemonCapturado.getWeight());
                pokemonBD.setImage(URLIMAGEN + Integer.toString(pokemonCapturado.getId()) + ".png");
                StringBuilder tip = new StringBuilder();
                for (PokemonCapturado.Tipos i : pokemonCapturado.getTypes()) {
                    tip.append(i.getType().getName()).append(" ");
                }
                tip.setLength(tip.length() - 1);
                String tipForm = tip.toString();
                pokemonBD.setTypes(tipForm.replaceAll(" ", "/"));
                String emailUser = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                // Lamamos a la controladora de BBDD para que guarde el objeto PokemonDB
                ControladoraBD.guardarPokemonBD(pokemonBD, emailUser)
                        .addOnCompleteListener(new OnCompleteListener<Boolean>() {
                            @Override
                            public void onComplete(@NonNull Task<Boolean> task) {
                                if (task.isSuccessful() && task.getResult()) {
                                    // La operación fue exitosa
                                    // Ponemos true en la lista de Pokedex
                                    // Actualizamos la lista de Capturados
                                    //lista.get(posicion).setCapturado(true);
                                    //dataList.setValue(lista);

                                    ArrayList<PokemonBD> lista = dataListCapturados.getValue();
                                    lista.add(pokemonBD);
                                    dataListCapturados.setValue(lista);

                                    Toast.makeText(context, context.getText(R.string.msgCapturadoOk), Toast.LENGTH_SHORT).show();
                                } else {
                                    // Hubo un error
                                    Toast.makeText(context, context.getText(R.string.msgCapturadoNoOk), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

            @Override
            public void onFailure(Call<PokemonCapturado> call, Throwable t) {
            }
        });

    }

    public void eliminarPokemon(String id) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ControladoraBD.borrarPokemon(user.getEmail(), id);
        ArrayList<PokemonBD> lista = dataListCapturados.getValue();
        PokemonBD pokemonABorrar = obtenerPokemon(lista, id);
        if (lista != null) {
            if (pokemonABorrar != null) {
                lista.remove(pokemonABorrar);
                dataListCapturados.setValue(lista);
            }
        }
    }

    private void iniciarMapaTipos() {
        tiposEsp = new HashMap<>();
        tiposEsp.put("normal", "normal");
        tiposEsp.put("fighting", "lucha");
        tiposEsp.put("flying", "volador");
        tiposEsp.put("poison", "veneno");
        tiposEsp.put("ground", "tierra");
        tiposEsp.put("rock", "roca");
        tiposEsp.put("bug", "bicho");
        tiposEsp.put("ghost", "fantasma");
        tiposEsp.put("steel", "acero");
        tiposEsp.put("fire", "fuego");
        tiposEsp.put("water", "agua");
        tiposEsp.put("grass", "planta");
        tiposEsp.put("electric", "eléctrico");
        tiposEsp.put("psychic", "psíquico");
        tiposEsp.put("ice", "hielo");
        tiposEsp.put("dragon", "dragón");
        tiposEsp.put("dark", "siniestro");
        tiposEsp.put("fairy", "hada");
        tiposEsp.put("stellar", "estelar");
        tiposEsp.put("unknown", "desconocido");
    }

    public String tipoTraducido(String tipo) {
        return tiposEsp.get(tipo).toString();
    }
}
