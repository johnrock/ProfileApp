package com.okapp.retrofit;

import com.okapp.data.helpers.LogHelper;
import com.okapp.data.models.ProfileResponse;
import com.okapp.data.repositories.SearchRepository;

import io.reactivex.Observable;
import retrofit2.Retrofit;


/**
 * @author John Piser johnpiser@yahoo.com
 */

public class RetrofitSearchRepository extends RetrofitRepository implements SearchRepository {

    public RetrofitSearchRepository(LogHelper logHelper) {
        super(logHelper);
    }

    @Override
    public Observable<ProfileResponse> bySpecialBlend(){

        Retrofit retrofit = createRXRetrofit(API_ENDPOINT);
        SearchAPI searchAPI = retrofit.create(SearchAPI.class);
        return searchAPI.getSpecialBlend("matchSample.json");
    }

    @Override
    public Observable<ProfileResponse> byMatchPercentage() {
        //making same call for sample purposes
        Retrofit retrofit = createRXRetrofit(API_ENDPOINT);
        SearchAPI searchAPI = retrofit.create(SearchAPI.class);
        return searchAPI.getSpecialBlend("matchSample.json");
    }
}
