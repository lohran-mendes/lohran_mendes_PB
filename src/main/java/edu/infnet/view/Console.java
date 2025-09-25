package edu.infnet.view;

import edu.infnet.model.catalog.Catalog;
import edu.infnet.model.item.Item;
import edu.infnet.model.user.AdminUser;
import edu.infnet.model.user.AuthenticatedUser;
import edu.infnet.model.user.UnauthenticatedUser;
import edu.infnet.repository.catalog.CatalogTable;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);
    private static UnauthenticatedUser user = new UnauthenticatedUser();
    private static AdminUser admin = new AdminUser("admin", "admin@gmail.com", "admin123");
    private static Catalog catalog = new Catalog();
    private static boolean running;

    public static void init() {
        System.out.println("\nBem-vindo ao sistema de gerenciamento de usuários!");
        showMenuDefault();
    }

    private static void showMenuDefault() {
        running = true;
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

    private static void showMenuAdmin() {
        running = true;

        while (running) {
            System.out.println("\n=== MENU ADMINISTRADOR ===");
            System.out.println("1. Visualizar Catálogo");
            System.out.println("2. Adicionar Item");
            System.out.println("3. Remover Item");
            System.out.println("4. Atualizar Item");
            System.out.println("5. Consultar Pedidos");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                int opcao = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opcao) {
                    case 1:
                        visualizarCatalogo();
                        break;
                    case 2:
                         adicionarItem();
                        break;
                    case 3:
                        // removerItem();
                        break;
                    case 4:
                        // atualizarItem();
                        break;
                    case 5:
                        // consultarPedidos();
                        break;
                    case 6:
                        System.out.println("Logout realizado com sucesso!");
                        running = false;
                        break;
                    default:
                        System.out.println("Opção inválida! Por favor, escolha uma opção entre 1 e 6.");
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida! Por favor, digite um número.");
                scanner.nextLine(); // Limpar o buffer em caso de erro
            }
        }
    }

    private static void visualizarCatalogo() throws IOException {
        List<String[]> itensList = catalog.consultCatalog();

        System.out.println("\n=== CATÁLOGO DE PRODUTOS ===");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-20s | %-10s | %-10s%n", "Nome", "Preço", "Quantidade");
        System.out.println("-------------------------------------------------");
        for (String[] itens : itensList) {
            System.out.printf("%-20s | %-10.2f | %-10s%n",
                    itens[CatalogTable.NAME],
                    Double.parseDouble(itens[CatalogTable.PRICE]),
                    itens[CatalogTable.QUANTITY]);
        }
        System.out.println("-------------------------------------------------");
        System.out.println("\n\nCatálogo exibido com sucesso!");
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
            System.out.println("Pressione Enter para voltar ao menu...");
            scanner.nextLine();
        }

    }

    private static void fazerLogin() {
        System.out.println("\n=== FAZER LOGIN ===");

        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        try {
            AuthenticatedUser authenticatedUser = user.singIn(email, senha);
            running = false;
            if (authenticatedUser.getUserType().toString().equals("ADMIN")) {
            } else {
                System.out.println("Login realizado com sucesso! Bem-vindo, " + authenticatedUser.getName() + "!");
                showMenuAdmin();
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer login: " + e.getMessage());
        }

        System.out.println("Pressione Enter para voltar ao menu...");
        scanner.nextLine();
    }

    private static void adicionarItem() {
        // Lógica para adicionar item ao catálogo
        System.out.println("\n=== Adicionar Item no Catálogo ===");

        System.out.print("Digite o nome do item: ");
        String name = scanner.nextLine();

        System.out.print("Digite o preço da unidade: ");
        double price = scanner.nextDouble();

        System.out.print("Digite a quantidade em estoque: ");
        int quantity = scanner.nextInt();


        try {
            Item newItem = new Item(name, price, quantity);
            admin.addItem(newItem);
            System.out.println("Novo item '" + newItem.getName() + "' criada com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao criar item: " + e.getMessage());
            System.out.println("Pressione Enter para voltar ao menu...");
            scanner.nextLine();
        }
    }
}
