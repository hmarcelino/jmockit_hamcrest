package org.hm.search;

public interface SearchClient {

    SearchResult search(String index, String query);
}
