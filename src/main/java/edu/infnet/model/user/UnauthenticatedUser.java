package edu.infnet.model.user;

import edu.infnet.repository.user.UserRepository;

import java.io.IOException;

public class UnauthenticatedUser implements IUser{
    private final UserType userType = UserType.UNAUTHENTICATED;

    public void singUp(AuthenticatedUser newAuthenticatedUser) throws Exception {
        UserRepository.singUp(newAuthenticatedUser);
    }

    public boolean singIn(String email, String password) throws IOException {
        return UserRepository.singIn(email, password);
    }

    @Override
    public UserType getUserType() {
        return userType;
    }

}
