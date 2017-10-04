package com.okapp.data.repositories;

import com.okapp.data.models.ProfileResponse;

import io.reactivex.Observable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface SearchRepository {


    Observable<ProfileResponse> bySpecialBlend();

    Observable<ProfileResponse> byMatchPercentage();

}
