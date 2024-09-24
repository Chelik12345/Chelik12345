package searchengine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import searchengine.services.SearchServic;
import searchengine.model.Page;

import java.util.List;

@RestController
public class SerchController {

    @Autowired
    private SearchServic searchService;

    @GetMapping("/api/search")
    public ResponseEntity<List<Page>> search(@RequestParam String query) {
        List<Page> results = searchService.search(query);
        return ResponseEntity.ok(results);
    }
}
