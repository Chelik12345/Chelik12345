package searchengine.model;
@RestController
@RequestMapping("/api")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public ResponseEntity<List<SearchResult>> search(@RequestParam String query) {
        List<SearchResult> results = searchService.search(query);
        return ResponseEntity.ok(results);
    }
}
