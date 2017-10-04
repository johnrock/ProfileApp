package com.okapp.features.search;

import com.okapp.config.OkAppApplication;
import com.okapp.data.helpers.LogHelper;
import com.okapp.data.models.Profile;
import com.okapp.data.models.ProfileResponse;
import com.okapp.domain.interactors.SearchInteractor;
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
    private SearchType searchType;
    SearchInteractor searchInteractor;
    private LogHelper logHelper;
    private final Scheduler backgroundScheduler;
    private final Scheduler uiScheduler;
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

        logHelper.debug(OkAppApplication.LOGTAG, "Received these responses for  : " + searchType + " : " + profileResponse);

        if(profileResponse != null){
            for (Profile profile : profileResponse.getData()) {
                logHelper.debug(OkAppApplication.LOGTAG, profile.getUserName());
            }
        }
        if(profileResponse != null){
            viewLayer.loadProfiles(profileResponse.getData());
        }

    }


}
