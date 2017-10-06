package com.okapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.okapp.domain.helpers.PreferencesHelper;

import java.util.HashSet;
import java.util.Set;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class PreferencesHelperImpl  implements PreferencesHelper{

    private final SharedPreferences sharedPreferences;

    public PreferencesHelperImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_KEY, Context.MODE_PRIVATE);
    }

    @Override
    public void putStringSet(String key, Set<String> stringSet) {
        sharedPreferences.edit().remove(key).apply();
        sharedPreferences.edit().putStringSet(key, stringSet).apply();
    }

    @Override
    public Set<String> getStringSet(String key) {
        return sharedPreferences.getStringSet(key, new HashSet<>());
    }
}
