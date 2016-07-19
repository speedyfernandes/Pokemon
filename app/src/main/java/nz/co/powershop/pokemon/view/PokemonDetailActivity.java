package nz.co.powershop.pokemon.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import nz.co.powershop.pokemon.R;
import nz.co.powershop.pokemon.presenter.PokemonDetailPresenter;

public class PokemonDetailActivity extends AppCompatActivity implements PokemonDetailView {

    private ImageView ivImage;
    private TextView txtName;
    private TextView txtWeight;
    private TextView txtHeight;
    private TextView txtExperience;
    private PokemonDetailPresenter presenter;

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

        presenter = new PokemonDetailPresenter(this, getIntent().getIntExtra(POKEMON_ID, 1),
                getAssets());
    }

    @Override
    protected void onStart() {
        super.onStart();

        presenter.onStart();
    }

    @Override
    public void setImage(String image) {
        InputStream stream = null;
        try {
            stream = getAssets().open(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (stream != null) {
            Drawable d = Drawable.createFromStream(stream, null);
            ivImage.setImageDrawable(d);
        }
    }

    @Override
    public void setName(String name) {
        txtName.setText(name);
    }

    @Override
    public void setHeight(int height) {
        txtHeight.setText(String.format("Height %d", height));
    }

    @Override
    public void setWeight(int weight) {
        txtWeight.setText(String.format("Weight %d", weight));
    }

    @Override
    public void setExperience(int base_experience) {
        txtExperience.setText(String.format("Experience %d", base_experience));
    }
}
