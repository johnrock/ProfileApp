package com.okapp.features.search;

import com.okapp.config.OkAppApplication;
import com.okapp.data.helpers.LogHelper;
import com.okapp.data.models.Profile;
import com.okapp.data.models.ProfileResponse;
import com.okapp.domain.interactors.SearchInteractor;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchPresenterImpl implements SearchPresenter {


    ViewLayer viewLayer;
    SearchInteractor searchInteractor;
    private LogHelper logHelper;
    CompositeDisposable compositeDisposable;

    @Inject
    public SearchPresenterImpl(SearchInteractor searchInteractor, LogHelper logHelper) {
        this.searchInteractor = searchInteractor;
        this.logHelper = logHelper;
    }

    @Override
    public void bind(ViewLayer viewLayer) {
        this.viewLayer = viewLayer;
        compositeDisposable = new CompositeDisposable();


        compositeDisposable.add(
                searchInteractor.getSpecialBlend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::refreshSpecialBlend)
        );


    }

    @Override
    public void unbind() {
        compositeDisposable.dispose();
    }

    private void refreshSpecialBlend(ProfileResponse profileResponse) {

        logHelper.debug(OkAppApplication.LOGTAG, "Received responses: " + profileResponse);

        if(profileResponse != null){
            for (Profile profile : profileResponse.getData()) {
                logHelper.debug(OkAppApplication.LOGTAG, profile.getUserName());
            }
        }


    }


}
