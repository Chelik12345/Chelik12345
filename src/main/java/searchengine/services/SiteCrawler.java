package searchengine.services;
import searchengine.model.Page;
import searchengine.model.Site;
import com.example.repository.PageRepository;
import com.example.repository.SiteRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.concurrent.RecursiveTask;

public class SiteCrawler extends RecursiveTask<Void> {

    private final Site site;
    private final PageRepository pageRepository;
    private final SiteRepository siteRepository;

    public SiteCrawler(Site site, PageRepository pageRepository, SiteRepository siteRepository) {
        this.site = site;
        this.pageRepository = pageRepository;
        this.siteRepository = siteRepository;
    }

    @Override
    protected Void compute() {
        try {
            Document doc = Jsoup.connect(site.getUrl()).get();
            Page page = new Page();
            page.setSite(site);
            page.setPath("/");
            page.setStatusCode(doc.connection().response().statusCode());
            page.setContent(doc.html());
            pageRepository.save(page);

            // Обработка ссылок на странице и создание новых задач для ForkJoinPool

        } catch (IOException e) {
            site.setStatus(SiteStatus.FAILED.name());
            site.setLastError(e.getMessage());
            siteRepository.save(site);
        }
        return null;
    }
}
