package nz.co.powershop.pokemon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import nz.co.powershop.pokemon.R;
import nz.co.powershop.pokemon.model.Pokemon;

/**
 * Created by Jerry on 19/07/16.
 */

public class PokemonsAdapter extends ArrayAdapter<Pokemon> {
    public PokemonsAdapter(Context context, ArrayList<Pokemon> pokemons) {
        super(context, 0, pokemons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Pokemon pokemon = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_pokemon, parent, false);
        }

        TextView txtName = (TextView) convertView.findViewById(R.id.txtName);
        txtName.setText(pokemon.getName());

        return convertView;
    }
}