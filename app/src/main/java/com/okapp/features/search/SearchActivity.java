package com.okapp.features.search;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.okapp.R;
import com.okapp.config.OkAppApplication;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity implements SearchPresenter.ViewLayer {

    @BindView(R.id.toolbar)   Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tablayout)  TabLayout tabLayout;

    @Inject SearchPresenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ((OkAppApplication)getApplication()).getAppComponent().inject(this);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.ACTIVITY_TITLE_SEARCH);
        setSupportActionBar(toolbar);
        initViewPager();

        searchPresenter.bind(this);
    }

    private void initViewPager() {

        SearchViewPagerAdapter adapter = new SearchViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new SearchFragment(), getString(R.string.SEARCH_TAB_SPECIAL_BLEND));
        adapter.addFragment(new SearchFragment(), getString(R.string.SEARCH_TAB_MATCH_PERC));

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void onPause() {
        searchPresenter.unbind();
        super.onPause();
    }
}
