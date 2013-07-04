package org.hm.search;

public class SearchService {

    private MonitorSystem monitorSystem;
    private Estatistics estatistics;
    private SearchClient client;

    public SearchService(MonitorSystem monitorSystem, Estatistics estatistics, SearchClient client) {
        this.monitorSystem = monitorSystem;
        this.estatistics = estatistics;
        this.client = client;
    }

    public SearchResult searchFor(String index, String query) {

        if (monitorSystem.isSearchEngineOff()) {
            return SearchResult.error("search-engine-off");
        }

        SearchResult searchResult = client.search(index, query);

        if (searchResult.success()) {
            estatistics.incCounter(index);
        }

        return searchResult;
    }
}
