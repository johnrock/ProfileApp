package com.okapp.dagger;

import android.content.Context;

import com.okapp.BuildConfig;
import com.okapp.data.helpers.LogHelper;
import com.okapp.data.repositories.SearchRepository;
import com.okapp.domain.interactors.SearchInteractor;
import com.okapp.domain.interactors.SearchInteractorImpl;
import com.okapp.features.search.SearchPresenter;
import com.okapp.features.search.SearchPresenterImpl;
import com.okapp.helpers.LogHelperImpl;
import com.okapp.retrofit.RetrofitSearchRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
    SearchPresenter providesSearchPresenter(SearchInteractor searchInteractor, LogHelper logHelper){
        return new SearchPresenterImpl(searchInteractor, logHelper);
    }

    @Provides
    @Singleton
    SearchInteractor providesSearchInteractor(SearchRepository searchRepository){
        return new SearchInteractorImpl(searchRepository);
    }

    @Provides
    @Singleton
    SearchRepository providesSearchRepository(LogHelper logHelper){
        return new RetrofitSearchRepository(logHelper);
    }
}
