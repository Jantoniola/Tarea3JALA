package dam.pmdm.tarea3jala.vistas;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dam.pmdm.tarea3jala.MainActivity;
import dam.pmdm.tarea3jala.R;
import dam.pmdm.tarea3jala.databinding.FragmentPokedexBinding;
import dam.pmdm.tarea3jala.datos.ViewModelCapturados;
import dam.pmdm.tarea3jala.datos.ViewModelPokemon;
import dam.pmdm.tarea3jala.modelos.Pokemon;
import dam.pmdm.tarea3jala.modelos.PokemonBD;


public class PokedexFragment extends Fragment {

    private FragmentPokedexBinding binding;

    private ViewModelPokemon viewModelPokemon;
    private ViewModelCapturados viewModelCapturados;
    ArrayList<PokemonBD> listaCapturados;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPokedexBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModelCapturados=new ViewModelProvider(requireActivity()).get(ViewModelCapturados.class);
        viewModelPokemon = new ViewModelProvider(requireActivity()).get(ViewModelPokemon.class);
        PokedexRecyclerAdapter adaptador = new PokedexRecyclerAdapter(new ArrayList<>(), viewModelPokemon,viewModelCapturados);
        // Observamoas el LiveData
        viewModelPokemon.getDataList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Pokemon>>() {
            @Override
            public void onChanged(ArrayList<Pokemon> pokemons) {
                adaptador.updateData(pokemons);
            }
        });


        // binding.rvpokedex.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvpokedex.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvpokedex.setAdapter(adaptador);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Cambiamos el título en el toolBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.pokedex);
        }
    }
}