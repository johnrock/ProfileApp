package com.okapp.dagger;

import com.okapp.features.search.SearchFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author John Piser johnpiser@yahoo.com
 */

@Singleton
@Component(modules={AppModule.class})
public interface AppComponent {
    void inject (SearchFragment searchFragment);
}
