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

    public static Item getItemByID(String itemID) throws IOException {
        List<String[]> allItems = itemRepository.getAll();
        for (String[] itemData : allItems) {
            if (itemData[ItemTable.ITEM_ID].equals(itemID)) {
                return new Item(itemData[ItemTable.ITEM_ID], itemData[ItemTable.NAME], Double.parseDouble(itemData[ItemTable.PRICE]), Integer.parseInt(itemData[ItemTable.QUANTITY])); // Ajuste conforme os atributos do Item
            }
        }
        return null;
    }
}
