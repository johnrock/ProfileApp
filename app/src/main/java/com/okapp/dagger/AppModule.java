package com.okapp.dagger;

import android.content.Context;

import com.okapp.BuildConfig;
import com.okapp.data.helpers.LogHelper;
import com.okapp.data.repositories.SearchRepository;
import com.okapp.domain.helpers.ImageHelper;
import com.okapp.domain.interactors.SearchInteractor;
import com.okapp.domain.interactors.SearchInteractorImpl;
import com.okapp.features.search.SearchPresenter;
import com.okapp.features.search.SearchPresenterImpl;
import com.okapp.helpers.LogHelperImpl;
import com.okapp.picasso.PicassoImageHelper;
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
    SearchPresenter providesSearchPresenter(SearchInteractor searchInteractor, LogHelper logHelper){
        //Not a singleton. Each Fragment gets a Fresh SearchPresenter
        return new SearchPresenterImpl(searchInteractor, logHelper, Schedulers.io(), AndroidSchedulers.mainThread());
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
