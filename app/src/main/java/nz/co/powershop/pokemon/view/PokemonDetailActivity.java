package nz.co.powershop.pokemon.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import nz.co.powershop.pokemon.R;
import nz.co.powershop.pokemon.interactor.GetPokemonDetailsInteractor;
import nz.co.powershop.pokemon.model.Pokemon;
import rx.Observable;
import rx.Observer;

public class PokemonDetailActivity extends AppCompatActivity {

    private ImageView ivImage;
    private TextView txtName;
    private TextView txtWeight;
    private TextView txtHeight;
    private TextView txtExperience;
    private int pokemonId;

    public static final String POKEMON_ID = "POKEMON_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        ivImage = (ImageView) findViewById(R.id.ivImage);
        txtName = (TextView) findViewById(R.id.txtName);
        txtWeight = (TextView) findViewById(R.id.txtWeight);
        txtHeight = (TextView) findViewById(R.id.txtHeight);
        txtExperience = (TextView) findViewById(R.id.txtExperience);

        pokemonId = getIntent().getIntExtra(POKEMON_ID, 1);

        loadData();
    }

    private void loadData() {
        String json = null;
        try {
            InputStream is = getAssets().open(String.format(Locale.US, "pokemondetails%d.json", pokemonId));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Pokemon pokemon = new Gson().fromJson(json, Pokemon.class);

        InputStream stream = null;
        try {
            stream = getAssets().open(pokemon.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (stream != null) {
            Drawable d = Drawable.createFromStream(stream, null);
            ivImage.setImageDrawable(d);
        }

        txtName.setText(pokemon.getName());
        txtHeight.setText(String.format("Height %d", pokemon.getHeight()));
        txtWeight.setText(String.format("Weight %d", pokemon.getWeight()));
        txtExperience.setText(String.format("Experience %d", pokemon.getBase_experience()));
    }
}
