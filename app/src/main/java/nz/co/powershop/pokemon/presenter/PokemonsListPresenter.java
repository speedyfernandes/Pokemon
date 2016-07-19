package nz.co.powershop.pokemon.presenter;

import nz.co.powershop.pokemon.interactor.GetPokemonsInteractor;
import nz.co.powershop.pokemon.model.Pokemon;
import nz.co.powershop.pokemon.model.Pokemons;
import nz.co.powershop.pokemon.view.PokemonsListView;
import rx.Observer;

/**
 * Created by Jerry on 19/07/16.
 */
public class PokemonsListPresenter implements Observer<Pokemons> {

    private PokemonsListView pokemonsListView;
    private GetPokemonsInteractor interactor;
    private Pokemons pokemons;

    public PokemonsListPresenter(PokemonsListView pokemonsListView, GetPokemonsInteractor interactor) {
        this.pokemonsListView = pokemonsListView;
        this.interactor = interactor;
    }

    public void onStart() {
        interactor.execute(this);
    }

    public void onStop() {
        interactor.cancel();
    }

    public void onItemClicked(int position) {
        Pokemon pokemon = pokemons.getPokemons().get(position);
        pokemonsListView.showDetails(pokemon.getId());
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Pokemons pokemons) {
        this.pokemons = pokemons;
        pokemonsListView.showList(pokemons.getPokemons());
    }
}
