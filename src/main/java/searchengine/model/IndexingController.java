package searchengine.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndexingController {

    @Autowired
    private IndexingService indexingService;  // Сервис для индексирования

    @GetMapping("/startIndexing")
    public ResponseEntity<String> startIndexing() {
        try {
            boolean isIndexingStarted = indexingService.startIndexing();
            if (isIndexingStarted) {
                return ResponseEntity.ok("Индексация успешно запущена.");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка запуска индексирования.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла ошибка: " + e.getMessage());
        }
    }
}
