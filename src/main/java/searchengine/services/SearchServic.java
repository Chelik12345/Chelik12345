package searchengine.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import searchengine.model.Index;
import searchengine.model.Page;
import searchengine.repository.IndexxRepository;
import searchengine.repository.LemmaRepository;

import java.util.List;

@Service
public class SearchServic {

    @Autowired
    private LemmaRepository lemmaRepository;

    @Autowired
    private IndexxRepository indexRepository;

    public List<Page> search(String query) {
        // Найдем все леммы, соответствующие запросу
        List<String> lemmas = lemmaRepository.findByLemmaContaining(query);

        // Получим все страницы, связанные с этими леммами через индекс
        return indexRepository.findPagesByLemma_LemmaIn(lemmas);
    }
}