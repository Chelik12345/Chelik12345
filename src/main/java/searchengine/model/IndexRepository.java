package searchengine.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndexRepository extends JpaRepository<Index, Long> {

    // Найти индекс по ID страницы и конкретной лемме
    @Query("SELECT i FROM Index i WHERE i.page.id = :pageId AND i.lemma = :lemma")
    Index findByPageIdAndLemma(@Param("pageId") Long pageId, @Param("lemma") String lemma);
    @Query("SELECT i FROM Index i WHERE i.lemma IN :lemmas")
    List<Index> findByLemmaIn(@Param("lemmas") List<Lemma> lemmas);
}

