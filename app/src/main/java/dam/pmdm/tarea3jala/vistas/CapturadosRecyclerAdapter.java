package dam.pmdm.tarea3jala.vistas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import dam.pmdm.tarea3jala.databinding.FichaPokemonCapturadoBinding;
import dam.pmdm.tarea3jala.datos.ViewModelCapturados;
import dam.pmdm.tarea3jala.modelos.PokemonBD;

public class CapturadosRecyclerAdapter extends RecyclerView.Adapter<CapturadosViewHolder> {
    private ArrayList<PokemonBD> pokemonsCapturados;
    private ViewModelCapturados viewModelCapturados;

    public CapturadosRecyclerAdapter(ArrayList<PokemonBD> pokemonsCapturados, ViewModelCapturados viewModelCapturados) {
        this.pokemonsCapturados = pokemonsCapturados;
        this.viewModelCapturados = viewModelCapturados;
    }

    @NonNull
    @Override
    public CapturadosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FichaPokemonCapturadoBinding binding=FichaPokemonCapturadoBinding.inflate(LayoutInflater.from(parent.getContext()));
        return  new CapturadosViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull CapturadosViewHolder holder, int position) {

        PokemonBD pokemonActual=pokemonsCapturados.get(position);
        holder.pinta(pokemonActual);
        holder.itemView.setOnClickListener(view->DetallesPokemon(position,view));
    }

    private void DetallesPokemon(int position, View view) {
        // Se pasan los detalles a otro fragment
    }

    @Override
    public int getItemCount() {
        return pokemonsCapturados.size();
    }

    public void updateData(ArrayList<PokemonBD> pokemonsCapturados) {
        this.pokemonsCapturados=pokemonsCapturados;
        notifyDataSetChanged();
    }
}
class CapturadosViewHolder extends RecyclerView.ViewHolder{
private FichaPokemonCapturadoBinding binding;
    public CapturadosViewHolder(FichaPokemonCapturadoBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }

    public void pinta(PokemonBD pokemonActual) {

        Picasso.get().load(pokemonActual.getImage()).into(binding.imageViewPokemon);
        binding.textNombre.setText(pokemonActual.getName().substring(0,1).toUpperCase()+pokemonActual.getName().substring(1));
        binding.textID.setText(Integer.toString(pokemonActual.getId()));
        // Cambiamos la altura de unidad
        binding.textAltura.setText(Integer.toString(pokemonActual.getHeight()));

        // Cambiamos el peso de unidad
        binding.textPeso.setText(Integer.toString(pokemonActual.getWeight()));

        binding.textoFijoTipos.setText(pokemonActual.getTypes());
    }
}