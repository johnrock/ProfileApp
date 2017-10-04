package com.okapp.config;

import android.app.Application;

import com.okapp.dagger.AppComponent;
import com.okapp.dagger.AppModule;
import com.okapp.dagger.DaggerAppComponent;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class OkAppApplication extends Application {

    public static final String LOGTAG = "OKAPP";
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(getApplicationContext())).build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
