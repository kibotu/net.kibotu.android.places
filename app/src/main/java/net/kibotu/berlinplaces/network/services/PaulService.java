package net.kibotu.berlinplaces.network.services;

import net.kibotu.berlinplaces.models.facebook.events.Events;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jan.rabe on 13/05/16.
 */
public interface PaulService {

    @GET("/")
    Call<Events> getNearbyPlaces(
            @Query("lat") double lat,
            @Query("lng") double lng,
            @Query("distance") int distance,
            @Query("sort") String sort,
            @Query("access_token") String accessToken);


    @GET("http://kibotu.net/places/generated.json")
    Call<Events> getPreloadedPlaces();
}