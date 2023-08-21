package mkcrud.mkboard;

import mkcrud.mkboard.domain.Item;
import mkcrud.mkboard.domain.ItemRepository;
import mkcrud.mkboard.domain.JdbcItemRepository;
import mkcrud.mkboard.domain.MemoryItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class BoardTest {

    @Autowired DataSource dataSource;
    ItemRepository memoryItemRepository = new MemoryItemRepository();

    @Test
    public void 값넣기(){
        memoryItemRepository.saveItem(new Item("제목1", "내용1"));
        memoryItemRepository.saveItem(new Item("제목2", "내용2"));

        List<Item> items = memoryItemRepository.showList();

        items.iterator().forEachRemaining(
                item -> System.out.println(item.getTitle())
        );
    }

    @Test
    public void 리버스테스트(){
        memoryItemRepository.saveItem(new Item("제목1", "내용1"));
        memoryItemRepository.saveItem(new Item("제목2", "내용2"));

        List<Item> items = memoryItemRepository.showList();
        System.out.println(items.get(0).getTitle());

        Collections.reverse(items);
        System.out.println(items.get(0).getTitle());
    }

    @Test
    public void JDBC연결테스트(){
        JdbcItemRepository jdbcItemRepository = new JdbcItemRepository(dataSource);
        jdbcItemRepository.saveItem(new Item("abc", "defgh"));
    }

    @Test
    public void JDBC카운트테스트(){
        JdbcItemRepository jdbcItemRepository = new JdbcItemRepository(dataSource);
        jdbcItemRepository.itemCnt();
    }
}
