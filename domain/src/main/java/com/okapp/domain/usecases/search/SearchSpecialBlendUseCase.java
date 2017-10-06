package com.okapp.domain.usecases.search;

import com.okapp.data.helpers.LogHelper;
import com.okapp.data.models.Profile;
import com.okapp.data.repositories.SearchRepository;
import com.okapp.domain.usecases.UseCase;
import com.okapp.domain.util.LibraryConstants;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author John Piser johnpiser@yahoo.com
 */

public class SearchSpecialBlendUseCase implements UseCase<List<Profile>>{

    SearchRepository searchRepository;
    private LogHelper logHelper;

    public SearchSpecialBlendUseCase(SearchRepository searchRepository, LogHelper logHelper) {
        this.searchRepository = searchRepository;
        this.logHelper = logHelper;
    }

    @Override
    public Observable<List<Profile>> execute() {
        logHelper.debug(LibraryConstants.LOGTAG, " Executing SearchSpecialBlendUseCase...");
        return searchRepository.bySpecialBlend();
    }

}
