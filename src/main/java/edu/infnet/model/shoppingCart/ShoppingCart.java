package edu.infnet.model.shoppingCart;

import edu.infnet.model.item.Item;

public class ShoppingCart {
    private Item[] preOrder;
    private double totalPrice;
    private int quantityOfItems;

    public void removeItemFromCart(Item item) {
        // implementa lógica para remover item do carrinho
    }

    public void addItemToCart(Item item) {
        // implementa lógica para adicionar item ao carrinho
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
