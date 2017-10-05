package com.okapp.repositories;

import com.okapp.data.repositories.LikesHelper;

import java.util.HashSet;
import java.util.Set;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class LikesHelperImpl implements LikesHelper {

    Set<String> likes;

    public LikesHelperImpl() {
        likes = new HashSet<>();
    }

    @Override
    public boolean userIsLiked(String username) {
        return likes.contains(username);
    }

    @Override
    public void likeUser(String username) {
        likes.add(username);
    }

    @Override
    public void unlikeUser(String username) {
        likes.remove(username);
    }
}
