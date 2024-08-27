package searchengine.model;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
//        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/my_base");
        dataSource.setJdbcUrl("jdbc:h2:mem:testdb");
        dataSource.setUsername("sa");
//        dataSource.setPassword("freefire007");
        dataSource.setPassword("");
        return dataSource;
    }
}
