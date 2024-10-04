package searchengine.model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PageInitializer {

    @Autowired
    private SearchService searchService;

    // Инициализация страниц при старте приложения
    @PostConstruct
    public void init() {
        searchService.addPage(new Page("https://example.com", "Example Title", "This is an example content."));
        searchService.addPage(new Page("https://another.com", "Another Title", "Another example of content to search."));
        searchService.addPage(new Page("https://mysite.com", "My Site Title", "Content of my site for testing search."));
    }
}
