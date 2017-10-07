package com.okapp.features.search;

import com.okapp.data.helpers.LikesHelper;
import com.okapp.data.helpers.LogHelper;
import com.okapp.data.models.Profile;
import com.okapp.domain.usecases.UseCaseExecutor;
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

    final LogHelper logHelper;
    final Scheduler backgroundScheduler;
    final Scheduler uiScheduler;
    final UseCaseExecutor useCaseExecutor;
    final LikesHelper likesHelper;
    ViewLayer viewLayer;
    CompositeDisposable compositeDisposable;

    @Inject
    public SearchPresenterImpl(LogHelper logHelper,
                               Scheduler backgroundScheduler,
                               Scheduler uiScheduler,
                               UseCaseExecutor useCaseExecutor,
                               LikesHelper likesHelper) {

        this.logHelper = logHelper;
        this.backgroundScheduler = backgroundScheduler;
        this.uiScheduler = uiScheduler;
        this.useCaseExecutor = useCaseExecutor;
        this.likesHelper = likesHelper;
    }

    @Override
    public void bind(ViewLayer viewLayer, SearchUseCase searchUseCase) {
        this.viewLayer = viewLayer;

        viewLayer.toggleLoading(true);

        if(compositeDisposable != null){
            compositeDisposable.dispose();
        }
        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(
                         useCaseExecutor.executeUseCaseFor(searchUseCase)
                        .subscribeOn(backgroundScheduler)
                        .observeOn(uiScheduler)
                        .subscribe(this::showData)
        );
    }

    @Override
    public void unbind() {
        if(compositeDisposable != null){
            compositeDisposable.dispose();
        }
    }

    @Override
    public void setLikedStateForUser(String username) {
        if(likesHelper.userIsLiked(username)){
            likesHelper.unlikeUser(username);
        }
        else{
            likesHelper.likeUser(username);
        }
        viewLayer.refresh();
    }

    @Override
    public boolean userIsLiked(String username) {
        return likesHelper.userIsLiked(username);
    }

    protected void showData(List<Profile> profiles) {
        if(profiles != null){
            viewLayer.loadProfiles(new ProfileAdapter().adapt(profiles));
        }
        viewLayer.toggleLoading(false);
    }
}
