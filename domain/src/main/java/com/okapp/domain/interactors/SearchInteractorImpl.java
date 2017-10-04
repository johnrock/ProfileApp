package com.okapp.domain.interactors;

import com.okapp.data.models.ProfileResponse;
import com.okapp.data.repositories.SearchRepository;

import io.reactivex.Observable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchInteractorImpl implements SearchInteractor {

    SearchRepository searchRepository;

    public SearchInteractorImpl(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public Observable<ProfileResponse> getSpecialBlend() {
        return searchRepository.getSpecialBlend();
    }
}
