package edu.infnet.repository.preOrder;

import edu.infnet.model.item.Item;
import edu.infnet.model.preOrder.PreOrder;
import edu.infnet.repository.Repository;
import edu.infnet.repository.item.ItemRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PreOrderRepository {
    public static Repository preOrederRepository = new Repository("preOrderDB");

    public static void saveNewPreOrder(PreOrder newPreOrder) throws Exception {
        String content = newPreOrder.getAllInfo();

        for (String[] preOrder : preOrederRepository.getAll()) {
            if (preOrder[PreOrderTable.PRE_ORDER_ID].equals(newPreOrder.getPreOrderID())) {
                throw new Exception("O pré-pedido '" + newPreOrder.getPreOrderID() + "' já foi cadastrado!");
            }
        }
        preOrederRepository.save(content);
    }

    public static List<Item> getAllItemsOFPreOrderByID(String preOrderID) throws IOException {
        List<String[]> preOrderList = preOrederRepository.getAll();
        List<Item> items = new ArrayList<>();


        for (String[] preOrderData : preOrderList) {
            if (preOrderData[PreOrderTable.PRE_ORDER_ID].equals(preOrderID)) {
                for (int i = 1; i < preOrderData.length; i++) {
                    String itemDataID = preOrderData[i];
                    items.add(ItemRepository.getItemByID(itemDataID));
                }

            }
        }
        return items;
    }
    public static void updateItemsOFPreOrderByID(String preOrderID, List<Item> newItems) throws Exception {
        PreOrder newPreOrder = new PreOrder(preOrderID, newItems);
        preOrederRepository.deleteById(preOrderID);
        saveNewPreOrder(newPreOrder);
    }
}
