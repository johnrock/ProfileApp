package com.okapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 *
 *
 *
 */

public class ProfileResponse {

    @SerializedName("total_matches")
    private int totalMatches;

    private List<Profile> data;

    public int getTotalMatches() {
        return totalMatches;
    }

    public List<Profile> getData() {
        return data;
    }


}
