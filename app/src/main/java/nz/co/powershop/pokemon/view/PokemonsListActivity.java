package nz.co.powershop.pokemon.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import nz.co.powershop.pokemon.R;
import nz.co.powershop.pokemon.model.Pokemon;
import nz.co.powershop.pokemon.model.Pokemons;
import rx.Observable;

public class PokemonsListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvPokemons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemons_list);

        lvPokemons = (ListView) findViewById(R.id.lvPokemons);
        lvPokemons.setOnItemClickListener(this);

        loadData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Pokemon pokemon = (Pokemon) parent.getAdapter().getItem(position);

        Intent detailsView = new Intent(this, PokemonDetailActivity.class);
        detailsView.putExtra(PokemonDetailActivity.POKEMON_ID, pokemon.getId());
        startActivity(detailsView);
    }

    private void loadData() {
        String json = null;
        try {
            InputStream is = getAssets().open("pokemonlist.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Pokemons pokemons = new Gson().fromJson(json, Pokemons.class);

        lvPokemons.setAdapter(new PokemonsAdapter(this, pokemons.getPokemons()));
    }

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
}
