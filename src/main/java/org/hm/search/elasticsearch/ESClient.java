package org.hm.search.elasticsearch;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.hm.search.SearchClient;
import org.hm.search.SearchResult;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

public class ESClient implements SearchClient {

    private WebResource webResource;
    private ESConfig ESConfig;

    public ESClient(ESConfig ESConfig) {
        this.ESConfig = ESConfig;

        prepareClient();
    }

    private void prepareClient() {
        String baseUrl = "http://" + ESConfig.getHost() + ":" + ESConfig.getPort() + "/" + ESConfig.getIndex();

        webResource = Client.create()
            .resource(baseUrl);
    }

    @Override
    public SearchResult search(String index, String query) {

        ClientResponse esResponse = webResource.path(index).path("_search")
            .queryParam("q", query)
            .accept(MediaType.APPLICATION_JSON)
            .type(MediaType.APPLICATION_JSON)
            .get(ClientResponse.class);

        return getSearchResult(esResponse);
    }

    private SearchResult getSearchResult(ClientResponse esResponse) {

        if (esResponse.getStatus() == 200) {

            SearchResult searchResult = parseESResult(esResponse.getEntity(JSONObject.class));
            esResponse.close();

            return searchResult;

        } else if (esResponse.getStatus() == 404) {
            return SearchResult.error("not-found");

        } else if (esResponse.getStatus() == 500) {
            return SearchResult.error("invalid-query");
        }

        throw new RuntimeException("this wasn't supposed to happen");
    }

    private SearchResult parseESResult(JSONObject jsonResults) {
        JSONObject hits = jsonResults.optJSONObject("hits");
        int totalMatching = hits.optInt("total");

        JSONArray jsonArray = hits.optJSONArray("hits");
        List<JSONObject> results = new ArrayList<JSONObject>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject doc = jsonArray.optJSONObject(i).optJSONObject("_source");
            results.add(doc);
        }

        return SearchResult.success(results, totalMatching);
    }

}
