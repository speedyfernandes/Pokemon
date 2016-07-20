package nz.co.powershop.pokemon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;

import nz.co.powershop.pokemon.interactor.GetPokemonsInteractor;
import nz.co.powershop.pokemon.model.Pokemon;
import nz.co.powershop.pokemon.model.Pokemons;
import nz.co.powershop.pokemon.presenter.PokemonsListPresenter;
import nz.co.powershop.pokemon.view.PokemonsListView;

import static org.mockito.Mockito.verify;

/**
 * Created by Jerry on 20/07/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PokemonsListPresenterTest {

    private PokemonsListPresenter presenter;

    @Mock
    private PokemonsListView view;

    @Mock
    private GetPokemonsInteractor interactor;

    @Mock
    private Pokemons pokemons;

    @Mock
    private ArrayList<Pokemon> pokemonArray;

    @Before
    public void setup() {
        presenter = new PokemonsListPresenter(view, interactor);
    }

    @Test
    public void shouldFetchData() {
        presenter.onStart();
        verify(interactor).execute(presenter);
    }

    @Test
    public void shouldUnsubscribe() {
        presenter.onStop();
        verify(interactor).cancel();
    }

    @Test
    public void shouldDisplayList() {
        presenter.onNext(pokemons);
        verify(view).showList(pokemons.getPokemons());
    }
}
