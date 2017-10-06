package com.okapp.domain.usecases;

import com.okapp.data.models.Profile;
import com.okapp.domain.usecases.search.SearchUseCase;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface UseCaseExecutor {

    Observable<List<Profile>> executeUseCaseFor(SearchUseCase searchUseCase);
}
