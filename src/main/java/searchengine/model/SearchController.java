package searchengine.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private SearchService searchService;

    // Поиск по ключевым словам
    @GetMapping("/search")
    public ResponseEntity<List<Page>> search(@RequestParam String query) {
        List<Page> results = searchService.search(query);
        return ResponseEntity.ok(results);
    }

    // Метод для добавления страниц через API (необязательно, но полезно для тестирования)
    @PostMapping("/addPage")
    public ResponseEntity<String> addPage(@RequestBody Page page) {
        searchService.addPage(page);
        return ResponseEntity.ok("Страница добавлена успешно.");
    }
}
