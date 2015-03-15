package com.flatandflatmates.Interfaces;

import com.flatandflatmates.JavaObjects.Places;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by applect on 15/3/15.
 */
public interface GetPlaces {
    @GET("/autocomplete/json")
    void listPlaces(
            @Query("sensor") String sensor,
            @Query("types") String types,
            @Query("key") String key,
            @Query("input") String input,
            Callback<Places> cb
    );
}
