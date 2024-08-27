package searchengine.model;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchengine.repository.IndexxRepository;
import searchengine.repository.LemmaRepository;
import searchengine.repository.PageRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SearchService {

    @Autowired
    private LemmaRepository lemmaRepository;

    @Autowired
    private PageRepository pageRepository;

//    @Autowired
    private final IndexxRepository indexxRepository;

    public List<SearchResult> search(String query) {
        // 1. Разбить запрос на леммы
        List<String> lemmas = lemmatize(query);

        // 2. Исключить часто встречающиеся леммы
        lemmas = filterCommonLemmas(lemmas);

        // 3. Найти страницы по леммам
        List<Page> pages = findPagesByLemmas(lemmas);

        // 4. Рассчитать релевантность
        List<SearchResult> results = calculateRelevance(pages, lemmas);

        // 5. Сортировать результаты по релевантности
        results.sort(Comparator.comparingDouble(SearchResult::getRelevance).reversed());

        return results;
    }

    private List<String> lemmatize(String query) {
        // Реализуйте лемматизацию, используя код из предыдущих этапов
        // Например, разбить на слова и исключить междометия, союзы и т.д.
        return Arrays.asList(query.split("\\s+"));
    }

    private List<String> filterCommonLemmas(List<String> lemmas) {
        // Исключить леммы, встречающиеся на слишком большом количестве страниц
        // Используйте LemmaRepository для поиска частотности и фильтрации
        return lemmas.stream()
                .filter(lemma -> !isCommonLemma(lemma))
                .collect(Collectors.toList());
    }

    private boolean isCommonLemma(String lemma) {
        int frequencyThreshold = 1000;  // Задайте порог частотности
        Lemma foundLemma = lemmaRepository.findByLemma(lemma);
        return foundLemma != null && foundLemma.getFrequency() > frequencyThreshold;
    }

    private List<Page> findPagesByLemmas(List<String> lemmas) {
        // Найти страницы по списку лемм
        List<Lemma> foundLemmas = lemmaRepository.findByLemmaIn(lemmas);

//        List<Index> indices = indexRepository.findByLemmaIn(foundLemmas);
        List<Index> indices = null;

        return indices.stream()
                .map(Index::getPage)
                .distinct()
                .collect(Collectors.toList());
    }

    private List<SearchResult> calculateRelevance(List<Page> pages, List<String> lemmas) {
        List<SearchResult> results = new ArrayList<>();

        double maxRelevance = 0;

        for (Page page : pages) {
            double relevance = calculatePageRelevance(page.getId(), lemmas);
            maxRelevance = Math.max(maxRelevance, relevance);
            results.add(new SearchResult(page.getPath(), page.getTitle(), "", relevance));
        }

        for (SearchResult result : results) {
            result.setRelevance(result.getRelevance() / maxRelevance);
        }

        return results;
    }

    private double calculatePageRelevance(Long pageId, List<String> lemmas) {
        double relevance = 0;
        for (String lemma : lemmas) {
//            Index index = indexRepository.findByPageIdAndLemma(pageId, lemma);
            Index index = null;
            relevance += index != null ? index.getRank() : 0;
        }
        return relevance;
    }
}
