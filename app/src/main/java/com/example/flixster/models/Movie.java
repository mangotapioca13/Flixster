package com.example.flixster.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Parcel //annotation indicates class is Parcelable
public class Movie {

    // INSTANCE FIELDS
    // values from API
        // fields must be public for parceler
    String title;
    String overview;
    String posterPath; // only the path
    String backdropPath;
    Double voteAverage;
    String releaseDate;

    // no-arg, empty constructor required for Parceler
    public Movie() {}

    // initialize from JSON data
    public Movie(JSONObject object) throws JSONException {
        title = object.getString("title");
        overview = object.getString("overview");
        posterPath = object.getString("poster_path");
        backdropPath = object.getString("backdrop_path");
        voteAverage = object.getDouble("vote_average");
        releaseDate = setReleaseDate(object);
    }

    public String setReleaseDate(JSONObject object) throws JSONException {
        // fields for setting up release date
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy");
        String inputReleaseDate = object.getString("release_date");
        Date date = null;

        try {
            date = inputFormat.parse(inputReleaseDate);
        } catch (ParseException e) {
            Log.e("Movie", "Error with parsing release date.");
        }

        return outputFormat.format(date);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}