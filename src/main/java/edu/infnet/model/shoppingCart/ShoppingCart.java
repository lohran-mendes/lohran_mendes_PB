package edu.infnet.model.shoppingCart;

import edu.infnet.model.item.Item;
import edu.infnet.model.preOrder.PreOrder;
import edu.infnet.repository.item.ItemRepository;
import edu.infnet.repository.preOrder.PreOrderRepository;
import edu.infnet.repository.shoppingCart.ShoppingCartRepository;

import java.io.IOException;
import java.util.List;

public class ShoppingCart {
    private final String CART_ID;
    private double totalPrice;
    private int quantityOfItems;
    private PreOrder preOrder;

    public ShoppingCart() throws Exception {
        this.CART_ID = java.util.UUID.randomUUID().toString();
        this.preOrder = new PreOrder();
    }

    public ShoppingCart(String CART_ID, double totalPrice, int quantityOfItems, PreOrder preOrder) {
        this.CART_ID = CART_ID;
        this.totalPrice = totalPrice;
        this.quantityOfItems = quantityOfItems;
        this.preOrder = preOrder;
    }

    public String getCART_ID() {
        return CART_ID;
    }
    public String getIdPreOrder() {
        return preOrder.getPreOrderID();
    }

    public int getQuantityOfItems() throws IOException {
        List<Item> allItemsOFPreOrderByID = PreOrderRepository.getAllItemsOFPreOrderByID(this.preOrder.getPreOrderID());
        this.quantityOfItems = allItemsOFPreOrderByID.size();
        return quantityOfItems;
    }

    public double getTotalPrice() throws IOException {
        List<Item> allItemsOFPreOrderByID = PreOrderRepository.getAllItemsOFPreOrderByID(this.preOrder.getPreOrderID());
        Double totalPrice = 0.0;
        for(Item item : allItemsOFPreOrderByID) {
            totalPrice += item.getPrice();
        }
        this.totalPrice = totalPrice;
        return totalPrice;
    }

    public String getAllInfo() throws Exception {
        return CART_ID + ";" + totalPrice + ";" + quantityOfItems + ";" + preOrder.getPreOrderID();
    }

    public void removeItemFromCart(Item item) {
        // implementa lógica para remover item do carrinho
    }

    public void addItemToCart(String idItem) throws Exception {
        Item newItem = ItemRepository.getItemByID(idItem);
        this.quantityOfItems++;
        this.totalPrice += newItem.getPrice();
        this.preOrder.addItemToPreOrder(newItem);
        ShoppingCartRepository.saveNewItemIntoCartByID(this.CART_ID, idItem);
    }

    public void addToPreOrder(Item item) {
        // implementa lógica para adicionar item ao pré-pedido
    }


    public void removeFromPreOrder(Item item) {
        // implementa lógica para remover item do pré-pedido
    }
    public void placeOrder() {
        // implementa lógica para finalizar o pedido
    }
}
