package dam.pmdm.tarea3jala;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.Locale;

import dam.pmdm.tarea3jala.api.ApiClient;
import dam.pmdm.tarea3jala.api.ApiService;
import dam.pmdm.tarea3jala.api.RespuestaLista;
import dam.pmdm.tarea3jala.databinding.ActivityMainBinding;
import dam.pmdm.tarea3jala.datos.ViewModelCapturados;
import dam.pmdm.tarea3jala.modelos.Pokemon;
import dam.pmdm.tarea3jala.modelos.PokemonBD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private NavController navController;
    public ArrayList<Pokemon> listaPokedex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        obtenerListaPokedex();
        cargarPreferencias();
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

    public void obtenerListaPokedex() {
        listaPokedex=new ArrayList<>();
        //Aquí llamamos a la api y nos traemos la lista de pokemons. Lo metemos en dataList

        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<RespuestaLista> call = apiService.obtenerListaPokemon();
        call.enqueue(new Callback<RespuestaLista>() {
            @Override
            public void onResponse(Call<RespuestaLista> call, Response<RespuestaLista> response) {

                RespuestaLista respuestaLista = response.body();
                listaPokedex = respuestaLista.getResults();
            }
            @Override
            public void onFailure(Call<RespuestaLista> call, Throwable t) {
            }
        });

    }
    private void cargarPreferencias() {
        SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);

        //Leemos el valor almacenado o si no existe, le damos un valor por defecto. En este caso, Español.
        String lenguaje = preferencias.getString("idioma", "es");
        //Cambia el idioma
        Locale locale = new Locale(lenguaje);
        Locale.setDefault(locale);

        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

    }

}