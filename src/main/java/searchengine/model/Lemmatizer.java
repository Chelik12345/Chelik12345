package searchengine.model;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

@Component
public class Lemmatizer {

    public List<String> lemmatize(String text) {
        // Простая логика лемматизации; замените на реальную реализацию
        String[] words = text.toLowerCase().split("\\W+");
        return Arrays.stream(words)
                .filter(word -> !isStopWord(word))
                .distinct()
                .collect(Collectors.toList());
    }

    private boolean isStopWord(String word) {
        // Список стоп-слов
        List<String> stopWords = Arrays.asList("and", "or", "but", "the", "is", "in", "on", "at");
        return stopWords.contains(word);
    }
}
