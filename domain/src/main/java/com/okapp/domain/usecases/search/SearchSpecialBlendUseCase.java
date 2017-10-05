package com.okapp.domain.usecases.search;

import com.okapp.data.models.Profile;
import com.okapp.data.repositories.SearchRepository;
import com.okapp.domain.usecases.UseCase;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchSpecialBlendUseCase implements UseCase<List<Profile>>{

    SearchRepository searchRepository;

    public SearchSpecialBlendUseCase(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public Observable<List<Profile>> execute() {
        return searchRepository.bySpecialBlend();
    }

}
