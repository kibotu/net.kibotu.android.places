package net.kibotu.berlinplaces.network.services;

import net.kibotu.berlinplaces.models.paul.blob.BlobRequest;
import net.kibotu.berlinplaces.models.paul.blob.BlobResponse;
import net.kibotu.berlinplaces.models.paul.events.Events;
import net.kibotu.berlinplaces.models.paul.locations.Locations;
import net.kibotu.berlinplaces.models.paul.login.AuthenticationRequest;
import net.kibotu.berlinplaces.models.paul.login.AuthenticationResponse;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jan.rabe on 13/05/16.
 */
public interface PaulService {

    @GET("places/api/events")
    Observable<Events> getEvents(
            @Query("lat") double lat,
            @Query("lng") double lng,
            @Query("distance") int distance,
            @Query("skip") int skip,
            @Query("limit") int limit);

    @GET("places/api/places")
    Observable<Locations> getPlaces(
            @Query("lat") double lat,
            @Query("lng") double lng,
            @Query("distance") int distance,
            @Query("skip") int skip,
            @Query("limit") int limit);

    @Headers("Content-Type: application/json")
    @POST("login")
    Observable<AuthenticationResponse> login(@Body AuthenticationRequest request);

    @Headers("Content-Type: application/json")
    @POST("register")
    Observable<AuthenticationResponse> register(@Body AuthenticationRequest request);

    @GET("user/blob")
    Observable<BlobResponse> loadBlob();

    @Headers("Content-Type: application/json")
    @POST("user/blob")
    Observable<BlobResponse> storeBlob(@Body BlobRequest request);
}