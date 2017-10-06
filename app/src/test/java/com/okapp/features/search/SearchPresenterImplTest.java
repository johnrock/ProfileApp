package com.okapp.features.search;

import com.okapp.data.helpers.LogHelper;
import com.okapp.domain.usecases.UseCaseExecutor;
import com.okapp.domain.usecases.search.SearchUseCase;
import com.okapp.models.Profile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


/**
 * @author John Piser johnpiser@yahoo.com
 */
public class SearchPresenterImplTest {

    public static final String CRAZYGIRL = "crazygirl";
    public static final String DOPEGUY = "dopeguy";
    public static final String RADDUDE = "raddude";

    SearchPresenterImpl searchPresenter;

    @Mock LogHelper logHelper;
    @Mock SearchPresenter.ViewLayer viewLayer;
    @Mock UseCaseExecutor useCaseExecutor;
    @Mock Observable<List<com.okapp.data.models.Profile>> mockObservable;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        searchPresenter = new SearchPresenterImpl(
                logHelper,
                Schedulers.trampoline(),
                Schedulers.trampoline(),
                useCaseExecutor);

        when(useCaseExecutor.executeUseCaseFor(any(SearchUseCase.class)))
                                     .thenReturn(Observable.just(makeObservableData()));

    }

    private List<com.okapp.data.models.Profile> makeObservableData() {
        List<com.okapp.data.models.Profile> list = new ArrayList<>();
        list.add(new com.okapp.data.models.Profile(CRAZYGIRL));
        list.add(new com.okapp.data.models.Profile(DOPEGUY));
        list.add(new com.okapp.data.models.Profile(RADDUDE));
        return list;
    }

    @Test
    public void shouldBind(){
        searchPresenter.bind(viewLayer, SearchUseCase.SPECIAL_BLEND);
        assertEquals(searchPresenter.viewLayer, viewLayer);
    }

    @Test
    public void shouldUnBind(){
        searchPresenter.bind(viewLayer, SearchUseCase.SPECIAL_BLEND);
        searchPresenter.unbind();

        assertTrue(searchPresenter.compositeDisposable.isDisposed());
    }

    @Test
    public void shouldToggleLoadingOnFromBind(){
        searchPresenter.bind(viewLayer, SearchUseCase.SPECIAL_BLEND);
        verify(viewLayer).toggleLoading(true);
    }

    @Test
    public void shouldToggleLoadingOffAfterShowingData(){
        SearchPresenter.ViewLayer mockViewLayer = Mockito.mock(SearchPresenter.ViewLayer.class);
        searchPresenter.viewLayer = mockViewLayer;
        searchPresenter.showData(makeObservableData());
        verify(mockViewLayer).toggleLoading(false);
    }

    @Test
    public void shouldExecuteSearchSpecialBlendOnBind(){
        searchPresenter.bind(viewLayer, SearchUseCase.SPECIAL_BLEND);
        verify(useCaseExecutor).executeUseCaseFor(SearchUseCase.SPECIAL_BLEND);
    }

    @Test
    public void shouldExecuteSearchMatchPercentageOnBind(){
        searchPresenter.bind(viewLayer, SearchUseCase.MATCH_PERCENTAGE);
        verify(useCaseExecutor).executeUseCaseFor(SearchUseCase.MATCH_PERCENTAGE);
    }

    @Test
    public void shouldShowDataAfterExecutingSearchSpecialBlend(){

        ArgumentCaptor<List<Profile>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        searchPresenter.bind(viewLayer, SearchUseCase.SPECIAL_BLEND);
        verifyShowData(argumentCaptor);
    }

    @Test
    public void shouldShowDataAfterExecutingSearchMatchPercentage(){

        ArgumentCaptor<List<Profile>> argumentCaptor = ArgumentCaptor.forClass(List.class);
        searchPresenter.bind(viewLayer, SearchUseCase.MATCH_PERCENTAGE);
        verifyShowData(argumentCaptor);
    }

    private void verifyShowData(ArgumentCaptor<List<Profile>> argumentCaptor) {

        verify(viewLayer).loadProfiles(argumentCaptor.capture());

        List<Profile> profiles = argumentCaptor.getValue();
        assertTrue(profiles.size() == 3);
        for(int i=0; i< profiles.size(); i++){
            Profile profile = profiles.get(i);
            switch(i){
                case 0:
                    assertEquals(CRAZYGIRL, profile.getUserName());
                    break;
                case 1:
                    assertEquals(DOPEGUY, profile.getUserName());
                    break;
                case 2:
                    assertEquals(RADDUDE, profile.getUserName());
                    break;
            }
        }
    }


}