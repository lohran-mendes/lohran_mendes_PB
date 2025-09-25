package edu.infnet.model.item;

public class Item {
    private String ITEM_ID;
    private String name;
    private double price;
    private int quantity;
    private String summary;

    public Item() {
        this.ITEM_ID = java.util.UUID.randomUUID().toString();
    }
    public Item(String name, double price, int quantity) {
        this.ITEM_ID = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String description(){
        // implementa lógica para retornar a descrição do item
        return summary;
    }

    public String getAllInfo() {
        return ITEM_ID + ";" + name + ";" + price + ";" + quantity + ";" + summary;
    }
}
