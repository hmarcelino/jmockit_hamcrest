package org.hm.search.elasticsearch;

public class ESConfig {

    private String host = "localhost";
    private int port = 9200;
    private String index = "news";

    public ESConfig() {
    }

    public ESConfig(String host, int port, String index) {
        this.host = host;
        this.port = port;
        this.index = index;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getIndex() {
        return index;
    }
}
