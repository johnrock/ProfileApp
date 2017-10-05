package com.okapp.data.repositories;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface LikesHelper {

    boolean userIsLiked(String username);
    void likeUser(String username);
    void unlikeUser(String username);
}
