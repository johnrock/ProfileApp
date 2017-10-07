package com.okapp.features.search;


import com.okapp.models.Profile;
import com.okapp.domain.usecases.search.SearchUseCase;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface SearchPresenter {

    interface ViewLayer {
        void loadProfiles(List<Profile> profiles);
        void toggleLoading(boolean loading);
        void refresh();
    }

    void bind(ViewLayer viewLayer, SearchUseCase searchUseCase);
    void unbind();
    void setLikedStateForUser(String username);
    boolean userIsLiked(String username);
}
