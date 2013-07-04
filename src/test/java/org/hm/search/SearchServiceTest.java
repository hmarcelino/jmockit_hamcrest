package org.hm.search;

import mockit.*;
import org.codehaus.jettison.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class SearchServiceTest {

    @Mocked
    MonitorSystem monitorSystem;

    @Mocked
    Estatistics estatistics;

    @Mocked
    SearchClient searchClient;

    SearchService searchService;

    @BeforeMethod
    public void setUp() throws Exception {
        searchService = new SearchService(monitorSystem, estatistics, searchClient);
    }

    @Test
    public void can_make_search() {

        new MockUp<SearchResult>() {
            @Mock
            public SearchResult success(List<JSONObject> jsonObject, int totalMatching) {
                return Deencapsulation.newInstance(SearchResult.class);
            }
        };

        new Expectations() {
            {
                searchClient.search("news", "a query string");
                result = SearchResult.success(new ArrayList<JSONObject>(), 0);
            }
        };

        SearchResult searchResult = searchService.searchFor("news", "a query string");
        assertTrue(searchResult.success(), "Search result returned error " + searchResult.getError());

        new Verifications() {
            {
                String index;
                estatistics.incCounter(index = withCapture());
                times = 1;

                assertEquals(index, "news");
            }
        };
    }


    @Test
    public void return_error_if_search_engine_is_off()
        throws Exception {

        new Expectations() {
            {
                onInstance(monitorSystem).isSearchEngineOff();
                result = true;
            }
        };

        SearchResult searchResult = searchService.searchFor("news", "a query string");
        assertFalse(searchResult.success());
        assertEquals(searchResult.getError(), "search-engine-off");

        new Verifications() {
            {
                searchClient.search(anyString, anyString);
                times = 0;
            }
        };
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void throw_exception_when_initializing_service() {

        new MockUp<SearchService>() {
            @Mock
            public void $init(MonitorSystem monitor, Estatistics estatistics, SearchClient client) {
                throw new RuntimeException("error");
            }
        };

        new SearchService(monitorSystem, estatistics, searchClient);
    }
}
