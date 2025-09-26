package edu.infnet.model.preOrder;

import edu.infnet.model.item.Item;
import edu.infnet.repository.preOrder.PreOrderRepository;

import java.util.ArrayList;
import java.util.List;

public class PreOrder {
    private String preOrderID;
    private List<Item> items;

    public PreOrder(){
        this.preOrderID = java.util.UUID.randomUUID().toString();
        this.items = new ArrayList<Item>();
    }

    public PreOrder(String orderID, List<Item> items) {
        this.preOrderID = orderID;
        this.items = items;
    }

    public String getPreOrderID() {
        return preOrderID;
    }

    public void addItemToPreOrder(Item item) throws Exception {
        this.items.add(item);
        PreOrderRepository.updateItemsOFPreOrderByID(this.preOrderID, this.items);
    }

    public String getAllInfo() {
        String content = preOrderID + ";";

        for (Item item : items) {
            content += item.getITEM_ID() + ";";
        }
        return content;
    }

    public String getAllIdsOfItems() {
        StringBuilder ids = new StringBuilder();
        for (Item item : items) {
            ids.append(item.getITEM_ID()).append(";");
        }
        if (ids.length() > 0) {
            ids.setLength(ids.length() - 1);
        }
        return ids.toString();
    }
}
