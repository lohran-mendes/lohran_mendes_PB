package edu.infnet.model.user;

import edu.infnet.repository.item.ItemRepository;
import edu.infnet.repository.user.UserRepository;

import java.io.IOException;
import java.util.List;

public class UnauthenticatedUser implements IUser{
    private final UserType userType = UserType.UNAUTHENTICATED;

    public void singUp(AuthenticatedUser newAuthenticatedUser) throws Exception {
        UserRepository.saveNewUser(newAuthenticatedUser);
    }

    public AuthenticatedUser singIn(String email, String password) throws Exception {
        return UserRepository.getUserByEmailAndPassword(email, password);
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
