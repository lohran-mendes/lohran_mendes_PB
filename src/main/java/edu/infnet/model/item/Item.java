package edu.infnet.model.item;

public class Item {
    private String name;
    private double price;
    private int quantity;
    private String summary;

    public String description(){
        // implementa lógica para retornar a descrição do item
        return summary;
    }
}
