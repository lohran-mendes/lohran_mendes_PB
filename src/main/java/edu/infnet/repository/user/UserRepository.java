package edu.infnet.repository.user;

import edu.infnet.model.user.AuthenticatedUser;
import edu.infnet.repository.Repository;

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
    }

    public static AuthenticatedUser getUserByEmailAndPassword(String email, String password) throws Exception {
        for (String[] user : userRepository.getAll()) {
            if (user[UserTable.EMAIL].equals(email) && user[UserTable.PASSWORD].equals(password)) {
                return new AuthenticatedUser(user[UserTable.ID], user[UserTable.NAME], user[UserTable.EMAIL], user[UserTable.PASSWORD]);
            }
        }
        throw new Exception("O email ou a senha estão incorretos.");
    }
}
