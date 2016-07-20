package nz.co.powershop.pokemon;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import nz.co.powershop.pokemon.view.PokemonDetailActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Jerry on 21/07/16.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class PokemonDetailViewTest {

    @Rule
    public ActivityTestRule<PokemonDetailActivity> activityTestRule =
            new ActivityTestRule<>(PokemonDetailActivity.class, true, false);

    @Before
    public void setUp() {
        Intent intent = new Intent();
        intent.putExtra(PokemonDetailActivity.POKEMON_ID, 1);
        activityTestRule.launchActivity(intent);
    }

    @Test
    public void testDetailsDisplayed() {
        onView(withText("bulbasaur")).check(matches(isDisplayed()));
        onView(withText(String.format("Height %d", 7))).check(matches(isDisplayed()));
        onView(withText(String.format("Weight %d", 69))).check(matches(isDisplayed()));
        onView(withText(String.format("Experience %d", 64))).check(matches(isDisplayed()));
    }
}
