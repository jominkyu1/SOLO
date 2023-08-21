package mkcrud.mkboard.domain;

import java.util.List;

public interface ItemRepository {
    List<Item> showList();

    Long saveItem(Item item);

    Item findItemById(Long id);

    void itemUpdate(Long itemId, Item item);
}
