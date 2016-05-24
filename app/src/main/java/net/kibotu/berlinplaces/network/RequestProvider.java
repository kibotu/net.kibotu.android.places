package net.kibotu.berlinplaces.network;

import net.kibotu.berlinplaces.BuildConfig;
import net.kibotu.berlinplaces.R;
import net.kibotu.berlinplaces.models.facebook.events.Events;
import net.kibotu.berlinplaces.models.foursquare.venues.Venues;
import net.kibotu.berlinplaces.models.google.nearby.Nearby;
import net.kibotu.berlinplaces.network.services.FourSquareService;
import net.kibotu.berlinplaces.network.services.GoogleApiService;
import net.kibotu.berlinplaces.network.services.PaulService;

import hugo.weaving.DebugLog;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

import static com.common.android.utils.ContextHelper.getContext;
import static java.text.MessageFormat.format;

/**
 * Created by Nyaruhodo on 05.05.2016.
 */
public class RequestProvider {

    public static FourSquareService createFoursquareService() {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.foursquare.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FourSquareService.class);
    }

    /**
     * https://api.foursquare.com/v2/venues/search?client_id=KXHUI3FE4IJ4QC3Q5UUYWY0CWJ1B01RTKGHACMUPE2PJVT4Y&client_secret=42NDOQX1EVTZSNB4F2OSPUBHLD2HGU34BKFS0NFQDE5OMXV4&v=%20&ll=40.7,-74%20&query=dance
     */
    @DebugLog
    public static Call<Venues> getNearbyPlacesFoursquare(double latitude, double longitude, String query) {
        return createFoursquareService().getNearbyPlaces(
                getContext().getString(R.string.foursquare_client_id),
                getContext().getString(R.string.foursquare_client_secret),
                "20130815",
                format("{0},{1}", latitude, longitude),
                query
        );
    }

    public static GoogleApiService createGoogleService() {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://maps.googleapis.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(GoogleApiService.class);
    }


    @DebugLog
    public static Observable<Nearby> getNearbyPlaces(double latitude, double longitude, int radius, String types) {
        return createGoogleService().getNearbyPlaces(format("{0},{1}", latitude, longitude), radius, types, getContext().getString(R.string.google_server_key));
    }

    public static PaulService createPaulService() {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG
                ? HttpLoggingInterceptor.Level.BODY
                : HttpLoggingInterceptor.Level.NONE);

        final OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        baseUrl = "http://172.27.100.143:3000/events/";
        baseUrl = "http://www.sprotte.eltanin.uberspace.de/";
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        return retrofit.create(PaulService.class);
    }

    @DebugLog
    public static Observable<Events> getNearbyEvents(double latitude, double longitude, int distance, String sort) {
        return createPaulService().getNearbyPlaces(latitude, longitude, distance, sort, getContext().getString(R.string.facebook_access_token));
    }

    @DebugLog
    public static Observable<Events> getMockedFacebookEvents() {
        return createPaulService().getMockedFacebookEvents();
    }

    @DebugLog
    public static Observable<Nearby> getMockedGooglePlaces() {
        return createPaulService().getMockedGooglePlaces();
    }
}
