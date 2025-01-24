package dam.pmdm.tarea3jala.vistas;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import dam.pmdm.tarea3jala.R;
import dam.pmdm.tarea3jala.databinding.FragmentMisPokemonBinding;
import dam.pmdm.tarea3jala.datos.ViewModelCapturados;
import dam.pmdm.tarea3jala.modelos.PokemonBD;

public class MisPokemonFragment extends Fragment {
private FragmentMisPokemonBinding binding;
    private ViewModelCapturados viewModelCapturados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding=FragmentMisPokemonBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(requireActivity());
        //Aquí inicializa el viewModel
        viewModelCapturados = new ViewModelProvider(requireActivity()).get(ViewModelCapturados.class);
        CapturadosRecyclerAdapter adaptador = new CapturadosRecyclerAdapter(new ArrayList<>(), viewModelCapturados,sharedPreferences.getString("idioma", "es"));
        // Observamoas el LiveData
        viewModelCapturados.getDataList().observe(getViewLifecycleOwner(), new Observer<ArrayList<PokemonBD>>() {
            @Override
            public void onChanged(ArrayList<PokemonBD> pokemonsCapturados) {
                adaptador.updateData(pokemonsCapturados);
            }
        });

       // binding.rvpokedex.setLayoutManager(new GridLayoutManager(getContext(), 2));
         binding.rvcapturados.setLayoutManager(new GridLayoutManager(getContext(),1));
        binding.rvcapturados.setAdapter(adaptador);
    }




    @Override
    public void onStart() {
        super.onStart();
        // Cambiamos el título en el toolBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.mis_pokemon);
        }
    }

}