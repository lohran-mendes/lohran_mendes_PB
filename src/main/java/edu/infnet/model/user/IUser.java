package edu.infnet.model.user;

import edu.infnet.repository.item.ItemRepository;

import java.io.IOException;
import java.util.List;

public interface IUser
{
    UserType getUserType();
    List<String[]> consultCatalog() throws  IOException;

}