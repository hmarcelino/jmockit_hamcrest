package org.hm.search.elasticsearch;

public class LocalESConfig extends ESConfig {

    public LocalESConfig() {
        super("localhost", 9200, "news");
    }

}
