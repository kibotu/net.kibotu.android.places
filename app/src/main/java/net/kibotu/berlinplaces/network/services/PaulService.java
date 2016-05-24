package net.kibotu.berlinplaces.network.services;

import net.kibotu.berlinplaces.models.facebook.events.Events;
import net.kibotu.berlinplaces.models.google.nearby.Nearby;
import net.kibotu.berlinplaces.models.google.nearby.Place;

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

    @GET("http://www.sprotte.eltanin.uberspace.de/places/api/locations?lat=52.481734083252654&lng=13.390166067775644&distance=100&limit=20")
    Observable<Locations> getLocations();
    @GET("https://gist.githubusercontent.com/Sprotte/7b37b653955c76c95170bc0fc0c377e3/raw/609c455caa23067431021b44ceda33453b46d1b8/location.json")
    Observable<Place> getMockedGooglePlaces2();
}