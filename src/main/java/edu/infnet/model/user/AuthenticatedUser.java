package edu.infnet.model.user;

import edu.infnet.model.address.Address;
import edu.infnet.model.order.Order;
import edu.infnet.model.shoppingCart.ShoppingCart;
import edu.infnet.repository.item.ItemRepository;

import java.io.IOException;
import java.util.List;

public class AuthenticatedUser implements IUser {
    private final UserType userType = UserType.AUTHENTICATED;

    private String USER_ID;
    private String name;
    private String email;
    private String password;
    private Address address;
    private Order order;
    public ShoppingCart shoppingCart;

    public AuthenticatedUser(String USER_ID, String name, String email, String password) {
        this.USER_ID = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public AuthenticatedUser(String name, String email, String password) {
        this.USER_ID = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public AuthenticatedUser(String name, String email, String password, Address address) {
        this.USER_ID = java.util.UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAllInfo() {
        return USER_ID + ";" + userType + ";" + name + ";" + email + ";" + password + ";" + getAddressIDToString() + ";" + order + ";" + shoppingCart;
    }

    public void consultOrders() {
        // Lógica para consultar pedidos
        System.out.println("Consultando pedidos do usuário " + name);
    }

    public void logout() {
        // Lógica para deslogar o usuário
        System.out.println("Usuário " + name + " deslogado com sucesso!");
    }

    public String getAddressIDToString() {
        if (address == null) {
            return "null";
        }
        return address.getADDRESS_ID();
    }

    @Override
    public UserType getUserType() {
        return userType;
    }

    @Override
    public List<String[]> consultCatalog() throws IOException {
        return ItemRepository.consultCatalog();
    }
}
