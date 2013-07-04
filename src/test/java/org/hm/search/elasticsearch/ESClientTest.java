package org.hm.search.elasticsearch;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hm.search.SearchClient;
import org.hm.search.SearchResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static junit.framework.Assert.assertTrue;

public class ESClientTest {

    private SearchClient searchClient;

    @BeforeMethod
    public void setUp() throws Exception {
        searchClient = new ESClient(new LocalESConfig());
    }

    @Test
    public void can_find_vitor_gaspar_news() throws Exception {
        SearchResult results = searchClient.search("news", "vítor gaspar");

        boolean found = true;

        for (JSONObject jsonObject : results.getResults()) {
            if (!jsonObject.toString().toLowerCase().contains("vítor gaspar")) {
                found = false;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    public void all_news_of_vitor_gaspar_fullfill_the_requirements() throws Exception {
        SearchResult results = searchClient.search("news", "\"vitor gaspar\"");

        boolean hasTagPolitics = true;

        for (JSONObject jsonObject : results.getResults()) {
            if (!jsonObject.optJSONArray("tags").toString().contains("politics")) {
                hasTagPolitics = false;
                break;
            }
        }

        assertTrue(hasTagPolitics);
    }

    private Matcher<? super SearchResult> hasAtLeastOneResultContaining(final String text) {

        return new TypeSafeMatcher<SearchResult>() {
            @Override
            protected boolean matchesSafely(SearchResult results) {

                for (JSONObject doc : results.getResults()) {
                    String document = doc.toString();

                    if (document.toLowerCase().contains(text))
                        return true;
                }

                return false;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("I expected to find a document containing \"" + text + "\"");
            }
        };
    }

    private Matcher<? super SearchResult> allResultsHaveTag(final String tag) {
        return new TypeSafeMatcher<SearchResult>() {

            JSONObject failingDoc;

            @Override
            protected boolean matchesSafely(SearchResult results) {
                for (JSONObject doc : results.getResults()) {
                    JSONArray tags = doc.optJSONArray("tags");

                    if (!tags.toString().contains(tag)) {
                        failingDoc = doc;
                        return false;
                    }
                }

                return true;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText(
                    "Expected that all docs have the tag \"" + tag + "\" " +
                        "but the doc " + failingDoc.optInt("id") + " doesn't have");
            }
        };
    }

}
