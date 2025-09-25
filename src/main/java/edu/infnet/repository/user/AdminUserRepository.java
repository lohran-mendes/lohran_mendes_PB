package edu.infnet.repository.user;

import edu.infnet.model.item.Item;
import edu.infnet.model.user.UserType;
import edu.infnet.repository.Repository;

import java.io.IOException;

public class AdminUserRepository {
    static Repository itemRepository = new Repository("itensDB");

    public static void addItem(Item item) throws IOException {
        itemRepository.save(item.getAllInfo());
    }
}
