package searchengine.services;
import searchengine.model.Page;
import searchengine.model.Site;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

public class PageIndexer extends RecursiveTask<Set<Page>> {

    private final Site site;
    private final String url;

    public PageIndexer(Site site, String url) {
        this.site = site;
        this.url = url;
    }

    @Override
    protected Set<Page> compute() {
        Set<Page> pages = new HashSet<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            // Здесь логика обработки страницы и добавления ее в набор pages
            // ...

            // Создание подзадач для обработки новых ссылок
            Set<PageIndexer> subtasks = new HashSet<>();
            for (var link : links) {
                String nextUrl = link.absUrl("href");
                PageIndexer subtask = new PageIndexer(site, nextUrl);
                subtask.fork();
                subtasks.add(subtask);
            }

            for (var subtask : subtasks) {
                pages.addAll(subtask.join());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return pages;
    }
}