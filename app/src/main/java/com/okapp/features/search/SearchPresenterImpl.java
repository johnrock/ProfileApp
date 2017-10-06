package com.okapp.features.search;

import android.support.annotation.Nullable;

import com.okapp.data.helpers.LogHelper;
import com.okapp.data.models.Profile;
import com.okapp.domain.usecases.UseCase;
import com.okapp.domain.usecases.search.SearchMatchPercentageUseCase;
import com.okapp.domain.usecases.search.SearchSpecialBlendUseCase;
import com.okapp.domain.usecases.search.SearchUseCase;
import com.okapp.models.ProfileAdapter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchPresenterImpl implements SearchPresenter {

    final SearchSpecialBlendUseCase searchSpecialBlendUseCase;
    final SearchMatchPercentageUseCase searchMatchPercentageUseCase;

    ViewLayer viewLayer;

    SearchUseCase searchUseCase;
    LogHelper logHelper;
    final Scheduler backgroundScheduler;
    final Scheduler uiScheduler;
    CompositeDisposable compositeDisposable;


    @Inject
    public SearchPresenterImpl(LogHelper logHelper,
                               Scheduler backgroundScheduler, Scheduler uiScheduler,
                               SearchSpecialBlendUseCase searchSpecialBlendUseCase,
                               SearchMatchPercentageUseCase searchMatchPercentageUseCase) {

        this.searchSpecialBlendUseCase = searchSpecialBlendUseCase;
        this.searchMatchPercentageUseCase = searchMatchPercentageUseCase;
        this.logHelper = logHelper;
        this.backgroundScheduler = backgroundScheduler;
        this.uiScheduler = uiScheduler;
    }

    @Override
    public void bind(ViewLayer viewLayer, SearchUseCase searchUseCase) {
        this.viewLayer = viewLayer;
        this.searchUseCase = searchUseCase;

        if(compositeDisposable != null){
            compositeDisposable.dispose();
        }
        compositeDisposable = new CompositeDisposable();

        UseCase<List<Profile>> useCase = getUseCase(searchUseCase);

        if (useCase != null) {
            compositeDisposable.add(
                    useCase.execute()
                            .subscribeOn(backgroundScheduler)
                            .observeOn(uiScheduler)
                            .subscribe(this::showData)

            );
        }
    }

    @Nullable
    private UseCase<List<Profile>> getUseCase(SearchUseCase searchUseCase) {
        switch (searchUseCase){
            case SPECIAL_BLEND:
                return searchSpecialBlendUseCase;
            case MATCH_PERCENTAGE:
                return searchMatchPercentageUseCase;
        }
        return null;
    }

    @Override
    public void unbind() {
        compositeDisposable.dispose();
    }

    private void showData(List<Profile> profiles) {
        if(profiles != null){
            viewLayer.loadProfiles(new ProfileAdapter().adapt(profiles));
        }

    }


}
