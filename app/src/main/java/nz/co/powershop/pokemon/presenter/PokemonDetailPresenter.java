package nz.co.powershop.pokemon.presenter;

import nz.co.powershop.pokemon.interactor.GetPokemonDetailsInteractor;
import nz.co.powershop.pokemon.model.Pokemon;
import nz.co.powershop.pokemon.view.PokemonDetailView;
import rx.Observer;

/**
 * Created by Jerry on 19/07/16.
 */
public class PokemonDetailPresenter implements Observer<Pokemon> {

    private PokemonDetailView pokemonDetailView;
    private int pokemonId;
    private GetPokemonDetailsInteractor interactor;

    public PokemonDetailPresenter(PokemonDetailView pokemonDetailView, int pokemonId,
                                  GetPokemonDetailsInteractor interactor) {
        this.pokemonDetailView = pokemonDetailView;
        this.pokemonId = pokemonId;
        this.interactor = interactor;
    }

    public void onStart() {
        interactor.execute(pokemonId, this);
    }

    public void onStop() {
        interactor.cancel();
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onNext(Pokemon pokemon) {
        pokemonDetailView.setImage(pokemon.getImage());
        pokemonDetailView.setName(pokemon.getName());
        pokemonDetailView.setHeight(pokemon.getHeight());
        pokemonDetailView.setWeight(pokemon.getWeight());
        pokemonDetailView.setExperience(pokemon.getBase_experience());
    }
}
