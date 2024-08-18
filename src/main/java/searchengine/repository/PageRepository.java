package searchengine.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import searchengine.model.Page;
import searchengine.model.Site;

import java.util.List;
@Repository
public interface PageRepository extends JpaRepository<Page, Long> {

    // Найти все страницы, где встречается хотя бы одна из лемм
    @Query("SELECT p FROM Page p JOIN Index i ON p.id = i.page.id JOIN Lemma l ON i.lemma.id = l.id WHERE l.lemma IN :lemmas")
    List<Page> findPagesByLemmas(@Param("lemmas") List<String> lemmas);

    // Найти все страницы, на которых встречается конкретная лемма
    @Query("SELECT p FROM Page p JOIN Index i ON p.id = i.page.id WHERE i.lemma.lemma = :lemma")
    List<Page> findPagesByLemma(@Param("lemma") String lemma);
    boolean existsByPathAndSite(String path, Site site);

    // Найти страницы по их ID
    List<Page> findByIdIn(List<Long> pageIds);
}

