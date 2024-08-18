package searchengine.model;
public class SearchResult {
    private String uri;
    private String title;
    private String snippet;
    private double relevance;

    public SearchResult(String uri, String title, String snippet, double relevance) {
        this.uri = uri;
        this.title = title;
        this.snippet = snippet;
        this.relevance = relevance;
    }

    public String getUri() {
        return uri;
    }

    public String getTitle() {
        return title;
    }

    public String getSnippet() {
        return snippet;
    }

    public double getRelevance() {
        return relevance;
    }

    public void setRelevance(double relevance) {
        this.relevance = relevance;
    }
}
