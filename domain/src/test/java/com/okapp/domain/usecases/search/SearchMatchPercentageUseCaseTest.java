package com.okapp.domain.usecases.search;

import com.okapp.data.helpers.LikesHelper;
import com.okapp.data.helpers.LogHelper;
import com.okapp.data.models.Profile;
import com.okapp.data.repositories.SearchRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class SearchMatchPercentageUseCaseTest {


    public static final String CRAZYGIRL = "crazygirl";
    public static final String DOPEGUY = "dopeguy";
    public static final String RADDUDE = "raddude";
    public static final String JOEJOE = "joejoe";
    public static final String BEEBOP = "beebop";
    public static final String COOLAMIGO = "coolamigo";
    public static final String CUTEFOX = "cutefox";
    public static final String SLICKMUCHACHO = "slickmuchacho";
    public static final String SWEETDARLING = "sweetdarling";

    SearchMatchPercentageUseCase searchMatchPercentageUseCase;

    @Mock SearchRepository searchRepository;
    @Mock LogHelper logHelper;
    @Mock LikesHelper likesHelper;

    Observable<List<Profile>> observable;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        searchMatchPercentageUseCase = new SearchMatchPercentageUseCase(searchRepository, likesHelper, logHelper);

        when(searchRepository.byMatchPercentage()).thenReturn(Observable.just(makeObservableData()));

        observable = searchMatchPercentageUseCase.execute();
    }

    @Test
    public void shouldSearchSpecialBlendOnExecute(){
        verify(searchRepository).byMatchPercentage();
    }

    //TODO: Add more sophisticated testing of the conditions of this use case


    private List<Profile> makeObservableData() {
        List<com.okapp.data.models.Profile> list = new ArrayList<>();

        list.add(new com.okapp.data.models.Profile(CRAZYGIRL));
        list.add(new com.okapp.data.models.Profile(DOPEGUY));
        list.add(new com.okapp.data.models.Profile(RADDUDE));
        list.add(new com.okapp.data.models.Profile(JOEJOE));
        list.add(new com.okapp.data.models.Profile(BEEBOP));
        list.add(new com.okapp.data.models.Profile(COOLAMIGO));
        list.add(new com.okapp.data.models.Profile(CUTEFOX));
        list.add(new com.okapp.data.models.Profile(SLICKMUCHACHO));
        list.add(new com.okapp.data.models.Profile(SWEETDARLING));
        return list;
    }

}