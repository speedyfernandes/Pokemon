package nz.co.powershop.pokemon.view;

import java.util.ArrayList;

import nz.co.powershop.pokemon.model.Pokemon;

/**
 * Created by Jerry on 19/07/16.
 */
public interface PokemonsListView {

    void showList(ArrayList<Pokemon> pokemons);
    void showDetails(int pokemonId);
}
