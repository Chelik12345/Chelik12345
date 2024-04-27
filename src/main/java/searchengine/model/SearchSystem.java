package searchengine.model;

import java.util.Map;

public class SearchSystem {
    private Indexer indexer;

    public SearchSystem(Indexer indexer) {
        this.indexer = indexer;
    }
    public String search(String query) {
        StringBuilder result = new StringBuilder();
        Map<String, String> index = indexer.getIndex();
        for (Map.Entry<String, String> entry : index.entrySet()) {
            String url = entry.getKey();
            String content = entry.getValue();
            if (content.contains(query)) {
                result.append("Found at: ").append(url).append("\n");
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Indexer indexer = new Indexer();
        // Ваш код индексации страниц

        SearchSystem searchSystem = new SearchSystem(indexer);
        String query = "search query"; // Замените на ваш запрос
        String searchResult = searchSystem.search(query);
        System.out.println("Search results for query '" + query + "':\n" + searchResult);
    }
}

