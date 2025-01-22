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



     //   PokedexRecyclerAdapter adaptador = new PokedexRecyclerAdapter(new ArrayList<>(), viewModelPokemon,viewModelCapturados);
        PokedexRecyclerAdapter adaptador = new PokedexRecyclerAdapter(((MainActivity) getActivity()).listaPokedex, viewModelPokemon,viewModelCapturados);


        viewModelCapturados.getDataList().observe(getViewLifecycleOwner(), new Observer<ArrayList<PokemonBD>>() {
            @Override
            public void onChanged(ArrayList<PokemonBD> pokemonsCapturados) {
                adaptador.updateData(conbinaListas(pokemonsCapturados));
            }
        });


        // binding.rvpokedex.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvpokedex.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvpokedex.setAdapter(adaptador);
    }

    private ArrayList<Pokemon> conbinaListas(ArrayList<PokemonBD> pokemonsCapturados) {
        ArrayList<Pokemon> listaPokedex=((MainActivity) getActivity()).listaPokedex;
        if (!pokemonsCapturados.isEmpty()){
            for (Pokemon p:listaPokedex){
                p.setCapturado(existeNombre(pokemonsCapturados,p.getName()));
            }
        }
        return listaPokedex;
    }

    private boolean existeNombre(ArrayList<PokemonBD> pokemonsCapturados, String name) {
        for (PokemonBD pDB:pokemonsCapturados){
            if (pDB.getName().equals(name)){
                return true;
            }
        }
        return false;
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