package com.okapp.data.helpers;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface LikesHelper {

    boolean userIsLiked(String username);
    void likeUser(String username);
    void unlikeUser(String username);
}
