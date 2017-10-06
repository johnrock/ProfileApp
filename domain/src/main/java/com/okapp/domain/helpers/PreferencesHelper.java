package com.okapp.domain.helpers;

import java.util.Set;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface PreferencesHelper {

    public static final String PREFERENCE_KEY = "com.okapp";


    void putStringSet(String key, Set<String> stringSet);
    Set<String> getStringSet(String key);

}