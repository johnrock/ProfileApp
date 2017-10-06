package com.okapp.domain.usecases.search;

import com.okapp.data.models.Profile;
import com.okapp.domain.usecases.UseCaseExecutor;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class UseCaseExecutorImpl implements UseCaseExecutor {

    SearchSpecialBlendUseCase searchSpecialBlendUseCase;
    SearchMatchPercentageUseCase searchMatchPercentageUseCase;

    public UseCaseExecutorImpl(SearchSpecialBlendUseCase searchSpecialBlendUseCase, SearchMatchPercentageUseCase searchMatchPercentageUseCase) {
        this.searchSpecialBlendUseCase = searchSpecialBlendUseCase;
        this.searchMatchPercentageUseCase = searchMatchPercentageUseCase;
    }


    @Override
    public Observable<List<Profile>> executeUseCaseFor(SearchUseCase searchUseCase) {

        switch (searchUseCase){
            case SPECIAL_BLEND:
                return searchSpecialBlendUseCase.execute();
            case MATCH_PERCENTAGE:
                return searchMatchPercentageUseCase.execute();
        }
        return null;
    }
}
