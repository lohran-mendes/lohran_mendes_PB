package edu.infnet.repository.user;

import edu.infnet.model.user.AuthenticatedUser;
import edu.infnet.model.user.UserType;
import edu.infnet.repository.Repository;

import java.io.IOException;

public class UserRepository {
    static Repository repository = new Repository(UserType.UNAUTHENTICATED.toString());

    public static void singUp(AuthenticatedUser newAuthenticatedUser) throws Exception {
        String content = newAuthenticatedUser.getAllInfo();

        for (String[] user : repository.getAll()) {
            if (user[UserTable.EMAIL].equals(newAuthenticatedUser.getEmail())) {
                throw new Exception("O email '" + newAuthenticatedUser.getEmail() + "' já foi cadastrado!");
            }
        }
        repository.save(content);
    }

    public static boolean singIn(String email, String password) throws IOException {
        for (String[] user : repository.getAll()) {
            if (user[UserTable.EMAIL].equals(email) && user[UserTable.PASSWORD].equals(password)) {
                System.out.println("Login bem-sucedido! Bem-vindo, " + user[UserTable.NAME]);
                return true;
            }
        }
        return false;
    }
}
