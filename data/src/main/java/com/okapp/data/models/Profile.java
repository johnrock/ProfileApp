package com.okapp.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * @author John Piser johnpiser@yahoo.com
 *
 *
 *
 *
 *
 * "enemy": 834,
"relative": 0,
"last_login": 1437583808,
"gender": 2,
"location": {
"country_code": "US",
"city_name": "Brooklyn",
"country_name": "United States",
"state_name": "New York",
"state_code": "NY"
},
"userid": "5592586755333955055",
"match": 8715,
"gender_tags": [],
"liked": false,
"state_code": "NY",
"orientation": 1,
"country_name": "United States",
"photo": {
"full_paths": {
"large": "https://k3.okccdn.com/php/load_okc_image.php/images/0x0/640x560/36x36/684x684/0/15743311334557165678.jpg",
"small": "https://k2.okccdn.com/php/load_okc_image.php/images/0x0/60x60/36x36/684x684/0/15743311334557165678.jpg",
"medium": "https://k2.okccdn.com/php/load_okc_image.php/images/0x0/120x120/36x36/684x684/0/15743311334557165678.jpg",
"original": "https://k2.okccdn.com/php/load_okc_image.php/images/36x36/684x684/0/15743311334557165678.jpg"
},
"base_path": "36x36/684x684/2/15743311334557165678.jpg",
"original_size": {
"width": 720,
"height": 720
},
"ordinal": 0,
"id": "15743311334557165678",
"crop_rect": {
"height": 648,
"y": 36,
"width": 648,
"x": 36
},
"caption": "",
"thumb_paths": {
"large": "https://k0.okccdn.com/php/load_okc_image.php/images/640x560/640x560/36x36/684x684/2/15743311334557165678.jpg",
"desktop_match": "https://k0.okccdn.com/php/load_okc_image.php/images/400x400/400x400/36x36/684x684/2/15743311334557165678.jpg",
"small": "https://k0.okccdn.com/php/load_okc_image.php/images/60x60/60x60/36x36/684x684/2/15743311334557165678.jpg",
"medium": "https://k0.okccdn.com/php/load_okc_image.php/images/120x120/120x120/36x36/684x684/2/15743311334557165678.jpg"
}
},
"state_name": "New York",
"age": 27,
"country_code": "US",
"friend": 8099,
"is_online": 0,
"username": "bklyn2356",
"city_name": "Brooklyn",
"stoplight_color": "red",
"last_contact_time": [
0,
0
],
"orientation_tags": []

 */

public class Profile {

    @SerializedName("userid")
    private String userId;

    @SerializedName("username")
    private String userName;

    private int match;
    private int age;
    private Location location;
    private boolean liked;
    private Photo photo;

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

    public boolean isLiked() {
        return liked;
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
                ", liked=" + liked +
                ", photo=" + photo +
                '}';
    }
}
