package mkcrud.mkboard.domain;


import java.util.*;

public class MemoryItemRepository implements ItemRepository {
    private final HashMap<Long, Item> itemMap = new HashMap<>();
    private Long sequence = 0L;

    @Override
    public List<Item> showList(){
        List<Item> items = new ArrayList<>(itemMap.values());
        Collections.reverse(items);
        return items;
    }

    @Override
    public Long saveItem(Item item){
        item.setId(++sequence);
        itemMap.put(item.getId(), item);

        return item.getId();
    }

    @Override
    public Item findItemById(Long id){
        return itemMap.get(id);
    }

    @Override
    public void itemUpdate(Long itemId, Item item){
        Item foundItem = findItemById(itemId);

        foundItem.setTitle(item.getTitle());
        foundItem.setContent(item.getContent());
    }
}
