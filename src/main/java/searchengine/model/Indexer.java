package searchengine.model;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Indexer {
    private Map<String, Set<String>> index1;
    private Map<String, String> index;


    public Indexer() {
        index = new HashMap<>();
    }
    public void indexPage(String url, String content) {
        index.put(url, content);
    }

    public Map<String, String> getIndex() {
        return index;
    }

    public void index(String url, String content) {
        // Разбиваем содержимое страницы на слова
        String[] words = content.split("\\s+");

        // Удаляем лишние символы из слов и приводим к нижнему регистру
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase();
        }


        // Индексируем слова
        for (String word : words) {
            if (!index.containsKey(word)) {
                index1.put(word, new HashSet<>());
            }
            index1.get(word).add(url);
        }
    }

    public Set<String> search(String query) {
        String cleanedQuery = query.replaceAll("[^a-zA-Zа-яА-Я]", "").toLowerCase();
        return index1.getOrDefault(cleanedQuery, new HashSet<>());
    }
    public static void main(String[] args) {
        // Пример использования
        Indexer indexer = new Indexer();
        indexer.index("https://www.example.com/page1", "This is an example page for indexing");
        indexer.index("https://www.example.com/page2", "Indexing is an important part of search engines");

        // Поиск
        Set<String> results = indexer.search("indexing");
        if (!results.isEmpty()) {
            System.out.println("Результаты поиска:");
            for (String result : results) {
                System.out.println(result);
            }
        } else {
            System.out.println("Ничего не найдено.");
        }
    }
}
