package edu.infnet.model.user;

public class UnauthenticatedUser implements IUser{
    private final UserType userType = UserType.UNAUTHENTICATED;

    public void singUp(String name, String password, String email) {
        // Lógica para registrar um novo usuário
        System.out.println("Usuário " + name + " registrado com sucesso!");
    }
    public void singIn(String email, String password) {
        // Lógica para autenticar um usuário
        System.out.println("Usuário " + email + " autenticado com sucesso!");
    }

    @Override
    public UserType getUserType() {
        return userType;
    }

}
