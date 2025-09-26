package edu.infnet.repository.user;

import edu.infnet.model.address.Address;
import edu.infnet.model.order.Order;
import edu.infnet.model.shoppingCart.ShoppingCart;
import edu.infnet.model.user.AdminUser;
import edu.infnet.model.user.AuthenticatedUser;
import edu.infnet.model.user.UserType;
import edu.infnet.repository.Repository;
import edu.infnet.repository.address.AddressRepository;
import edu.infnet.repository.shoppingCart.ShoppingCartRepository;

public class UserRepository {
    static Repository userRepository = new Repository("usersDB");

    public static void saveNewUser(AuthenticatedUser newAuthenticatedUser) throws Exception {
        String content = newAuthenticatedUser.getAllInfo();

        for (String[] user : userRepository.getAll()) {
            if (user[UserTable.EMAIL].equals(newAuthenticatedUser.getEmail())) {
                throw new Exception("O email '" + newAuthenticatedUser.getEmail() + "' já foi cadastrado!");
            }
        }
        userRepository.save(content);
        ShoppingCartRepository.saveNewShoppingCart(newAuthenticatedUser.shoppingCart);
    }

    public static AuthenticatedUser getUserByEmailAndPassword(String email, String password) throws Exception {
        for (String[] user : userRepository.getAll()) {
            if (user[UserTable.EMAIL].equals(email) && user[UserTable.PASSWORD].equals(password)) {
                if (user[UserTable.USERTYPE].equals(UserType.AUTHENTICATED.toString())) {
                    Address address = AddressRepository.getAddressById(user[UserTable.ADDRESS]);
                    Order order = new Order(user[UserTable.ORDER]);
                    ShoppingCart shoppingCart = ShoppingCartRepository.getShoppingCartByID(user[UserTable.SHOPPINGCART_id]);



                    return new AuthenticatedUser(user[UserTable.ID], user[UserTable.NAME], user[UserTable.EMAIL], user[UserTable.PASSWORD], address, order, shoppingCart);
                } else if (user[UserTable.USERTYPE].equals(UserType.ADMIN.toString())) {
                    return new AdminUser(user[UserTable.ID], user[UserTable.NAME], user[UserTable.EMAIL], user[UserTable.PASSWORD]);
                }
            }
        }
        throw new Exception("O email ou a senha estão incorretos.");
    }
}
