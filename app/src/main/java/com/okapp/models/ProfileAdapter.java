package com.okapp.models;

import com.okapp.data.models.Location;
import com.okapp.data.models.Photo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class ProfileAdapter {

    public static final String COMMA = ", ";
    public static final String PERCENT_SYMBOL = "%";
    public static final String LARGE = "large";

    public Profile adapt(com.okapp.data.models.Profile p) {
        Profile profile = null;

        if (p != null) {
            profile = new Profile();
            profile.setUserName(p.getUserName());
            profile.setAge(String.valueOf(p.getAge()));
            Location location = p.getLocation();
            if (location != null) {
                StringBuilder builder = new StringBuilder(location.getCityName())
                        .append(COMMA)
                        .append(location.getStateCode());
                profile.setLocation(builder.toString());
            }
            profile.setMatchPercentage(Math.round(p.getMatch() / 100) + PERCENT_SYMBOL);
            Photo photo = p.getPhoto();
            if (photo != null) {
                profile.setImageUrl(photo.getFullPaths().get(LARGE));
            }
        }
        return profile;
    }

    public List<Profile> adapt(List<com.okapp.data.models.Profile> profileList) {
        if (profileList != null) {
            List<Profile> resultList = new ArrayList<>();
            for (com.okapp.data.models.Profile profile : profileList) {
                resultList.add(adapt(profile));
            }
            return resultList;
        }
        return null;
    }
}
