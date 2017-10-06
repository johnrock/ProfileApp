package com.okapp.domain.usecases.search;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class UseCaseExecutorImplTest {

    UseCaseExecutorImpl useCaseExecutor;

    @Mock SearchSpecialBlendUseCase searchSpecialBlendUseCase;
    @Mock SearchMatchPercentageUseCase searchMatchPercentageUseCase;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        useCaseExecutor = new UseCaseExecutorImpl(searchSpecialBlendUseCase, searchMatchPercentageUseCase);
    }

    @Test
    public void shouldExecuteUseCaseForSearchSpecialBlend(){
        useCaseExecutor.executeUseCaseFor(SearchUseCase.SPECIAL_BLEND);
        verify(searchSpecialBlendUseCase).execute();
        verifyZeroInteractions(searchMatchPercentageUseCase);
    }

    @Test
    public void shouldExecuteUseCaseForSearchMatchPercentage(){
        useCaseExecutor.executeUseCaseFor(SearchUseCase.MATCH_PERCENTAGE);
        verify(searchMatchPercentageUseCase).execute();
        verifyZeroInteractions(searchSpecialBlendUseCase);
    }

}