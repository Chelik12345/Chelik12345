package searchengine.model;
@RestController
@RequestMapping("/api")
public class IndexingController {

    @Autowired
    private IndexingService indexingService;

    @PostMapping("/startIndexing")
    public ResponseEntity<String> startIndexing() {
        indexingService.startIndexing();
        return ResponseEntity.ok("Indexing started");
    }

    @PostMapping("/stopIndexing")
    public ResponseEntity<String> stopIndexing() {
        indexingService.stopIndexing();
        return ResponseEntity.ok("Indexing stopped");
    }
}

@Service
public class IndexingService {

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private PageRepository pageRepository;

    public void startIndexing() {
        // Удаляем все существующие данные
        siteRepository.deleteAll();
        pageRepository.deleteAll();

        // Начинаем индексацию сайтов из конфигурационного файла
        List<String> sites = getSitesFromConfig();
        for (String siteUrl : sites) {
            Site site = new Site();
            site.setUrl(siteUrl);
            site.setStatus(Status.INDEXING);
            site.setStatusTime(new Date());
            siteRepository.save(site);

            // Запускаем процесс обхода страниц
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            forkJoinPool.invoke(new PageCrawler(site, siteUrl));
        }
    }

    public void stopIndexing() {
        // Реализация остановки индексации
    }

    private List<String> getSitesFromConfig() {
        // Получаем список сайтов из конфигурации
        return Arrays.asList("https://example.com", "https://anotherexample.com");
    }

    private class PageCrawler extends RecursiveTask<Void> {
        private final Site site;
        private final String url;

        public PageCrawler(Site site, String url) {
            this.site = site;
            this.url = url;
        }

        @Override
        protected Void compute() {
            try {
                Document doc = Jsoup.connect(url)
                        .userAgent("HeliontSearchBot")
                        .referrer("http://www.google.com")
                        .get();

                String content = doc.outerHtml();
                int statusCode = 200;

                Page page = new Page();
                page.setSite(site);
                page.setPath(url);
                page.setContent(content);
                page.setStatusCode(statusCode);
                pageRepository.save(page);

                List<PageCrawler> subtasks = new ArrayList<>();
                for (Element link : doc.select("a[href]")) {
                    String linkHref = link.attr("abs:href");
                    if (!pageRepository.existsByPath(linkHref)) {
                        subtasks.add(new PageCrawler(site, linkHref));
                    }
                }
                invokeAll(subtasks);
                site.setStatus(Status.INDEXED);
            } catch (IOException e) {
                site.setStatus(Status.FAILED);
                site.setLastError(e.getMessage());
            } finally {
                site.setStatusTime(new Date());
                siteRepository.save(site);
            }
            return null;
        }
    }
}