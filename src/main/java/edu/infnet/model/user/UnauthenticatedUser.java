package edu.infnet.model.user;

import edu.infnet.repository.user.UnauthenticatedUserRepository;

import java.io.IOException;

public class UnauthenticatedUser implements IUser{
    private final UserType userType = UserType.UNAUTHENTICATED;

    public void singUp(AuthenticatedUser newAuthenticatedUser) throws Exception {
        UnauthenticatedUserRepository.singUp(newAuthenticatedUser);
    }

    public AuthenticatedUser singIn(String email, String password) throws Exception {
        return UnauthenticatedUserRepository.singIn(email, password);
    }

    @Override
    public UserType getUserType() {
        return userType;
    }

}
