package com.okapp.features.search;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.okapp.R;
import com.okapp.config.OkAppApplication;
import com.okapp.data.helpers.LogHelper;
import com.okapp.domain.helpers.ImageHelper;
import com.okapp.domain.usecases.search.SearchUseCase;
import com.okapp.helpers.NetworkHelper;
import com.okapp.models.Profile;
import com.okapp.util.FragmentArgs;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchFragment extends Fragment implements SearchPresenter.ViewLayer,
                                                        SearchRecyclerViewAdapter.AdapterResponder {

    @Inject SearchPresenter searchPresenter;
    @Inject ImageHelper imageHelper;
    @Inject NetworkHelper networkHelper;
    @Inject LogHelper logHelper;

    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    @BindView(R.id.progressBar)   ProgressBar progressBar;
    SearchUseCase searchUseCase;
    GridLayoutManager gridLayoutManager;
    SearchRecyclerViewAdapter searchRecyclerViewAdapter;
    private ConnectivityManager connectivityManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        logHelper.debug(OkAppApplication.LOGTAG, "SearchFragment: onCreate()");

        Bundle arguments = getArguments();
        if (arguments != null) {
            searchUseCase = (SearchUseCase) arguments.get(FragmentArgs.SEARCH_TYPE);
        }
    }

    @Override
    public void onAttach(Context context) {
        FragmentActivity activity = getActivity();
        if(activity!= null){
            ((OkAppApplication)activity.getApplication()).getAppComponent().inject(this);
            connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        else{
            //TODO: handle this state in a base class
            return;
        }
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        logHelper.debug(OkAppApplication.LOGTAG, "SearchFragment: onCreateView()" + searchUseCase );

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        int gridColumns = getResources().getInteger(R.integer.search_results_grid_columns);
        gridLayoutManager = new GridLayoutManager(getActivity().getBaseContext(), gridColumns);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);

        bind();

        return view;
    }


    @Override
    public void onDestroyView() {
        logHelper.debug(OkAppApplication.LOGTAG, "SearchFragment: onDestroyView()" + searchUseCase );
        unbind();
        super.onDestroyView();
    }

    protected void bind() {
        if(networkHelper.networkAvailable(connectivityManager)){
            searchPresenter.bind(this, searchUseCase);
        }
        else{
            Toast.makeText(getContext(),R.string.message_check_internet_connection, Toast.LENGTH_LONG).show();
        }
    }

    protected void unbind() {
        searchPresenter.unbind();
    }

    @Override
    public void loadProfiles(List<Profile> profiles) {

        if(searchRecyclerViewAdapter == null){
            searchRecyclerViewAdapter = new SearchRecyclerViewAdapter(profiles, imageHelper, this);
            recyclerView.setAdapter(searchRecyclerViewAdapter);
        }
        else{
            searchRecyclerViewAdapter.updateData(profiles);
        }
    }

    @Override
    public void toggleLoading(boolean loading) {
        if(loading){
            progressBar.setVisibility(View.VISIBLE);
        }
        else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void refresh() {
        FragmentActivity activity = getActivity();
        if(activity != null)
        ((SearchActivity) activity).refreshTabs();
    }

    @Override
    public void onProfileTapped(String username) {
        searchPresenter.setLikedStateForUser(username);
    }

    @Override
    public boolean userIsLiked(String username) {
        return searchPresenter.userIsLiked(username);
    }


}
