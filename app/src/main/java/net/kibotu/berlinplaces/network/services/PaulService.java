package net.kibotu.berlinplaces.network.services;

import net.kibotu.berlinplaces.models.paul.events.Events;
import net.kibotu.berlinplaces.models.paul.locations.Locations;

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

    @GET("http://www.sprotte.eltanin.uberspace.de/places/api/locations?lat=52.481734083252654&lng=13.390166067775644&distance=100&limit=20")
    Observable<Locations> getLocations();

    @GET("http://www.sprotte.eltanin.uberspace.de/places/api/events?skip=0&limit=20")
    Observable<Events> getEvents();
}