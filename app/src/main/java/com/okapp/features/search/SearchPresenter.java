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
    }

    void bind(ViewLayer viewLayer, SearchUseCase searchUseCase);
    void unbind();
}
