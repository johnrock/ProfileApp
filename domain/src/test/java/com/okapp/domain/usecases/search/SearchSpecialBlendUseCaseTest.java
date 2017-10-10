package com.okapp.domain.usecases.search;

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
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author John Piser johnpiser@yahoo.com
 */
public class SearchSpecialBlendUseCaseTest {

    public static final String CRAZYGIRL = "crazygirl";
    public static final String DOPEGUY = "dopeguy";
    public static final String RADDUDE = "raddude";

    SearchSpecialBlendUseCase searchSpecialBlendUseCase;

    @Mock SearchRepository searchRepository;
    @Mock LogHelper logHelper;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        searchSpecialBlendUseCase = new SearchSpecialBlendUseCase(searchRepository, logHelper);

        when(searchRepository.bySpecialBlend()).thenReturn(Observable.just(makeObservableData()));
    }

    @Test
    public void shouldSearchBySpecialBlendOnExecute(){
        searchSpecialBlendUseCase.execute();
        verify(searchRepository).bySpecialBlend();
    }

    @Test
    public void shouldReturnProfileResults(){
        TestObserver<List<Profile>> testObserver = searchSpecialBlendUseCase.execute().test();

        testObserver
                .assertNoErrors()
                .assertValueAt(0, list -> list.size() == 3);
    }

    private List<Profile> makeObservableData() {
        List<com.okapp.data.models.Profile> list = new ArrayList<>();
        list.add(new com.okapp.data.models.Profile(CRAZYGIRL));
        list.add(new com.okapp.data.models.Profile(DOPEGUY));
        list.add(new com.okapp.data.models.Profile(RADDUDE));
        return list;
    }

}