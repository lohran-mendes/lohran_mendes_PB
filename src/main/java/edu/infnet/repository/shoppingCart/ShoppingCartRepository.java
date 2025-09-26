package edu.infnet.repository.shoppingCart;

import edu.infnet.model.address.Address;
import edu.infnet.model.item.Item;
import edu.infnet.model.preOrder.PreOrder;
import edu.infnet.model.shoppingCart.ShoppingCart;
import edu.infnet.repository.Repository;
import edu.infnet.repository.address.AddressTable;
import edu.infnet.repository.item.ItemRepository;
import edu.infnet.repository.item.ItemTable;
import edu.infnet.repository.preOrder.PreOrderRepository;
import edu.infnet.repository.user.UserTable;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartRepository {
    static Repository shoppingCartRepository = new Repository("shoppingCartDB");

    public static void saveNewShoppingCart(ShoppingCart newShoppingCart) throws Exception {
        String content = newShoppingCart.getAllInfo();

        for (String[] shoppingCart : shoppingCartRepository.getAll()) {
            if (shoppingCart[ShoppingCartTable.CART_ID].equals(newShoppingCart.getCART_ID())) {
                throw new Exception("O ID do carrinho '" + newShoppingCart.getCART_ID() + "' já foi cadastrado!");
            }
        }
        shoppingCartRepository.save(content);
    }

    public static void saveNewItemIntoCartByID(String CART_ID, String itemID) throws Exception {

        ShoppingCart shoppingCart = getShoppingCartByID(CART_ID);
        Item newItem = ItemRepository.getItemByID(itemID);
        List<Item> newListItens = PreOrderRepository.getAllItemsOFPreOrderByID(shoppingCart.getIdPreOrder());
        newListItens.add(newItem);


        PreOrder preOrder = new PreOrder(shoppingCart.getIdPreOrder(), newListItens);
        ShoppingCart newShoppingCart = new ShoppingCart(shoppingCart.getCART_ID(), shoppingCart.getTotalPrice(), shoppingCart.getQuantityOfItems(), preOrder);
        String content = newShoppingCart.getAllInfo();

        shoppingCartRepository.deleteById(CART_ID);
        shoppingCartRepository.save(content);
    }



    public static ShoppingCart getShoppingCartByID(String id) throws Exception {
        for (String[] shopping : shoppingCartRepository.getAll()) {
            if (shopping[ShoppingCartTable.CART_ID].equals(id)) {
                return new ShoppingCart(shopping[ShoppingCartTable.CART_ID], Double.parseDouble(shopping[ShoppingCartTable.TOTAL_PRICE]), Integer.parseInt(shopping[ShoppingCartTable.QUANTITY_OF_ITEMS]), new PreOrder(shopping[ShoppingCartTable.ID_PRE_ORDER], PreOrderRepository.getAllItemsOFPreOrderByID(shopping[ShoppingCartTable.ID_PRE_ORDER])));
            }
        }
        return null;
    }
}
