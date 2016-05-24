package net.kibotu.berlinplaces.network.services;

import net.kibotu.berlinplaces.models.foursquare.venues.Venues;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jan.rabe on 17/05/16.
 */
public interface FourSquareService {

   // https://api.foursquare.com/v2/venues/search?client_id=KXHUI3FE4IJ4QC3Q5UUYWY0CWJ1B01RTKGHACMUPE2PJVT4Y&client_secret=42NDOQX1EVTZSNB4F2OSPUBHLD2HGU34BKFS0NFQDE5OMXV4&v=20130815%20&ll=40.7,-74%20&query=dance
    @GET("v2/venues/search")
    Call<Venues> getNearbyPlaces(
            @Query("client_id") String clientId,
            @Query("client_secret") String clientSecret,
            @Query("v") String date,
            @Query("ll") String latitudeLongitude,
            @Query("query") String query);
}
