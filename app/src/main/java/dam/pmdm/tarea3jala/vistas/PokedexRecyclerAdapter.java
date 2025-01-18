package dam.pmdm.tarea3jala.vistas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dam.pmdm.tarea3jala.R;
import dam.pmdm.tarea3jala.databinding.FichaPokemonPokedexBinding;
import dam.pmdm.tarea3jala.datos.ViewModelPokemon;
import dam.pmdm.tarea3jala.modelos.Pokemon;

public class PokedexRecyclerAdapter extends RecyclerView.Adapter<PokedexViewHolder> {
    private ArrayList<Pokemon> pokemons;
    private ViewModelPokemon viewModelPokemon;

    /**
     * Constructor de la clase
     *
     * @param pokemons La lista de Pokemon
     * @param viewModelPokemon  El ViewModel que usamos en el fragmento
     */
    public PokedexRecyclerAdapter(ArrayList<Pokemon> pokemons, ViewModelPokemon viewModelPokemon) {
        this.pokemons = pokemons;
        this.viewModelPokemon = viewModelPokemon;
    }

    //Implementamos los métodos obligatorios

    /**
     * Devolvemos un objeto ViewHolder, al cual le pasamos la celda que hemos creado.
     * @param parent   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return Objeto ViewHolder
     */
    @NonNull
    @Override
    public PokedexViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // Inflamos el layout donde mostraremos los datos y se lo enviamos al Holder
        FichaPokemonPokedexBinding binding=FichaPokemonPokedexBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new PokedexViewHolder(binding);

    }

    /**
     * Se encarga de pasar cada una de las posiciones de la lista de Pokemons a la clase ViewHolder
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PokedexViewHolder holder, int position) {
        Pokemon pokemonActual = pokemons.get(position);
        //Llamamos al método de la clase ViewHolder, que habremos creado, que se encargará de darle valor al cardView y le enviamnos el objeto actual

        holder.pinta(pokemonActual);
        holder.itemView.setOnClickListener(view->capturarPokemon(position,view));
    }

    private void capturarPokemon(int posicion,View view) {
        // Aquí llamaremos al procedimiento donde meteremos el pokemon seleccionado en la base de datos, así como cambiaremos su aspecto

        viewModelPokemon.capturarPokemon(view.getContext(), posicion);

    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }
    public void updateData(ArrayList<Pokemon> newDataList){
        this.pokemons=newDataList;
        notifyDataSetChanged();
    }
}

class PokedexViewHolder extends RecyclerView.ViewHolder {
private FichaPokemonPokedexBinding binding;
    private final String URLIMAGEN="https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/";
    public PokedexViewHolder(FichaPokemonPokedexBinding binding) {
        super(binding.getRoot());
        this.binding=binding;
    }

    /**
     * Va a Renderizar el CardView o simplemente asignar los valores al xml de cada item
     *
     * @param pokemonActual El objeto Pokemon que le toca pintar
     */
    public void pinta(Pokemon pokemonActual) {

        if (pokemonActual.isCapturado()){
            this.itemView.setAlpha(0.4f);
            this.itemView.setEnabled(false);
        }else {
            this.itemView.setAlpha(1f);
            this.itemView.setEnabled(true);
        }

        Picasso.get().load(URLIMAGEN+Integer.toString(pokemonActual.getId())+".png").into(binding.imageViewPokemon);
        binding.textID.setText(Integer.toString(pokemonActual.getId()));
        //Ponemos la primera letra en mayúsculas
        binding.textNombre.setText(pokemonActual.getName().substring(0,1).toUpperCase()+pokemonActual.getName().substring(1));
    }
}