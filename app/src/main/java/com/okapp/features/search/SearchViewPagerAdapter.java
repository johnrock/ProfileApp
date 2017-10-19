package com.okapp.features.search;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.okapp.config.OkAppApplication;
import com.okapp.data.helpers.LogHelper;
import com.okapp.domain.usecases.search.SearchUseCase;
import com.okapp.util.FragmentArgs;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchViewPagerAdapter extends FragmentPagerAdapter {

    private final List<SearchFragment> fragmentList;
    private final List<String> titleList;
    private final List<SearchUseCase> searchUseCaseList;
    private final LogHelper logHelper;

    public SearchViewPagerAdapter(FragmentManager fragmentManager,
                                  List<String> titleList,
                                  List<SearchUseCase> searchUseCaseList,
                                  LogHelper logHelper) {
        super(fragmentManager);
        this.fragmentList      = new ArrayList<>();
        this.titleList = titleList;
        this.searchUseCaseList = searchUseCaseList;
        this.logHelper = logHelper;
    }

    @Override
    public Fragment getItem(int position) {
        logHelper.debug(OkAppApplication.LOGTAG, "SearchViewPagerAdapter: getItem() *** position: " + position);

        Bundle bundle = new Bundle();
        SearchFragment fragment = new SearchFragment();
        bundle.putSerializable(FragmentArgs.SEARCH_TYPE, searchUseCaseList.get(position));
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        SearchFragment fragment = (SearchFragment)super.instantiateItem(container, position);
        logHelper.debug(OkAppApplication.LOGTAG, "SearchViewPagerAdapter: instantiateItem() *** position: " + position);
        fragmentList.add(fragment);
        return fragment;
    }

    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    public void refreshFragments() {
        for (SearchFragment searchFragment : fragmentList) {
            searchFragment.bind();
        }
    }
}
