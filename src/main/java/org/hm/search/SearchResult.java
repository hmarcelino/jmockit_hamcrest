package org.hm.search;

import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {

    private List<JSONObject> results = new ArrayList<JSONObject>();
    private int totalMatching = 0;

    private String error;

    public static SearchResult success(List<JSONObject> results, int totalMatching) {
        SearchResult searchResult = new SearchResult();
        searchResult.results = results;
        searchResult.totalMatching = totalMatching;

        return searchResult;
    }

    public static SearchResult error(String error) {
        SearchResult searchResult = new SearchResult();
        searchResult.error = error;

        return searchResult;
    }

    private SearchResult() {

    }

    public boolean success() {
        return error == null;
    }

    public List<JSONObject> getResults() {
        return results;
    }

    public int getTotalMatching() {
        return totalMatching;
    }

    public String getError() {
        return error;
    }
}
