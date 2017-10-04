package com.okapp.retrofit;

import com.okapp.data.models.ProfileResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;


/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface SearchAPI {

    @GET
    Observable<ProfileResponse> getSpecialBlend(@Url String url);
}
