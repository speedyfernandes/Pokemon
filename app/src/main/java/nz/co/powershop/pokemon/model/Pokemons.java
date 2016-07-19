package nz.co.powershop.pokemon.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Jerry on 19/07/16.
 */
public class Pokemons {

    @SerializedName("results")
    public ArrayList<Pokemon> pokemons;

    public ArrayList<Pokemon> getPokemons() {
        return pokemons;
    }
}
