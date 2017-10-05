package com.okapp.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class Location {

    @SerializedName("city_name")
    private String cityName;

    @SerializedName("state_name")
    private String stateName;

    @SerializedName("state_code")
    private String stateCode;

    public String getCityName() {
        return cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public String getStateCode() {
        return stateCode;
    }
}
