package searchengine.model;

import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private List<Page> pages = new ArrayList<>();  // Хранение страниц в памяти

    // Метод для поиска по ключевым словам
    public List<Page> search(String query) {
        List<Page> results = new ArrayList<>();
        String[] keywords = query.toLowerCase().split("\\s+");  // Разбиваем запрос на слова

        for (Page page : pages) {
            for (String keyword : keywords) {
                // Проверяем, содержится ли ключевое слово в содержимом или заголовке страницы
                if (page.getContent().toLowerCase().contains(keyword) || page.getTitle().toLowerCase().contains(keyword)) {
                    results.add(page);
                    break;  // Если нашли совпадение, добавляем страницу в результаты
                }
            }
        }

        return results;  // Возвращаем список страниц, которые соответствуют запросу
    }

    // Метод для добавления страниц в список
    public void addPage(Page page) {
        pages.add(page);
    }
}
