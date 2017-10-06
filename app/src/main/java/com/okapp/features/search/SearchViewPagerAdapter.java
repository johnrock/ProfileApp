package com.okapp.features.search;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchViewPagerAdapter extends FragmentPagerAdapter {

    private final List<SearchFragment> fragmentList = new ArrayList<>();
    private final List<String> titleList      = new ArrayList<>();

    public SearchViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    public void addFragment(SearchFragment fragment, String title){
        fragmentList.add(fragment);
        titleList.add(title);
    }

    public void refreshFragments() {
        for (SearchFragment searchFragment : fragmentList) {
            searchFragment.bind();
        }
    }
}
