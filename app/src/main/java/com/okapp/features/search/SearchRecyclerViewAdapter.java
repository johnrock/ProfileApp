package com.okapp.features.search;

import android.support.v7.widget.CardView;
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

    interface AdapterResponder {
        void onProfileTapped(String username);
        boolean userIsLiked(String username);
    }

    List<Profile> profiles;
    ImageHelper imageHelper;
    AdapterResponder adapterResponder;


    public SearchRecyclerViewAdapter(List<Profile> profiles,
                                     ImageHelper imageHelper,
                                     AdapterResponder adapterResponder) {
        this.profiles = profiles;
        this.imageHelper = imageHelper;
        this.adapterResponder = adapterResponder;
    }

    @Override
    public ProfileHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile, parent, false);
        return new ProfileHolder(view, imageHelper, adapterResponder);
    }

    @Override
    public void onBindViewHolder(ProfileHolder holder, int position) {
        holder.bind(profiles.get(position));
    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public void updateData(List<Profile> profiles) {
        this.profiles = profiles;
        notifyDataSetChanged();
    }

    public static class ProfileHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final ImageHelper imageHelper;
        private final AdapterResponder adapterResponder;
        private String username;

        @BindView(R.id.card_view) CardView cardView;
        @BindView(R.id.name) TextView name;
        @BindView(R.id.image) ImageView imageView;
        @BindView(R.id.age)   TextView age;
        @BindView(R.id.location) TextView location;
        @BindView(R.id.matchpercentage) TextView matchPercentage;

        public ProfileHolder(View itemView,
                             ImageHelper imageHelper,
                             AdapterResponder adapterResponder) {
            super(itemView);
            this.imageHelper = imageHelper;
            this.adapterResponder = adapterResponder;

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            adapterResponder.onProfileTapped(username);
        }

        public void bind(Profile profile) {
            this.username = profile.getUserName();
            name.setText(profile.getUserName());
            imageHelper.loadImage(imageView,profile.getImageUrl());
            age.setText(profile.getAge());
            location.setText(profile.getLocation());
            matchPercentage.setText(profile.getMatchPercentage());

            setBackgroundColor();
        }

        private void setBackgroundColor() {
            if(adapterResponder.userIsLiked(username)){
                cardView.setBackgroundColor(imageView.getResources().getColor(R.color.highlightCard));
            }
            else{
                cardView.setBackgroundColor(imageView.getResources().getColor(R.color.white));
            }
        }
    }
}