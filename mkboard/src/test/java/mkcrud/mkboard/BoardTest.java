package mkcrud.mkboard;

import mkcrud.mkboard.domain.Item;
import mkcrud.mkboard.domain.ItemRepository;
import mkcrud.mkboard.domain.MemoryItemRepository;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class BoardTest {
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
}
