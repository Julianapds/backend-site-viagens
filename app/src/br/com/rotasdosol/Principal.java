package br.com.rotasdosol;


import br.com.rotasdosol.entidades.Cliente;
import br.com.rotasdosol.entidades.Destino;
import br.com.rotasdosol.entidades.Voo;
import br.com.rotasdosol.repositorios.ClienteRepositorio;
import br.com.rotasdosol.repositorios.DestinoRepositorio;
import br.com.rotasdosol.repositorios.VooRepositorio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
        DestinoRepositorio destinoRepositorio = new DestinoRepositorio();
        VooRepositorio vooRepositorio = new VooRepositorio();

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
                    listarDestinos(destinoRepositorio);
                    break;
                case 6:
                    cadastrarNovoDestino(scanner, destinoRepositorio);
                    break;
                case 7:
                    atualizarDestino(scanner, destinoRepositorio);
                    break;
                case 8:
                    deletarDestino(scanner, destinoRepositorio);
                    break;
                case 9:
                    listarVoos(vooRepositorio);
                    break;
                case 10:
                    cadastrarNovoVoo(scanner, vooRepositorio);
                    break;
                case 11:
                    atualizarVoo(scanner, vooRepositorio);
                    break;
                case 12:
                    deletarVoo(scanner, vooRepositorio);
                    break;
                case 13:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 13);

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
        System.out.println("5) Listar destinos");
        System.out.println("6) Cadastrar novo destino");
        System.out.println("7) Atualizar destino");
        System.out.println("8) Deletar destino");
        System.out.println("9) Listar voos");
        System.out.println("10) Cadastrar novo voo");
        System.out.println("11) Atualizar voo");
        System.out.println("12) Deletar voo");
        System.out.println("13) Sair");
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

    private static void cadastrarNovoDestino(Scanner scanner, DestinoRepositorio destinoRepositorio) {
        System.out.println("Digite o nome do destino: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o país do destino: ");
        String pais = scanner.nextLine();
        System.out.println("Digite a cidade do destino: ");
        String cidade = scanner.nextLine();

        Destino destino = new Destino();
        destino.setIdDestino(new Random().nextInt(1000 - 1 + 1)); // Ajustando para ter um range maior
        destino.setNome(nome);
        destino.setPais(pais);
        destino.setCidade(cidade);

        destinoRepositorio.criar(destino);

        System.out.println("Destino cadastrado com sucesso! ID: " + destino.getIdDestino());
        System.out.println();
    }

    private static void listarDestinos(DestinoRepositorio destinoRepositorio) {
        System.out.println(destinoRepositorio.listar());
    }

    private static void atualizarDestino(Scanner scanner, DestinoRepositorio destinoRepositorio) {

        System.out.println("Digite o ID do destino que pretende atualizar:");
        Integer id = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        System.out.println("Digite o novo nome ou tecle Enter para usar o mesmo:");
        String nome = scanner.nextLine();
        System.out.println("Digite o novo país ou tecle Enter para usar o mesmo:");
        String pais = scanner.nextLine();
        System.out.println("Digite a nova cidade ou tecle Enter para usar o mesmo:");
        String cidade = scanner.nextLine();

        var destinoEncontrado = destinoRepositorio.buscarPorId(id);

        if(destinoEncontrado == null) {
            System.out.println("Destino não encontrado.");
            return;
        }

        if(nome != null && !nome.isBlank()) {
            destinoEncontrado.setNome(nome);
        }
        if(pais != null && !pais.isBlank()) {
            destinoEncontrado.setPais(pais);
        }
        if(cidade != null && !cidade.isBlank()) {
            destinoEncontrado.setCidade(cidade);
        }

        var destinoAtualizado = destinoRepositorio.atualizar(destinoEncontrado);

        System.out.println("Destino de ID "+ destinoAtualizado.getIdDestino() + " foi atualizado com sucesso!");

    }

    private static void deletarDestino(Scanner scanner, DestinoRepositorio destinoRepositorio) {
        System.out.print("Digite o ID do destino que pretende deletar: ");
        Integer id = scanner.nextInt();
        destinoRepositorio.deletar(id);
        System.out.println("Destino deletado com sucesso!");
    }

    private static void listarVoos(VooRepositorio vooRepositorio) {
        System.out.println(vooRepositorio.listar());
    }

    private static void cadastrarNovoVoo(Scanner scanner, VooRepositorio vooRepositorio) {
        System.out.println("Digite a companhia aérea:");
        String companhiaAerea = scanner.nextLine();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date dataPartida = null;
        System.out.println("Digite a data de partida (formato YYYY-MM-DD):");
        String dataPartidaStr = scanner.nextLine();
        try {
            dataPartida = formatter.parse(dataPartidaStr);
        } catch (ParseException e) {
            System.out.println("Erro na data de partida: " + e.getMessage());
            return;
        }

        Date dataChegada = null;
        System.out.println("Digite a data de chegada (formato YYYY-MM-DD):");
        String dataChegadaStr = scanner.nextLine();
        try {
            dataChegada = formatter.parse(dataChegadaStr);
        } catch (ParseException e) {
            System.out.println("Erro na data de chegada: " + e.getMessage());
            return;
        }

        System.out.println("Digite o valor do voo:");
        Double valorPreco = scanner.nextDouble();

        System.out.println("Digite o ID do destino:");
        Integer idDestino = scanner.nextInt();
        scanner.nextLine();

        Voo voo = new Voo();
        voo.setIdVoo(new Random().nextInt(100 - 1 + 1));
        voo.setCompanhiaAerea(companhiaAerea);
        voo.setDataPartida(dataPartida);
        voo.setDataChegada(dataChegada);
        voo.setValorPreco(valorPreco);
        voo.setIdDestino(idDestino);

        vooRepositorio.criar(voo);

        System.out.println("Voo cadastrado com sucesso! ID: " + voo.getIdVoo());
        System.out.println();
    }


    private static void atualizarVoo(Scanner scanner, VooRepositorio vooRepositorio) {

        System.out.println("Digite o ID do voo que pretende atualizar:");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite a nova companhia aérea ou tecle Enter para usar a mesma:");
        String companhiaAerea = scanner.nextLine();

        var vooEncontrado = vooRepositorio.buscarPorId(id);

        if(vooEncontrado == null) {
            System.out.println("Voo não encontrado.");
            return;
        }

        if(companhiaAerea != null && !companhiaAerea.isBlank()) {
            vooEncontrado.setCompanhiaAerea(companhiaAerea);
        }

        var vooAtualizado = vooRepositorio.atualizar(vooEncontrado);

        System.out.println("Voo de ID "+ vooAtualizado.getIdVoo() + " foi atualizado com sucesso!");
    }

    private static void deletarVoo(Scanner scanner, VooRepositorio vooRepositorio) {
        System.out.print("Digite o ID do voo que pretende deletar: ");
        Integer id = scanner.nextInt();
        vooRepositorio.deletar(id);
        System.out.println("Voo deletado com sucesso!");
    }
}
