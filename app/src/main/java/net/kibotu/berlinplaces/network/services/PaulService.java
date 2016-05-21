package net.kibotu.berlinplaces.network.services;

import net.kibotu.berlinplaces.models.facebook.events.Events;
import net.kibotu.berlinplaces.models.google.nearby.Nearby;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jan.rabe on 13/05/16.
 */
public interface PaulService {

    @GET("places/api/events")
    Observable<Events> getNearbyPlaces(
            @Query("lat") double lat,
            @Query("lng") double lng,
            @Query("distance") int distance,
            @Query("sort") String sort,
            @Query("access_token") String accessToken);


    @GET("http://kibotu.net/places/facebook_events.json")
    Observable<Events> getMockedFacebookEvents();

    @GET("http://kibotu.net/places/google_places.json")
    Observable<Nearby> getMockedGooglePlaces();
}