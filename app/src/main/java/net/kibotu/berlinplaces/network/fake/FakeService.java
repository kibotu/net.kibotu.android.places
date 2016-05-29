package net.kibotu.berlinplaces.network.fake;

import net.kibotu.berlinplaces.models.fake.person.People;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Nyaruhodo on 29.05.2016.
 */

public interface FakeService {

    @GET("https://randomuser.me/api/?results=20")
    Observable<People> getPeople();
}
