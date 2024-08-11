package searchengine.model;
@Service
public class SearchService {

    @Autowired
    private Lemmatizer lemmatizer;

    @Autowired
    private PageRepository pageRepository;

    public List<SearchResult> search(String query) {
        List<String> lemmas = lemmatizer.lemmatize(query);
        lemmas = filterCommonLemmas(lemmas);
        Collections.sort(lemmas, Comparator.comparingInt(this::getFrequency));

        List<Page> pages = findPagesByLemmas(lemmas);

        List<SearchResult> results = calculateRelevance(pages, lemmas);
        return results.stream()
                .sorted(Comparator.comparingDouble(SearchResult::getRelevance).reversed())
                .collect(Collectors.toList());
    }

    private List<String> filterCommonLemmas(List<String> lemmas) {
        // Фильтрация часто встречающихся лемм
        // Реализуйте фильтр на основе частотности
    }

    private int getFrequency(String lemma) {
        // Получение частоты встречаемости леммы
        // Реализуйте метод на основе данных из индекса
    }

    private List<Page> findPagesByLemmas(List<String> lemmas) {
        // Поиск страниц по списку лемм
        // Реализуйте логику поиска страниц
    }

    private List<SearchResult> calculateRelevance(List<Page> pages, List<String> lemmas) {
        // Рассчет релевантности для каждой страницы
        // Реализуйте метод на основе данных из индекса
    }
}
