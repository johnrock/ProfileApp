package com.okapp.domain.interactors;

import com.okapp.data.models.ProfileResponse;

import io.reactivex.Observable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface SearchInteractor {

    Observable<ProfileResponse> getSpecialBlend();
}
