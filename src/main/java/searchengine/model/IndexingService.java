package searchengine.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexingService {

    @Autowired
    private WebCrawler webCrawler;  // Ваш веб-краулер

    public boolean startIndexing() {
        try {
            webCrawler.startCrawling();  // Запуск вашего краулера для индексирования
            return true;
        } catch (Exception e) {
            // Логирование ошибки
            System.err.println("Ошибка при индексировании: " + e.getMessage());
            return false;
        }
    }
}

