package com.okapp.features.search;

import com.okapp.data.helpers.LogHelper;
import com.okapp.data.models.ProfileResponse;
import com.okapp.domain.interactors.SearchInteractor;
import com.okapp.models.ProfileAdapter;
import com.okapp.util.SearchType;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchPresenterImpl implements SearchPresenter {

    ViewLayer viewLayer;
    SearchType searchType;
    SearchInteractor searchInteractor;
    LogHelper logHelper;
    final Scheduler backgroundScheduler;
    final Scheduler uiScheduler;
    CompositeDisposable compositeDisposable;

    @Inject
    public SearchPresenterImpl(SearchInteractor searchInteractor, LogHelper logHelper, Scheduler backgroundScheduler, Scheduler uiScheduler) {
        this.searchInteractor = searchInteractor;
        this.logHelper = logHelper;
        this.backgroundScheduler = backgroundScheduler;
        this.uiScheduler = uiScheduler;
    }

    @Override
    public void bind(ViewLayer viewLayer, SearchType searchType) {
        this.viewLayer = viewLayer;
        this.searchType = searchType;
        this.compositeDisposable = new CompositeDisposable();


        compositeDisposable.add(
                getSearchObservable()
                .subscribeOn(backgroundScheduler)
                .observeOn(uiScheduler)
                .subscribe(this::refreshSpecialBlend)

        );


    }

    private Observable<ProfileResponse> getSearchObservable() {
        switch (searchType){
            case MATCH_PERCENTAGE:
                return searchInteractor.byMatchPercentage();
                default:
                    return searchInteractor.bySpecialBlend();
        }
    }

    @Override
    public void unbind() {
        compositeDisposable.dispose();
    }

    private void refreshSpecialBlend(ProfileResponse profileResponse) {
        if(profileResponse != null){
            viewLayer.loadProfiles(new ProfileAdapter().adapt(profileResponse.getData()));
        }

    }


}
