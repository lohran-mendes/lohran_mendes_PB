package edu.infnet.model.user;

import edu.infnet.model.address.Address;
import edu.infnet.model.item.Item;
import edu.infnet.repository.item.ItemRepository;

import java.io.IOException;
import java.util.List;

public class AdminUser extends AuthenticatedUser {
    private final UserType userType = UserType.ADMIN;

    public AdminUser(String name, String email, String password) {
        super(name, email, password);
    }

    public AdminUser(String USER_ID, String name, String email, String password) {
        super(USER_ID, name, email, password);
    }

    // todos tem que ter um item de parametro que deve ser adicionado mais tarde
    public void addItem(Item item) throws IOException {
        ItemRepository.addItem(item);
        System.out.println("Item adicionado com sucesso!");
    }

    public void removeItem() {
        // Lógica para remover um item
        System.out.println("Item removido com sucesso!");
    }

    public void updateItem() {
        // Lógica para atualizar um item
        System.out.println("Item atualizado com sucesso!");
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
