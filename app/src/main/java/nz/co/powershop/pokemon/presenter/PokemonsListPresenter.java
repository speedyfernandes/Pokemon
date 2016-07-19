package nz.co.powershop.pokemon.presenter;

import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import nz.co.powershop.pokemon.model.Pokemon;
import nz.co.powershop.pokemon.model.Pokemons;
import nz.co.powershop.pokemon.view.PokemonsListView;

/**
 * Created by Jerry on 19/07/16.
 */
public class PokemonsListPresenter {

    private PokemonsListView pokemonsListView;
    private AssetManager assets;
    private Pokemons pokemons;

    public PokemonsListPresenter(PokemonsListView pokemonsListView, AssetManager assets) {
        this.pokemonsListView = pokemonsListView;
        this.assets = assets;
    }

    public void onStart() {

        String json = null;
        try {
            InputStream is = assets.open("pokemonlist.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        pokemons = new Gson().fromJson(json, Pokemons.class);

        pokemonsListView.showList(pokemons.getPokemons());
    }

    public void onItemClicked(int position) {
        Pokemon pokemon = pokemons.getPokemons().get(position);
        pokemonsListView.showDetails(pokemon.getId());
    }
}
