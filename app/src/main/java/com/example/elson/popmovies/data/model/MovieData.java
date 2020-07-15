package com.example.elson.popmovies.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MovieData extends RealmObject implements Parcelable {

    @PrimaryKey
    @SerializedName("id")
    int id;

    @SerializedName("poster_path")
    String poster;

    @SerializedName("vote_average")
    double rating;

    @SerializedName("title")
    String title;

    public MovieData() {
    }

    public MovieData(@NonNull Parcel in) {
        id = in.readInt();
        poster = in.readString();
        rating = in.readDouble();
        title = in.readString();
    }

    public MovieData(@NonNull MovieFullData data) {
        this.id = data.getId();
        this.poster = data.getPoster();
        this.title = data.getTitle();
        this.rating = data.getRawRating();
    }

    public static final Parcelable.Creator<MovieData> CREATOR = new Parcelable.Creator<MovieData>() {
        @NonNull
        @Override
        public MovieData createFromParcel(@NonNull Parcel in) {
            return new MovieData(in);
        }

        @NonNull
        @Override
        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };

    @Nullable
    public String getPoster() {
        return poster;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getTitle() {
        return title;
    }

    @NonNull
    public String getRating() {
        return rating + "/10";
    }

    public boolean isFavorite() {
        try (Realm db = Realm.getDefaultInstance()){
            return db.where(MovieData.class).equalTo("id", id).findFirst() != null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeByte((byte) (0x01));
        dest.writeInt(id);
        dest.writeString(poster);
        dest.writeByte((byte) (0x01));
        dest.writeDouble(rating);
        dest.writeString(title);
    }
}