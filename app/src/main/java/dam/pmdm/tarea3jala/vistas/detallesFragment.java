package dam.pmdm.tarea3jala.vistas;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import dam.pmdm.tarea3jala.R;
import dam.pmdm.tarea3jala.databinding.FragmentDetallesBinding;
import dam.pmdm.tarea3jala.datos.ViewModelCapturados;

public class detallesFragment extends Fragment {

    private FragmentDetallesBinding binding;
    private ViewModelCapturados viewModelCapturados;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentDetallesBinding.inflate(inflater, container, false);

        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            String nombre = getArguments().getString("nombre", "Datos no recibidos");
            binding.nombreDetalles.setText(nombre.substring(0, 1).toUpperCase() + nombre.substring(1));
            String id = Integer.toString(getArguments().getInt("id", 0));
            binding.IdDetalles.setText(id);
            String altura = Integer.toString(getArguments().getInt("altura", 0) * 10) + " Cm.";
            binding.alturaDetalles.setText(altura);
            double peso = getArguments().getInt("peso", 0) * 0.1f;
            String pesoString = String.format("%.1f", peso )+ " Kg.";
            binding.pesoDetalles.setText(pesoString);
            binding.tiposDetalles.setText(getArguments().getString("tipos", "Datos no recibidos"));

            Picasso.get()
                    .load(getArguments().getString("imagen"))
                    .error(R.drawable.delete_pokemon)
                    .into(binding.imagenDetalles);

            viewModelCapturados=new ViewModelProvider(requireActivity()).get(ViewModelCapturados.class);
             binding.botonBorrar.setOnClickListener(v -> eliminarPokemon(viewModelCapturados, getContext()));

        }
    }

    private void eliminarPokemon(ViewModelCapturados viewModelCapturados, Context context) {
        viewModelCapturados.eliminarPokemon(binding.IdDetalles.getText().toString());
        Toast.makeText(requireActivity(), "El Pokemon " +binding.nombreDetalles.getText()+" ha sido borrado correctamente", Toast.LENGTH_SHORT).show();
        getActivity().getSupportFragmentManager().popBackStack();

    }
    @Override
    public void onStart() {
        super.onStart();
        // Cambiamos el título en el toolBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.tituloDetalle);
        }
    }
}