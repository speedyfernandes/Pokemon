package nz.co.powershop.pokemon.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import nz.co.powershop.pokemon.R;
import nz.co.powershop.pokemon.adapters.PokemonsAdapter;
import nz.co.powershop.pokemon.interactor.GetPokemonsInteractor;
import nz.co.powershop.pokemon.model.Pokemon;
import nz.co.powershop.pokemon.presenter.PokemonsListPresenter;

public class PokemonsListActivity extends AppCompatActivity implements PokemonsListView,
        AdapterView.OnItemClickListener {

    private ListView lvPokemons;
    private PokemonsListPresenter presenter;
    private GetPokemonsInteractor interactor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemons_list);

        lvPokemons = (ListView) findViewById(R.id.lvPokemons);
        lvPokemons.setOnItemClickListener(this);

        interactor = new GetPokemonsInteractor(getAssets());
        presenter = new PokemonsListPresenter(this, interactor);
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();

        presenter.onStop();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        presenter.onItemClicked(position);
    }

    @Override
    public void showList(ArrayList<Pokemon> pokemons) {
        lvPokemons.setAdapter(new PokemonsAdapter(this, pokemons));
    }

    @Override
    public void showDetails(int pokemonId) {
        Intent detailsView = new Intent(this, PokemonDetailActivity.class);
        detailsView.putExtra(PokemonDetailActivity.POKEMON_ID, pokemonId);
        startActivity(detailsView);
    }
}
