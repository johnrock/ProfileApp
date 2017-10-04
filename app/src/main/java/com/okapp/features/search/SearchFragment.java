package com.okapp.features.search;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.okapp.R;
import com.okapp.config.OkAppApplication;
import com.okapp.data.models.Profile;
import com.okapp.domain.helpers.ImageHelper;
import com.okapp.util.FragmentArgs;
import com.okapp.util.SearchType;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchFragment extends Fragment implements SearchPresenter.ViewLayer{


    @Inject SearchPresenter searchPresenter;
    @Inject ImageHelper imageHelper;
    @BindView(R.id.recyclerView)      RecyclerView recyclerView;

    SearchType searchType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null) {
            searchType = (SearchType) arguments.get(FragmentArgs.SEARCH_TYPE);
        }

    }

    @Override
    public void onAttach(Context context) {
        FragmentActivity activity = getActivity();
        if(activity!= null){
            ((OkAppApplication)activity.getApplication()).getAppComponent().inject(this);
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

        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }


    @Override
    public void onStart() {
        super.onStart();
        searchPresenter.bind(this, searchType);
    }

    @Override
    public void onStop() {
        searchPresenter.unbind();
        super.onStop();
    }

    @Override
    public void loadProfiles(List<Profile> profiles) {

        SearchRecyclerViewAdapter searchRecyclerViewAdapter = new SearchRecyclerViewAdapter(profiles, imageHelper);
        recyclerView.setAdapter(searchRecyclerViewAdapter);
    }
}
