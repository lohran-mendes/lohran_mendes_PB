package edu.infnet.repository.user;

import edu.infnet.model.user.AuthenticatedUser;
import edu.infnet.model.user.UserType;
import edu.infnet.repository.Repository;

import java.io.IOException;

public class UnauthenticatedUserRepository {
    static Repository repository = new Repository("usersDB");

    public static void singUp(AuthenticatedUser newAuthenticatedUser) throws Exception {
        String content = newAuthenticatedUser.getAllInfo();

        for (String[] user : repository.getAll()) {
            if (user[UserTable.EMAIL].equals(newAuthenticatedUser.getEmail())) {
                throw new Exception("O email '" + newAuthenticatedUser.getEmail() + "' já foi cadastrado!");
            }
        }
        repository.save(content);
    }

    public static AuthenticatedUser singIn(String email, String password) throws Exception {
        for (String[] user : repository.getAll()) {
            if (user[UserTable.EMAIL].equals(email) && user[UserTable.PASSWORD].equals(password)) {
                return new AuthenticatedUser(user[UserTable.ID], user[UserTable.NAME], user[UserTable.EMAIL], user[UserTable.PASSWORD]);
            }
        }
        throw new Exception("O email ou a senha estão incorretos.");
    }
}
