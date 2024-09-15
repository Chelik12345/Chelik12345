package searchengine.model;

import org.springframework.stereotype.Component;

@Component
public class WebCrawler {

    public void startCrawling() {
        // Реализация логики для сбора данных с сайта и их индексирования
        System.out.println("Индексация началась...");
    }
}

