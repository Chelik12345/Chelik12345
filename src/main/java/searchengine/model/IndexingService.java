package searchengine.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexingService {

    @Autowired
    private WebCrawler webCrawler;  // Ваш веб-краулер

    public boolean startIndexing() {
        try {
            // Список URL-ов для индексирования
            List<String> urlsToIndex = List.of(
                    "https://www.lenta.ru",
                    "https://www.skillbox.ru",
                    "https://www.playback.ru"
                    // Добавьте другие URL для индексирования
            );

            // Индексация каждой страницы
            for (String url : urlsToIndex) {
                webCrawler.indexPage(url);  // Индексируем каждую страницу
            }
            return true;
        } catch (Exception e) {
            // Логирование ошибки
            System.err.println("Ошибка при индексировании: " + e.getMessage());
            return false;
        }
    }
}

