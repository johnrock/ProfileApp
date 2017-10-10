package com.okapp.domain.usecases.search;

import com.okapp.data.helpers.LikesHelper;
import com.okapp.data.helpers.LogHelper;
import com.okapp.data.models.Profile;
import com.okapp.data.repositories.SearchRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
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

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        searchMatchPercentageUseCase = new SearchMatchPercentageUseCase(searchRepository, likesHelper, logHelper);

        when(searchRepository.byMatchPercentage()).thenReturn(Observable.just(profileList()));
    }

    @Test
    public void shouldSearchByMatchPercentageOnExecute(){
        searchMatchPercentageUseCase.execute();
        verify(searchRepository).byMatchPercentage();
    }

    @Test
    public void shouldReturnNoMoreThan6profiles() throws InterruptedException {

        when(likesHelper.userIsLiked(anyString())).thenReturn(true);

        TestObserver<List<Profile>> testObserver = searchMatchPercentageUseCase.execute().test();

        testObserver
                .assertNoErrors()
                .assertValueAt(0, list -> list.size() == 6);
    }

    @Test
    public void shouldOnlyReturnLikedUsers(){

        when(likesHelper.userIsLiked(BEEBOP)).thenReturn(true);
        when(likesHelper.userIsLiked(SLICKMUCHACHO)).thenReturn(true);
        when(likesHelper.userIsLiked(COOLAMIGO)).thenReturn(true);
        when(likesHelper.userIsLiked(DOPEGUY)).thenReturn(true);

        TestObserver<List<Profile>> testObserver = searchMatchPercentageUseCase.execute().test();

        testObserver
                .assertNoErrors()
                .assertValueAt(0, list -> list.size() == 4);

        List<Profile> profiles = testObserver.values().get(0);
        assertTrue(profiles.contains(new com.okapp.data.models.Profile(BEEBOP)));
        assertTrue(profiles.contains(new com.okapp.data.models.Profile(SLICKMUCHACHO)));
        assertTrue(profiles.contains(new com.okapp.data.models.Profile(COOLAMIGO)));
        assertTrue(profiles.contains(new com.okapp.data.models.Profile(DOPEGUY)));
    }

    @Test
    public void shouldOrderProfilesByMatchPercentageDescending(){
        when(likesHelper.userIsLiked(anyString())).thenReturn(true);
        TestObserver<List<Profile>> testObserver = searchMatchPercentageUseCase.execute().test();

        testObserver
                .assertNoErrors()
                .assertValueAt(0, list -> list.size() == 6);

        List<Profile> profileList = testObserver.values().get(0);
        assertEquals( new com.okapp.data.models.Profile(SWEETDARLING), profileList.get(0));
        assertEquals( new com.okapp.data.models.Profile(RADDUDE),      profileList.get(1));
        assertEquals( new com.okapp.data.models.Profile(CRAZYGIRL),    profileList.get(2));
        assertEquals( new com.okapp.data.models.Profile(COOLAMIGO),    profileList.get(3));
        assertEquals( new com.okapp.data.models.Profile(BEEBOP),       profileList.get(4));
        assertEquals( new com.okapp.data.models.Profile(JOEJOE),       profileList.get(5));
    }


    private List<Profile> profileList() {
        return Arrays.asList(
                new com.okapp.data.models.Profile(CRAZYGIRL, 9940),
                new com.okapp.data.models.Profile(DOPEGUY, 5050),
                new com.okapp.data.models.Profile(RADDUDE, 9950),
                new com.okapp.data.models.Profile(JOEJOE, 8875),
                new com.okapp.data.models.Profile(BEEBOP, 8975),
                new com.okapp.data.models.Profile(COOLAMIGO, 9200),
                new com.okapp.data.models.Profile(CUTEFOX, 6650),
                new com.okapp.data.models.Profile(SLICKMUCHACHO, 2525),
                new com.okapp.data.models.Profile(SWEETDARLING, 9999)
        );
    }
}