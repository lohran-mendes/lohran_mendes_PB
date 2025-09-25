package edu.infnet.view;

import edu.infnet.model.address.Address;
import edu.infnet.model.item.Item;
import edu.infnet.model.user.AdminUser;
import edu.infnet.model.user.AuthenticatedUser;
import edu.infnet.model.user.UnauthenticatedUser;
import edu.infnet.repository.address.AddressRepository;
import edu.infnet.repository.item.ItemTable;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Console {
    private static final Scanner scanner = new Scanner(System.in);
    private static UnauthenticatedUser user = new UnauthenticatedUser();
    private static AdminUser admin = new AdminUser("admin", "admin@gmail.com", "admin123");
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
            System.out.print("\nEscolha uma opção: ");

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
            System.out.print("\nEscolha uma opção: ");

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
                System.out.println("\nEntrada inválida! Por favor, digite um número.");
                scanner.nextLine(); // Limpar o buffer em caso de erro
            }
        }
    }

    private static void visualizarCatalogo() throws IOException {
        List<String[]> itensList = user.consultCatalog();

        System.out.println("\n=== CATÁLOGO DE PRODUTOS ===");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-20s | %-10s | %-10s%n", "Nome", "Preço", "Quantidade");
        System.out.println("-------------------------------------------------");
        for (String[] itens : itensList) {
            System.out.printf("%-20s | %-10.2f | %-10s%n",
                    itens[ItemTable.NAME],
                    Double.parseDouble(itens[ItemTable.PRICE]),
                    itens[ItemTable.QUANTITY]);
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

        System.out.println("Deseja adicionar um endereço? (s/n)");
        String resposta = scanner.nextLine();
        if (resposta.equalsIgnoreCase("s")) {
            Address address = adicionarEndereco();
            try {
                AuthenticatedUser novoUsuario = new AuthenticatedUser(nome, email, senha , address);
                user.singUp(novoUsuario);
                System.out.println("Conta criada com sucesso!");
            } catch (Exception e) {
                System.out.println("\nErro ao criar conta: " + e.getMessage());
                System.out.println("Pressione Enter para voltar ao menu...");
                scanner.nextLine();
            }
        } else {
            try {
                AuthenticatedUser novoUsuario = new AuthenticatedUser(nome, email, senha);
                user.singUp(novoUsuario);
                System.out.println("Conta criada com sucesso!");
            } catch (Exception e) {
                System.out.println("\nErro ao criar conta: " + e.getMessage());
                System.out.println("Pressione Enter para voltar ao menu...");
                scanner.nextLine();
            }
        }

    }

    private static Address adicionarEndereco() {
        System.out.println("\n=== ADICIONAR ENDEREÇO ===");

        System.out.print("Qual o seu estado: ");
        String estado = scanner.nextLine();

        System.out.print("Qual a sua cidade: ");
        String cidade = scanner.nextLine();

        System.out.print("Qual o seu CEP: ");
        String cep = scanner.nextLine();

        System.out.print("Qual o nome da sua rua: ");
        String rua = scanner.nextLine();

        System.out.print("Qual o número da sua casa: ");
        String numero = scanner.nextLine();

        try {
            Address newAddress = new Address(estado, cidade, cep, rua, Integer.parseInt(numero));
            AddressRepository.saveNewAddress(newAddress);
            return newAddress;
        } catch (Exception e) {
            System.out.println("\nErro ao adicionar endereço: " + e.getMessage());
            System.out.println("Pressione Enter para voltar ao menu...");
            scanner.nextLine();
            return null;
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
            System.out.println("\nErro ao criar item: " + e.getMessage());
            System.out.println("Pressione Enter para voltar ao menu...");
            scanner.nextLine();
        }
    }
}
