package com.okapp.domain.usecases.search;

import com.okapp.data.models.Profile;
import com.okapp.data.repositories.SearchRepository;
import com.okapp.domain.usecases.UseCase;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchMatchPercentageUseCase implements UseCase<List<Profile>>{

    SearchRepository searchRepository;

    public SearchMatchPercentageUseCase(SearchRepository searchRepository) {
        this.searchRepository = searchRepository;
    }

    @Override
    public Observable<List<Profile>> execute() {

        return searchRepository.byMatchPercentage()
                .flatMap(profiles -> Observable.fromIterable(profiles))
                .filter(p -> p.isLiked())
                .toSortedList((a,b) -> b.getMatch() - a.getMatch())
                .toObservable()
                .flatMap(profiles -> Observable.fromIterable(profiles).take(6))
                .toList()
                .toObservable();
    }

}
