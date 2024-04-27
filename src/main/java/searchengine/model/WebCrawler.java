package searchengine.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class WebCrawler {
    private Set<String> visitedUrls;

    public WebCrawler() {
        visitedUrls = new HashSet<>();
    }

    public void crawl(String startUrl) {
        crawlPage(startUrl);
    }

    private void crawlPage(String url) {
        if (visitedUrls.contains(url)) {
            return;
        }

        visitedUrls.add(url);
        System.out.println("Crawling: " + url);

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                // Здесь вы можете обрабатывать контент страницы
                // Например, индексировать текст или находить ссылки

                // В этом примере просто выводим содержимое страницы
                System.out.println(line);
            }

            reader.close();

            // Здесь вы можете извлекать ссылки и рекурсивно обходить их
            // Например, если вы хотите найти ссылки в содержимом страницы:
            // extractLinksFromPage(url);
            // И рекурсивно обойти каждую найденную ссылку:
            // for (String link : links) {
            //     crawlPage(link);
            // }
        } catch (IOException e) {
            System.err.println("Failed to crawl " + url + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String startUrl = "https://www.example.com"; // Начальная страница для сканирования

        WebCrawler crawler = new WebCrawler();
        crawler.crawl(startUrl);
    }
}

