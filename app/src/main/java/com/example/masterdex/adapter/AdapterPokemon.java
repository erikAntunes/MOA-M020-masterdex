package com.example.masterdex.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.masterdex.interfaces.PokemonListener;
import com.example.masterdex.models.Pokemon;
import com.example.masterdex.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AdapterPokemon  extends RecyclerView.Adapter<AdapterPokemon.ViewHolder>  {


        private ArrayList<Pokemon> pokemonsDados;


        private PokemonListener pokemonListener;


        public AdapterPokemon (PokemonListener pokemonListener, ArrayList<Pokemon> pokemonsDados){ // construtor
            this.pokemonsDados = pokemonsDados;
            this.pokemonListener = pokemonListener;

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pokemons_celula,viewGroup,false);// fazendo a conversao de um xlm para uma view

            return new ViewHolder(view); // retornando uma nova view holder passando a view que era um xml
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

            final Pokemon pokemon = pokemonsDados.get(i);
            viewHolder.textNomePokemon.setText(pokemon.getName());


            Picasso.get().load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+pokemon.getNumber()+".png")
                    .into(viewHolder.imageFotoPokemon);

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pokemonListener.onPokemonClicado(pokemon);
                }
            });
        }
        class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textNomePokemon;
        private ImageView imageFotoPokemon;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            textNomePokemon = itemView.findViewById(R.id.textNomePokemon);
            imageFotoPokemon = itemView.findViewById(R.id.imageFotoPokemon);
        }
    }

        @Override
        public int getItemCount() {
            return pokemonsDados.size();
        }

        public void adicionarListaPokemon(ArrayList<Pokemon> pokemonArrayList) {

            pokemonsDados.addAll(pokemonArrayList);
            notifyDataSetChanged();
        }


    public void atualizarListaPokemons(List<Pokemon> listaFiltrada) {

            pokemonsDados = new ArrayList<>();
            pokemonsDados.addAll(listaFiltrada);
            notifyDataSetChanged();

    }

}




