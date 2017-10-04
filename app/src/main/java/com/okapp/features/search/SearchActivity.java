package com.okapp.features.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.okapp.R;
import com.okapp.config.OkAppApplication;

import javax.inject.Inject;

public class SearchActivity extends AppCompatActivity implements SearchPresenter.ViewLayer {

    @Inject SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ((OkAppApplication)getApplication()).getAppComponent().inject(this);

        searchPresenter.bind(this);
    }

    @Override
    protected void onPause() {
        searchPresenter.unbind();
        super.onPause();
    }
}
