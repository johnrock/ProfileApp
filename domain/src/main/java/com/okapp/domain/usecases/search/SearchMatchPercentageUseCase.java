package com.okapp.domain.usecases.search;

import com.okapp.data.models.Profile;
import com.okapp.data.repositories.LikesHelper;
import com.okapp.data.repositories.SearchRepository;
import com.okapp.domain.usecases.UseCase;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchMatchPercentageUseCase implements UseCase<List<Profile>>{

    SearchRepository searchRepository;
    private LikesHelper likesHelper;

    public SearchMatchPercentageUseCase(SearchRepository searchRepository, LikesHelper likesHelper) {
        this.searchRepository = searchRepository;
        this.likesHelper = likesHelper;
    }

    @Override
    public Observable<List<Profile>> execute() {

        return searchRepository.byMatchPercentage()
                .flatMap(profiles -> Observable.fromIterable(profiles))
                .filter(p -> likesHelper.userIsLiked(p.getUserName()))
                .toSortedList((a,b) -> b.getMatch() - a.getMatch())
                .toObservable()
                .flatMap(profiles -> Observable.fromIterable(profiles).take(6))
                .toList()
                .toObservable();
    }

}
