package searchengine.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Site extends searchengine.model.Site {
    private String url;
    private String name;
}
