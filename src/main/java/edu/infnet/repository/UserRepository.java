package edu.infnet.repository;

import edu.infnet.model.user.AuthenticatedUser;
import edu.infnet.model.user.UserType;

import java.io.IOException;

public class UserRepository {
    static Repository repository = new Repository(UserType.UNAUTHENTICATED.toString());
    private static final int ID = 0;
    private static final int NAME= 1;
    private static final int EMAIL= 2;
    private static final int PASSWORD= 3;

    public static void singUp(AuthenticatedUser newAuthenticatedUser) {
        String content = newAuthenticatedUser.getAllInfo();
        try {
            for (String[] user : repository.read()) {
                if (user[EMAIL].equals(newAuthenticatedUser.getEmail())) {
                    throw new Exception("O Email "+ newAuthenticatedUser.getEmail()+ " já foi cadastrado!");
                }
            }
            repository.save(content);
        } catch (Exception e) {
            System.out.println("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    public static boolean singIn(String email, String password) throws IOException {
        for (String[] user : repository.read()) {
            if (user[EMAIL].equals(email) && user[PASSWORD].equals(password)) {
                System.out.println("Login bem-sucedido! Bem-vindo, " + user[NAME]);
                return true;
            }
        }
        return false;
    }
}
