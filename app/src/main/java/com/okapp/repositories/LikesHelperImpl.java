package com.okapp.repositories;

import com.okapp.data.repositories.LikesHelper;
import com.okapp.domain.helpers.PreferencesHelper;

import java.util.Set;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class LikesHelperImpl implements LikesHelper {

    public static final String LIKES_PREFERENCEKEY = "com.okapp.likes.preferencekey";
    Set<String> likes;
    private PreferencesHelper preferencesHelper;

    public LikesHelperImpl(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
        likes = preferencesHelper.getStringSet(LIKES_PREFERENCEKEY);
    }

    @Override
    public boolean userIsLiked(String username) {
        return likes.contains(username);
    }

    @Override
    public synchronized void likeUser(String username) {
        likes.add(username);
        save();
    }

    @Override
    public synchronized void unlikeUser(String username) {
        likes.remove(username);
        save();
    }

    private void save() {
        preferencesHelper.putStringSet(LIKES_PREFERENCEKEY, likes);
    }
}
