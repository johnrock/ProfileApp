package com.okapp.features.search;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface SearchPresenter {

    interface ViewLayer {

    }

    void bind(ViewLayer viewLayer);
    void unbind();
}
