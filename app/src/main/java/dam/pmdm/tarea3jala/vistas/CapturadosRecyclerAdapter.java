package dam.pmdm.tarea3jala.vistas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dam.pmdm.tarea3jala.R;
import dam.pmdm.tarea3jala.databinding.FichaPokemonCapturadoBinding;
import dam.pmdm.tarea3jala.datos.ViewModelCapturados;
import dam.pmdm.tarea3jala.modelos.PokemonBD;

public class CapturadosRecyclerAdapter extends RecyclerView.Adapter<CapturadosViewHolder> {
    private ArrayList<PokemonBD> pokemonsCapturados;
    private ViewModelCapturados viewModelCapturados;
    private String idioma;

    public CapturadosRecyclerAdapter(ArrayList<PokemonBD> pokemonsCapturados, ViewModelCapturados viewModelCapturados, String idioma) {
        this.pokemonsCapturados = pokemonsCapturados;
        this.viewModelCapturados = viewModelCapturados;
        this.idioma=idioma;
    }

    @NonNull
    @Override
    public CapturadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FichaPokemonCapturadoBinding binding = FichaPokemonCapturadoBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new CapturadosViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull CapturadosViewHolder holder, int position) {

        PokemonBD pokemonActual = pokemonsCapturados.get(position);

        StringBuilder tiposTraducidos = new StringBuilder();
        if (idioma.equals("es")) {
            String[] tipos = pokemonActual.getTypes().split("/");

            for (String s : tipos) {
                String t=viewModelCapturados.tipoTraducido(s);
                tiposTraducidos.append(t);
                tiposTraducidos.append("/");
            }
            tiposTraducidos.deleteCharAt(tiposTraducidos.length() - 1);
        }else{
            tiposTraducidos.append(pokemonActual.getTypes());
        }
        holder.pinta(pokemonActual,tiposTraducidos.toString());
        holder.itemView.setOnClickListener(view -> DetallesPokemon(pokemonActual,tiposTraducidos.toString(), view));
    }

    private void DetallesPokemon(PokemonBD pokemonDetalles, String tipos, View view) {
        // Se pasan los detalles al fragment de detalles.

        Bundle bundle = new Bundle();
        bundle.putString("nombre", pokemonDetalles.getName());
        bundle.putString("imagen", pokemonDetalles.getImage());
        bundle.putInt("id", pokemonDetalles.getId());
        bundle.putInt("altura", pokemonDetalles.getHeight());
        bundle.putInt("peso", pokemonDetalles.getWeight());
        bundle.putString("tipos", tipos);
        Navigation.findNavController(view).navigate(R.id.detallesFragment, bundle);
    }

    @Override
    public int getItemCount() {
        return pokemonsCapturados.size();
    }

    public void updateData(ArrayList<PokemonBD> pokemonsCapturados) {
        this.pokemonsCapturados = pokemonsCapturados;
        notifyDataSetChanged();
    }
}

class CapturadosViewHolder extends RecyclerView.ViewHolder {
    private FichaPokemonCapturadoBinding binding;

    public CapturadosViewHolder(FichaPokemonCapturadoBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void pinta(PokemonBD pokemonActual, String tiposTraducidos) {
        Picasso.get().load(pokemonActual.getImage()).into(binding.imageViewPokemon);
        binding.textNombre.setText(pokemonActual.getName().substring(0, 1).toUpperCase() + pokemonActual.getName().substring(1));
        binding.textID.setText(Integer.toString(pokemonActual.getId()));
        // Cambiamos la altura de unidad
        int altura = pokemonActual.getHeight() * 10;
        binding.textAltura.setText(Integer.toString(altura) + " Cm.");

        // Cambiamos el peso de unidad
        double peso = pokemonActual.getWeight() * 0.1f;
        binding.textPeso.setText(String.format("%.1f", peso) + " Kg.");
        binding.textTipos.setText(tiposTraducidos);

    }


}