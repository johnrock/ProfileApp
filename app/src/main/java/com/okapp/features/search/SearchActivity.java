package com.okapp.features.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.okapp.R;
import com.okapp.config.OkAppApplication;
import com.okapp.data.helpers.LogHelper;
import com.okapp.domain.usecases.search.SearchUseCase;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @Inject LogHelper logHelper;

    @BindView(R.id.toolbar)   Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tablayout)  TabLayout tabLayout;
    private SearchViewPagerAdapter searchViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((OkAppApplication)getApplication()).getAppComponent().inject(this);

        setContentView(R.layout.activity_search);
        logHelper.debug(OkAppApplication.LOGTAG, "SearchActivity: onCreate()");
        ButterKnife.bind(this);
        toolbar.setTitle(getString(R.string.activity_title_search));
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {

        searchViewPagerAdapter = new SearchViewPagerAdapter(getSupportFragmentManager(),
                                                            tabTitleList(),
                                                            searchUseCaseList(),
                                                            logHelper);

        viewPager.setAdapter(searchViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @NonNull
    private List<SearchUseCase> searchUseCaseList() {
        return Arrays.asList(SearchUseCase.SPECIAL_BLEND, SearchUseCase.MATCH_PERCENTAGE);
    }

    @NonNull
    private List<String> tabTitleList() {
        return Arrays.asList(getString(R.string.search_tab_special_blend), getString(R.string.search_tab_match_perc));
    }

    public void refreshTabs(){
        searchViewPagerAdapter.refreshFragments();
    }

    @Override
    protected void onDestroy() {
        logHelper.debug(OkAppApplication.LOGTAG, "SearchActivity: onDestroy()");
        super.onDestroy();
    }
}
