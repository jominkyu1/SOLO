package mkcrud.mkboard;

import mkcrud.mkboard.domain.ItemRepository;
import mkcrud.mkboard.domain.JdbcItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Configuration
public class BoardConfig {

    @Autowired public DataSource dataSource;

    @Bean
    public ItemRepository itemRepository(){
        return new JdbcItemRepository(dataSource);
    }
}
