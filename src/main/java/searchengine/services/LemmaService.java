package searchengine.services;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.ru.RussianAnalyzer;
import org.springframework.stereotype.Service;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class LemmaService {

    public Map<String, Integer> extractLemmas(String text) {
        Map<String, Integer> lemmas = new HashMap<>();
        try (Analyzer analyzer = new RussianAnalyzer()) {
            TokenStream tokenStream = analyzer.tokenStream(null, new StringReader(text));
            CharTermAttribute charTermAttr = tokenStream.addAttribute(CharTermAttribute.class);
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                String lemma = charTermAttr.toString();
                lemmas.put(lemma, lemmas.getOrDefault(lemma, 0) + 1);
            }
            tokenStream.end();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lemmas;
    }
}
