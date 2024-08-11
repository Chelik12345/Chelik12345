package searchengine.services;
import searchengine.model.Page;
import searchengine.model.Lemma;
import com.example.repository.PageRepository;
import com.example.repository.LemmaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final LemmaService lemmaService;
    private final LemmaRepository lemmaRepository;
    private final PageRepository pageRepository;

    public SearchService(LemmaService lemmaService, LemmaRepository lemmaRepository, PageRepository pageRepository) {
        this.lemmaService = lemmaService;
        this.lemmaRepository = lemmaRepository;
        this.pageRepository = pageRepository;
    }

    public List<Page> search(String query) {
        Map<String, Integer> queryLemmas = lemmaService.extractLemmas(query);
        // Логика поиска страниц по леммам
        // Подсчет релевантности и сортировка результатов
    }
}
