package dam.pmdm.tarea3jala.vistas;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dam.pmdm.tarea3jala.R;
import dam.pmdm.tarea3jala.databinding.FragmentMisPokemonBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MisPokemonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MisPokemonFragment extends Fragment {
private FragmentMisPokemonBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding=FragmentMisPokemonBinding.inflate(inflater,container,false);
        binding.boton.setOnClickListener(this::pulsado);
        return binding.getRoot();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Cambiamos el título en el toolBar
        if (getActivity() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.mis_pokemon);
        }
    }
    private void pulsado(View view) {
        Navigation.findNavController(view).navigate(R.id.detallesFragment);
    }
}