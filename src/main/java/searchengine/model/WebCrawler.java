package searchengine.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class WebCrawler {

    public void indexPage(String url) {
        try {
            // Подключаемся к странице и загружаем её содержимое с помощью JSoup
            Document doc = Jsoup.connect(url).get();

            // Извлекаем заголовок и текст страницы
            String title = doc.title();
            String content = doc.body().text();

            // Логируем результат в консоль
            System.out.println("URL: " + url);
            System.out.println("Заголовок: " + title);
            System.out.println("Контент: " + content.substring(0, Math.min(content.length(), 200)) + "...");  // Обрезаем для удобства
            System.out.println("======================================");

        } catch (IOException e) {
            System.err.println("Ошибка при парсинге страницы " + url + ": " + e.getMessage());
        }
    }
}