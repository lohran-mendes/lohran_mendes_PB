package edu.infnet.model.user;

import edu.infnet.model.item.Item;
import edu.infnet.repository.user.AdminUserRepository;

import java.io.IOException;

public class AdminUser extends AuthenticatedUser {
    private final UserType userType = UserType.ADMIN;

    public AdminUser(String name, String email, String password) {
        super(name, email, password);
    }

    // todos tem que ter um item de parametro que deve ser adicionado mais tarde
    public void addItem(Item item) throws IOException {
        AdminUserRepository.addItem(item);
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
}
