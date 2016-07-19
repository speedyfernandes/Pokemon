package nz.co.powershop.pokemon.presenter;

import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import nz.co.powershop.pokemon.model.Pokemon;
import nz.co.powershop.pokemon.view.PokemonDetailView;

/**
 * Created by Jerry on 19/07/16.
 */
public class PokemonDetailPresenter {

    private PokemonDetailView pokemonDetailView;
    private int pokemonId;
    private AssetManager assets;

    public PokemonDetailPresenter(PokemonDetailView pokemonDetailView, int pokemonId,
                                  AssetManager assets) {
        this.pokemonDetailView = pokemonDetailView;
        this.pokemonId = pokemonId;
        this.assets = assets;
    }

    public void onStart() {

        String json = null;
        try {
            InputStream is = assets.open(String.format(Locale.US, "pokemondetails%d.json", pokemonId));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Pokemon pokemon = new Gson().fromJson(json, Pokemon.class);

        pokemonDetailView.setImage(pokemon.getImage());
        pokemonDetailView.setName(pokemon.getName());
        pokemonDetailView.setHeight(pokemon.getHeight());
        pokemonDetailView.setWeight(pokemon.getWeight());
        pokemonDetailView.setExperience(pokemon.getBase_experience());
    }
}
