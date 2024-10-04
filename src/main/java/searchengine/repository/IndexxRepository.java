package searchengine.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import searchengine.model.Index;
import searchengine.model.Page;

import java.util.List;

public interface IndexxRepository extends JpaRepository<Index, Long> {
    // Изменяем название метода на корректное

}


