package searchengine.services;
import com.example.model.Lemma;
import com.example.model.Page;
import com.example.model.Index;
import com.example.repository.LemmaRepository;
import com.example.repository.IndexRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class IndexingService {

    private final LemmaService lemmaService;
    private final LemmaRepository lemmaRepository;
    private final IndexRepository indexRepository;

    public IndexingService(LemmaService lemmaService, LemmaRepository lemmaRepository, IndexRepository indexRepository) {
        this.lemmaService = lemmaService;
        this.lemmaRepository = lemmaRepository;
        this.indexRepository = indexRepository;
    }

    public void indexPage(Page page) {
        Map<String, Integer> lemmas = lemmaService.extractLemmas(page.getContent());
        lemmas.forEach((lemmaText, frequency) -> {
            Lemma lemma = lemmaRepository.findByLemmaAndSite(lemmaText, page.getSite())
                    .orElse(new Lemma(page.getSite(), lemmaText));
            lemma.setFrequency(lemma.getFrequency() + 1);
            lemmaRepository.save(lemma);

            Index index = new Index();
            index.setPage(page);
            index.setLemma(lemma);
            index.setRank(frequency);
            indexRepository.save(index);
        });
    }
}

