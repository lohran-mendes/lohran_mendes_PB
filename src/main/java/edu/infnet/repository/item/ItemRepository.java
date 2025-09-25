package edu.infnet.repository.item;

import edu.infnet.model.item.Item;
import edu.infnet.repository.Repository;

import java.io.IOException;
import java.util.List;

public class ItemRepository {
    static Repository itemRepository = new Repository("itensDB");

    public static List<String[]> consultCatalog() throws IOException {
        return itemRepository.getAll();
    }

    public static void addItem(Item item) throws IOException {
        itemRepository.save(item.getAllInfo());
    }
}
