package dam.pmdm.tarea3jala;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

import dam.pmdm.tarea3jala.api.ApiClient;
import dam.pmdm.tarea3jala.api.ApiService;
import dam.pmdm.tarea3jala.api.RespuestaLista;
import dam.pmdm.tarea3jala.databinding.ActivityMainBinding;
import dam.pmdm.tarea3jala.modelos.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;
    public ArrayList<Pokemon> pokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        if (navHostFragment != null) {
            navController = NavHostFragment.findNavController(navHostFragment);
            // Asignamos el contenedor de fragmentos con el boton de navegación
            NavigationUI.setupWithNavController(binding.bottonNavigationView, navController);
            // El botón back funcionara con nuestro navController
            NavigationUI.setupActionBarWithNavController(this, navController);
        }
        binding.bottonNavigationView.setOnItemSelectedListener(this::onmenuselected);
        initializeAppBar();

        cargaLista();
    }


    private void initializeAppBar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.pokedexFragment,
                R.id.ajustesFragment,
                R.id.misPokemonFragment
        ).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    private boolean onmenuselected(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.mis_pokemon) {
            navController.navigate(R.id.misPokemonFragment);
        } else if (menuItem.getItemId() == R.id.pokedex) {

            navController.navigate(R.id.pokedexFragment);
        } else {
            navController.navigate(R.id.ajustesFragment);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        return super.onSupportNavigateUp() || navController.navigateUp();
    }

    private void cargaLista() {
//        ApiService apiService = ApiClient.getClient().create(ApiService.class);
//        Call<RespuestaLista> call = apiService.obtenerListaPokemon();
//        call.enqueue(new Callback<RespuestaLista>() {
//            @Override
//            public void onResponse(Call<RespuestaLista> call, Response<RespuestaLista> response) {
//
//                RespuestaLista respuestaLista = response.body();
//                pokemons = respuestaLista.getResults();
//            }
//
//            @Override
//            public void onFailure(Call<RespuestaLista> call, Throwable t) {
//            }
//        });
    }
}