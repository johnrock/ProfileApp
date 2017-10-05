package com.okapp.features.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.okapp.R;
import com.okapp.domain.helpers.ImageHelper;
import com.okapp.models.Profile;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchRecyclerViewAdapter extends RecyclerView.Adapter<SearchRecyclerViewAdapter.ProfileHolder> {

    List<Profile> profiles;
    ImageHelper imageHelper;

    public SearchRecyclerViewAdapter(List<Profile> profiles, ImageHelper imageHelper) {
        this.profiles = profiles;
        this.imageHelper = imageHelper;
    }

    @Override
    public ProfileHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        return new ProfileHolder(view, imageHelper);
    }

    @Override
    public void onBindViewHolder(ProfileHolder holder, int position) {
        holder.bind(profiles.get(position));
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public static class ProfileHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ImageHelper imageHelper;

        @BindView(R.id.name) TextView name;
        @BindView(R.id.image) ImageView imageView;
        @BindView(R.id.age)   TextView age;
        @BindView(R.id.location) TextView location;
        @BindView(R.id.matchpercentage) TextView matchPercentage;

        public ProfileHolder(View itemView, ImageHelper imageHelper) {
            super(itemView);
            this.imageHelper = imageHelper;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }


        public void bind(Profile profile) {
            name.setText(profile.getUserName());
            imageHelper.loadImage(imageView,profile.getImageUrl());
            age.setText(profile.getAge());
            location.setText(profile.getLocation());
            matchPercentage.setText(profile.getMatchPercentage());
        }

        @Override
        public void onClick(View v) {

        }
    }
}
