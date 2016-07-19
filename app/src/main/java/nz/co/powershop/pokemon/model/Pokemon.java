package nz.co.powershop.pokemon.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Jerry on 19/07/16.
 */
public class Pokemon implements Parcelable {

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    @SerializedName("image")
    private String image;

    @SerializedName("id")
    private int id;

    @SerializedName("weight")
    private int weight;

    @SerializedName("height")
    private int height;

    @SerializedName("base_experience")
    private int base_experience;

    protected Pokemon(Parcel in) {
        name = in.readString();
        url = in.readString();
        image = in.readString();
        id = in.readInt();
        weight = in.readInt();
        height = in.readInt();
        base_experience = in.readInt();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(url);
        dest.writeString(image);
        dest.writeInt(id);
        dest.writeInt(weight);
        dest.writeInt(height);
        dest.writeInt(base_experience);
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getBase_experience() {
        return base_experience;
    }
}
