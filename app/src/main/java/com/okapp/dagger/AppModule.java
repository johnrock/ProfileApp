package com.okapp.dagger;

import android.content.Context;

import com.okapp.BuildConfig;
import com.okapp.data.helpers.LogHelper;
import com.okapp.data.helpers.LikesHelper;
import com.okapp.data.repositories.SearchRepository;
import com.okapp.domain.helpers.ImageHelper;
import com.okapp.domain.helpers.PreferencesHelper;
import com.okapp.domain.usecases.UseCaseExecutor;
import com.okapp.domain.usecases.search.SearchMatchPercentageUseCase;
import com.okapp.domain.usecases.search.SearchSpecialBlendUseCase;
import com.okapp.domain.usecases.search.UseCaseExecutorImpl;
import com.okapp.features.search.SearchPresenter;
import com.okapp.features.search.SearchPresenterImpl;
import com.okapp.helpers.LogHelperImpl;
import com.okapp.helpers.NetworkHelper;
import com.okapp.helpers.PreferencesHelperImpl;
import com.okapp.picasso.PicassoImageHelper;
import com.okapp.helpers.LikesHelperImpl;
import com.okapp.retrofit.RetrofitSearchRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * @author John Piser johnpiser@yahoo.com
 */

@Module
public class AppModule {

    Context applicationContext;

    public AppModule(Context applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Provides
    @Singleton
    LogHelper providesLogHelper(){
        return new LogHelperImpl(BuildConfig.DEBUG);
    }

    @Provides
    @Singleton
    ImageHelper providesImageHelper(){
        return new PicassoImageHelper(applicationContext, false);
    }

    @Provides
    @Singleton
    PreferencesHelper providesPreferenceHelper(){
        return new PreferencesHelperImpl(applicationContext);
    }


    @Provides
    SearchPresenter providesSearchPresenter(LogHelper logHelper,
                                            UseCaseExecutor useCaseExecutor,
                                            LikesHelper likesHelper){

        //Not a singleton. Each Fragment gets a Fresh SearchPresenter
        return new SearchPresenterImpl(logHelper,
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                useCaseExecutor,
                likesHelper);
    }


    @Provides
    @Singleton
    SearchRepository providesSearchRepository(LogHelper logHelper){
        return new RetrofitSearchRepository(logHelper);
    }

    @Provides
    @Singleton
    SearchSpecialBlendUseCase providesSpecialBlendUseCase(SearchRepository searchRepository, LogHelper logHelper){
        return new SearchSpecialBlendUseCase(searchRepository, logHelper);
    }

    @Provides
    @Singleton
    SearchMatchPercentageUseCase providesSearchMatchPercentageUseCase(SearchRepository searchRepository,
                                                                      LikesHelper likesHelper,
                                                                      LogHelper logHelper){
        return new SearchMatchPercentageUseCase(searchRepository, likesHelper, logHelper);
    }

    @Provides
    @Singleton
    LikesHelper providesLikesHelper(PreferencesHelper preferenceHelper) {
        return new LikesHelperImpl(preferenceHelper);
    }

    @Provides
    @Singleton
    UseCaseExecutor providesUseCaseExecutor(SearchSpecialBlendUseCase searchSpecialBlendUseCase,
                                                     SearchMatchPercentageUseCase searchMatchPercentageUseCase){
        return new UseCaseExecutorImpl(searchSpecialBlendUseCase, searchMatchPercentageUseCase);

    }

    @Provides
    @Singleton
    NetworkHelper providesNetworkHelper(){
        return new NetworkHelper();
    }
}
