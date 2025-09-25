package edu.infnet.model.user;

import edu.infnet.model.address.Address;
import edu.infnet.model.order.Order;
import edu.infnet.model.shoppingCart.ShoppingCart;

public class AuthenticatedUser implements IUser {
    private final UserType userType = UserType.AUTHENTICATED;

    private String USER_ID;
    private String name;
    private String email;
    private String password;
    private Address address;
    private Order order;
    private ShoppingCart shoppingCart;

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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAllInfo() {
        return USER_ID + ";" + name + ";" + email + ";" + password + ";" + address + ";" + order + ";" + shoppingCart;
    }

    public void consultOrders() {
        // Lógica para consultar pedidos
        System.out.println("Consultando pedidos do usuário " + name);
    }

    public void logout() {
        // Lógica para deslogar o usuário
        System.out.println("Usuário " + name + " deslogado com sucesso!");
    }

    @Override
    public UserType getUserType() {
        return userType;
    }
}
