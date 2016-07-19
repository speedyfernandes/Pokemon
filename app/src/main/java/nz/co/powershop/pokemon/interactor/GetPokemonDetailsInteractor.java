package nz.co.powershop.pokemon.interactor;

import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

import nz.co.powershop.pokemon.model.Pokemon;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by Jerry on 19/07/16.
 */
public class GetPokemonDetailsInteractor {

    private Subscription subscription = Subscriptions.empty();
    private AssetManager assets;

    public GetPokemonDetailsInteractor(AssetManager assets) {
        this.assets = assets;
    }

    public void execute(int pokemonId, Observer<Pokemon> observer) {
        String json = null;
        try {
            InputStream is = assets.open(String.format(Locale.US, "pokemondetails%d.json", pokemonId));
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        subscription = Observable.just(new Gson().fromJson(json, Pokemon.class))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    public void cancel() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
