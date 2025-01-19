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

public class ViewModelCapturados extends ViewModel {
    private final String URLIMAGEN = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    private MutableLiveData<ArrayList<PokemonBD>> dataListCapturados;

    public ViewModelCapturados() {
        dataListCapturados = new MutableLiveData<>();
        //Aquí llamamos a la controladora de BD y almacenamos la lista de pokemon capturados
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ControladoraBD.leerPokemonCapturados(user.getEmail(), ListCapturados -> {

            dataListCapturados.setValue(ListCapturados);

        });


    }



        public LiveData<ArrayList<PokemonBD>> getDataList () {
            return dataListCapturados;
        }


    }
