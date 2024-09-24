package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import searchengine.model.Lemma;
import java.util.List;
@Repository
public interface LemmaRepository extends JpaRepository<Lemma, Long> {
    // В вашем репозитории LemmaRepository
    List<Lemma> findByLemmaIn(List<String> lemmas);
    Lemma findByLemma(String lemma);  // Найти лемму по значению
    List<String> findByLemmaContaining(String lemmas);

}

