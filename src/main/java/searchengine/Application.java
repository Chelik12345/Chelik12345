package searchengine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "searchengine.repository")
@EntityScan(basePackages = "searchengine")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

