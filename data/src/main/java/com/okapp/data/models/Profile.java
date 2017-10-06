package com.okapp.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class Profile {

    @SerializedName("userid")
    private String userId;
    @SerializedName("username")
    private String userName;
    private int match;
    private int age;
    private Location location;
    private Photo photo;

    public Profile() {
    }

    //For Testing
    public Profile(String username) {
        this.userName =  username;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getMatch() {
        return match;
    }

    public int getAge() {
        return age;
    }

    public Location getLocation() {
        return location;
    }


    public Photo getPhoto() {
        return photo;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", match=" + match +
                ", age=" + age +
                ", location=" + location +
                ", photo=" + photo +
                '}';
    }
}
