package com.okapp.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class Photo {

    @SerializedName("full_paths")
    private Map<String, String> fullPaths;

    public Map<String, String> getFullPaths() {
        return fullPaths;
    }

}
