package nz.co.powershop.pokemon.view;

/**
 * Created by Jerry on 19/07/16.
 */
public interface PokemonDetailView {
    void setImage(String image);

    void setName(String name);

    void setHeight(int height);

    void setWeight(int weight);

    void setExperience(int base_experience);
}
