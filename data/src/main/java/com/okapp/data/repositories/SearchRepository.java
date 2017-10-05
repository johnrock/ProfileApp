package com.okapp.data.repositories;

import com.okapp.data.models.Profile;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface SearchRepository {


    Observable<List<Profile>> bySpecialBlend();

    Observable<List<Profile>> byMatchPercentage();

}
