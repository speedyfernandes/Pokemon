package nz.co.powershop.pokemon;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import nz.co.powershop.pokemon.interactor.GetPokemonDetailsInteractor;
import nz.co.powershop.pokemon.model.Pokemon;
import nz.co.powershop.pokemon.presenter.PokemonDetailPresenter;
import nz.co.powershop.pokemon.view.PokemonDetailView;

import static org.mockito.Mockito.verify;

/**
 * Created by Jerry on 20/07/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PokemonDetailPresenterTest {

    private PokemonDetailPresenter presenter;

    private int pokemonId;

    @Mock
    private PokemonDetailView view;

    @Mock
    private GetPokemonDetailsInteractor interactor;

    @Mock
    private Pokemon pokemon;

    @Before
    public void setup() {
        pokemonId = 1;
        presenter = new PokemonDetailPresenter(view, pokemonId, interactor);
    }

    @Test
    public void shouldFetchData() {
        presenter.onStart();
        verify(interactor).execute(pokemonId, presenter);
    }

    @Test
    public void shouldUnsubscribe() {
        presenter.onStop();
        verify(interactor).cancel();
    }

    @Test
    public void shouldDisplayPokemonImage() {
        presenter.onNext(pokemon);
        verify(view).setImage(pokemon.getImage());
    }

    @Test
    public void shouldDisplayName() {
        presenter.onNext(pokemon);
        verify(view).setName(pokemon.getName());
    }

    @Test
    public void shouldDisplayHeight() {
        presenter.onNext(pokemon);
        verify(view).setHeight(pokemon.getHeight());
    }

    @Test
    public void shouldDisplayWeight() {
        presenter.onNext(pokemon);
        verify(view).setWeight(pokemon.getWeight());
    }

    @Test
    public void shouldDisplayExperience() {
        presenter.onNext(pokemon);
        verify(view).setExperience(pokemon.getBase_experience());
    }
}
