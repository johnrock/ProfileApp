package com.okapp.retrofit;

import com.okapp.data.helpers.LogHelper;
import com.okapp.data.models.Profile;
import com.okapp.data.repositories.SearchRepository;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Retrofit;


/**
 * @author John Piser johnpiser@yahoo.com
 */

public class RetrofitSearchRepository extends RetrofitRepository implements SearchRepository {

    public static final String ENDPOINT_SPECIALBLEND    = "matchSample.json";
    public static final String ENDPOINT_MATCHPERCENTAGE = "matchSample.json";

    public RetrofitSearchRepository(LogHelper logHelper) {
        super(logHelper);
    }

    @Override
    public Observable<List<Profile>> bySpecialBlend(){

        Retrofit retrofit = createRXRetrofit(API_ENDPOINT);
        SearchAPI searchAPI = retrofit.create(SearchAPI.class);
        return searchAPI.getSpecialBlend(ENDPOINT_SPECIALBLEND)
                .map(p->p.getData());
    }

    @Override
    public Observable<List<Profile>> byMatchPercentage() {
        //making same call for sample purposes
        Retrofit retrofit = createRXRetrofit(API_ENDPOINT);
        SearchAPI searchAPI = retrofit.create(SearchAPI.class);

        return searchAPI.getSpecialBlend(ENDPOINT_MATCHPERCENTAGE)
        .map(p -> p.getData());


    }
}
