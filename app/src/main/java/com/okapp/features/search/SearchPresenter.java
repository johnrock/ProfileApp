package com.okapp.features.search;


import com.okapp.models.Profile;
import com.okapp.util.SearchType;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface SearchPresenter {

    interface ViewLayer {
        void loadProfiles(List<Profile> profiles);
    }

    void bind(ViewLayer viewLayer, SearchType searchType);
    void unbind();
}
