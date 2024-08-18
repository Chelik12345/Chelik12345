package searchengine.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlCleaner {

    public static String cleanHtml(String html) {
        Document doc = Jsoup.parse(html);
        return doc.text();
    }
}
