package com.okapp.features.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.okapp.R;
import com.okapp.data.models.Profile;
import com.okapp.domain.helpers.ImageHelper;

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

        public ProfileHolder(View itemView, ImageHelper imageHelper) {
            super(itemView);
            this.imageHelper = imageHelper;
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }


        public void bind(Profile profile) {
            name.setText(profile.getUserName());
            imageHelper.loadImage(imageView,profile.getPhoto().getFullPaths().get("large"));
        }

        @Override
        public void onClick(View v) {

        }
    }
}
