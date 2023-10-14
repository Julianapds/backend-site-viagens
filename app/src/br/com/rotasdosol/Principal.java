package br.com.rotasdosol;


import br.com.rotasdosol.entidades.Cliente;
import br.com.rotasdosol.repositorios.ClienteRepositorio;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        ClienteRepositorio clienteRepositorio = new ClienteRepositorio();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            System.out.print("Digite a opção desejada: ");
            opcao = scanner.nextInt();

            scanner.nextLine();
            switch (opcao) {
                case 1:
                    listarCliente(clienteRepositorio);
                    break;
                case 2:
                    cadastrarNovoCliente(scanner, clienteRepositorio);
                    break;
                case 3:
                    atualizarCliente(scanner, clienteRepositorio);
                    break;
                case 4:
                    deletarCliente(scanner, clienteRepositorio);
                    break;
                case 5:
                    // Deletar uma ideia
                    break;
                case 6:
                    // Adicionar comentário a uma ideia
                    break;
                case 7:
                    // Visualizar comentários de uma ideia
                    break;
                case 8:
                    // Deletar comentários de uma ideia
                    break;
                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 9);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("=========================================");
        System.out.println("            APP - Rotas do Sol          ");
        System.out.println("=========================================");
        System.out.println("1) Listar clientes");
        System.out.println("2) Cadastrar novo cliente");
        System.out.println("3) Atualizar cliente");
        System.out.println("4) Deletar cliente");
        System.out.println("5) Sair");
        System.out.println();
    }

    private static void cadastrarNovoCliente(Scanner scanner, ClienteRepositorio clienteRepositorio) {
        System.out.println("Digite seu e-mail: ");
        String email = scanner.nextLine();
        System.out.println("Digite seu CPF: ");
        String cpf = scanner.nextLine();
        System.out.println("Digite seu telefone: ");
        String telefone = scanner.nextLine();
        System.out.println("Digite seu endereço: ");
        String endereco = scanner.nextLine();

        Cliente cliente = new Cliente();
        cliente.setIdCliente(new Random().nextInt(100 - 1 + 1));
        cliente.setEmail(email);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setEndereco(endereco);

        clienteRepositorio.criar(cliente);

        System.out.println("Cliente cadastrado com sucesso! ID: " + cliente.getIdCliente());
        System.out.println();
    }

    private static void listarCliente(ClienteRepositorio clienteRepositorio) {
        System.out.println(clienteRepositorio.listar());
    }

    private static void atualizarCliente(Scanner scanner, ClienteRepositorio clienteRepositorio) {

        System.out.println("Digite o CPF do cliente que pretende atualizar:");
        String cpf = scanner.nextLine();
        System.out.println("Digite o novo endereço de e-mail ou tecle Enter para usar o mesmo:");
        String email = scanner.nextLine();
        System.out.println("Digite o novo telefone ou tecle Enter para usar o mesmo: ");
        String telefone = scanner.nextLine();
        System.out.println("Digite seu novo endereço ou tecle Enter para usar o mesmo: ");
        String endereco = scanner.nextLine();

        var clienteEncontrado = clienteRepositorio.buscarPorCpf(cpf);

        if(clienteEncontrado == null) {
            return;
        }

        if(email != null && !email.isBlank()) {
            clienteEncontrado.setEmail(email);
        }
        if(telefone != null && !telefone.isBlank()) {
            clienteEncontrado.setTelefone(telefone);
        }
        if(endereco != null && !endereco.isBlank()) {
            clienteEncontrado.setEndereco(endereco);
        }

        var clienteAtualizado = clienteRepositorio.atualizar(clienteEncontrado);

        System.out.println("Cliente do CPF "+ clienteAtualizado.getCpf() + " foi atualizado com sucesso!");

    }

    private static void deletarCliente(Scanner scanner, ClienteRepositorio clienteRepositorio) {
        System.out.print("Digite o ID do cliente que pretende atualizar: ");
        Integer id = scanner.nextInt();
        clienteRepositorio.deletar(id);
    }
}
