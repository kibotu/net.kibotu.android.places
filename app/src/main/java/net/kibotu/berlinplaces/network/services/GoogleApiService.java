package net.kibotu.berlinplaces.network.services;

import net.kibotu.berlinplaces.models.google.nearby.Nearby;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public interface GoogleApiService {

    @GET("maps/api/place/nearbysearch/json?sensor=true")
    Observable<Nearby> getNearbyPlaces(
            @Query("location") String latLon,
            @Query("radius") int radius,
            @Query("type") String types,
            @Query("key") String key);
}
