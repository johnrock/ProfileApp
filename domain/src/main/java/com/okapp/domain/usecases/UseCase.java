package com.okapp.domain.usecases;

import io.reactivex.Observable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public interface UseCase<T> {
    Observable<T> execute();
}
