package edu.infnet.view;

import edu.infnet.model.catalog.Catalog;
import edu.infnet.model.user.AuthenticatedUser;
import edu.infnet.model.user.UnauthenticatedUser;

import java.io.IOException;
import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);
    private static UnauthenticatedUser user = new UnauthenticatedUser();
    private static Catalog catalog = new Catalog();

    public static void init(){
        System.out.println("\nBem-vindo ao sistema de gerenciamento de usuários!");
        showMenu();
    }

    private static void showMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Visualizar Catálogo");
            System.out.println("2. Criar Nova Conta");
            System.out.println("3. Fazer Login");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        visualizarCatalogo();
                        break;
                    case 2:
                        criarNovaConta();
                        break;
                    case 3:
                        fazerLogin();
                        break;
                    case 4:
                        System.out.println("Obrigado por usar nosso sistema!");
                        running = false;
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção entre 1 e 4.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                scanner.nextLine(); // Limpar o buffer em caso de erro
            }
        }
    }

    private static void visualizarCatalogo() {
        System.out.println("\n=== CATÁLOGO DE PRODUTOS ===");
        catalog.consultCatalog();
        System.out.println("Catálogo exibido com sucesso!");
        System.out.println("Pressione Enter para voltar ao menu...");
        scanner.nextLine();
    }

    private static void criarNovaConta() {
        System.out.println("\n=== CRIAR NOVA CONTA ===");

        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();

        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        try {
            AuthenticatedUser novoUsuario = new AuthenticatedUser(nome, email, senha);
            user.singUp(novoUsuario);
            System.out.println("Conta criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar conta: " + e.getMessage());
        }

        System.out.println("Pressione Enter para voltar ao menu...");
        scanner.nextLine();
    }

    private static void fazerLogin() {
        System.out.println("\n=== FAZER LOGIN ===");

        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        try {
            boolean loginSucesso = user.singIn(email, senha);
            if (!loginSucesso) {
                System.out.println("Email ou senha incorretos!");
            }
        } catch (IOException e) {
            System.out.println("Erro ao fazer login: " + e.getMessage());
        }

        System.out.println("Pressione Enter para voltar ao menu...");
        scanner.nextLine();
    }
}
