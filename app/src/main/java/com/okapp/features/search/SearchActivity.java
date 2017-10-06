package com.okapp.features.search;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.okapp.R;
import com.okapp.domain.usecases.search.SearchUseCase;
import com.okapp.util.FragmentArgs;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)   Toolbar toolbar;
    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tablayout)  TabLayout tabLayout;
    private SearchViewPagerAdapter searchViewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ButterKnife.bind(this);
        toolbar.setTitle(getString(R.string.activity_title_search));
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {

        searchViewPagerAdapter = new SearchViewPagerAdapter(getSupportFragmentManager());

        Bundle bundle = new Bundle();
        SearchFragment fragment = new SearchFragment();
        bundle.putSerializable(FragmentArgs.SEARCH_TYPE, SearchUseCase.SPECIAL_BLEND);
        fragment.setArguments(bundle);
        searchViewPagerAdapter.addFragment(fragment, getString(R.string.search_tab_special_blend));

        bundle = new Bundle();
        fragment = new SearchFragment();
        bundle.putSerializable(FragmentArgs.SEARCH_TYPE, SearchUseCase.MATCH_PERCENTAGE);
        fragment.setArguments(bundle);
        searchViewPagerAdapter.addFragment(fragment, getString(R.string.search_tab_match_perc));

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

    public void refreshTabs(){
        searchViewPagerAdapter.refreshFragments();
    }

}
