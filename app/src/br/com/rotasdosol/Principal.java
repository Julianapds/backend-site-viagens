package br.com.rotasdosol;


import br.com.rotasdosol.entidades.*;
import br.com.rotasdosol.repositorios.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        ClienteRepositorio clienteRepositorio = new ClienteRepositorio();
        DestinoRepositorio destinoRepositorio = new DestinoRepositorio();
        VooRepositorio vooRepositorio = new VooRepositorio();
        HospedagemRepositorio hospedagemRepositorio = new HospedagemRepositorio();
        PacoteRepositorio pacoteRepositorio = new PacoteRepositorio();
        ReservaRepositorio reservaRepositorio = new ReservaRepositorio();

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
                    listarHospedagens(hospedagemRepositorio);
                    break;
                case 14:
                    cadastrarNovaHospedagem(scanner, hospedagemRepositorio);
                    break;
                case 15:
                    atualizarHospedagem(scanner, hospedagemRepositorio);
                    break;
                case 16:
                    deletarHospedagem(scanner, hospedagemRepositorio);
                    break;
                case 17:
                    listarPacotes(pacoteRepositorio);
                    break;
                case 18:
                    cadastrarNovoPacote(scanner, pacoteRepositorio);
                    break;
                case 19:
                    atualizarPacote(scanner, pacoteRepositorio);
                    break;
                case 20:
                    deletarPacote(scanner, pacoteRepositorio);
                    break;
                case 21:
                    listarReservasDeCliente(scanner, reservaRepositorio);
                    break;
                case 22:
                    criarReserva(scanner, reservaRepositorio, clienteRepositorio,pacoteRepositorio,hospedagemRepositorio,vooRepositorio);
                    break;
                case 23:
                    atualizarReserva(scanner, reservaRepositorio);
                case 24:
                    deletarReserva(scanner,reservaRepositorio);
                case 25:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 25);

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
        System.out.println("13) Listar hospedagens");
        System.out.println("14) Cadastrar nova hospedagem");
        System.out.println("15) Atualizar hospedagem");
        System.out.println("16) Deletar hospedagem");
        System.out.println("17) Listar pacotes");
        System.out.println("18) Cadastrar novo pacote");
        System.out.println("19) Atualizar pacote");
        System.out.println("20) Deletar pacote");
        System.out.println("21) Listar reservar por cliente");
        System.out.println("22) Cadastrar nova reserva para cliente");
        System.out.println("23) Atualizar reserva");
        System.out.println("24) Cancelar reserva");
        System.out.println("25) Sair");
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
        destino.setIdDestino(new Random().nextInt(1000 - 1 + 1));
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

    private static void listarHospedagens(HospedagemRepositorio hospedagemRepositorio) {
        System.out.println(hospedagemRepositorio.listar());
    }

    private static void cadastrarNovaHospedagem(Scanner scanner, HospedagemRepositorio hospedagemRepositorio) {
        System.out.println("Digite o nome do hotel:");
        String nomeHotel = scanner.nextLine();

        System.out.println("Digite o tipo de quarto:");
        String tipoQuarto = scanner.nextLine();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date dataCheckin = null;
        System.out.println("Digite a data de check-in (formato YYYY-MM-DD):");
        String dataCheckinStr = scanner.nextLine();
        try {
            dataCheckin = formatter.parse(dataCheckinStr);
        } catch (ParseException e) {
            System.out.println("Erro na data de check-in: " + e.getMessage());
            return;
        }

        Date dataCheckout = null;
        System.out.println("Digite a data de checkout (formato YYYY-MM-DD):");
        String dataCheckoutStr = scanner.nextLine();
        try {
            dataCheckout = formatter.parse(dataCheckoutStr);
        } catch (ParseException e) {
            System.out.println("Erro na data de checkout: " + e.getMessage());
            return;
        }

        System.out.println("Digite o valor da pernoite:");
        Double valorPernoite = scanner.nextDouble();

        System.out.println("Digite o ID do destino:");
        Integer idDestino = scanner.nextInt();
        scanner.nextLine();

        Hospedagem hospedagem = new Hospedagem();
        hospedagem.setIdHospedagem(new Random().nextInt(100 - 1 + 1));
        hospedagem.setNomeHotel(nomeHotel);
        hospedagem.setTipoQuarto(tipoQuarto);
        hospedagem.setDataCheckin(dataCheckin);
        hospedagem.setDataCheckout(dataCheckout);
        hospedagem.setValorPernoite(valorPernoite);
        hospedagem.setIdDestino(idDestino);

        hospedagemRepositorio.criar(hospedagem);

        System.out.println("Hospedagem cadastrada com sucesso! ID: " + hospedagem.getIdHospedagem());
        System.out.println();
    }

    private static void atualizarHospedagem(Scanner scanner, HospedagemRepositorio hospedagemRepositorio) {
        System.out.println("Digite o ID da hospedagem que pretende atualizar:");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o novo nome do hotel ou tecle Enter para usar o mesmo:");
        String nomeHotel = scanner.nextLine();

        var hospedagemEncontrada = hospedagemRepositorio.buscarPorId(id);

        if(hospedagemEncontrada == null) {
            System.out.println("Hospedagem não encontrada.");
            return;
        }

        if(nomeHotel != null && !nomeHotel.isBlank()) {
            hospedagemEncontrada.setNomeHotel(nomeHotel);
        }

        var hospedagemAtualizada = hospedagemRepositorio.atualizar(hospedagemEncontrada);

        System.out.println("Hospedagem de ID "+ hospedagemAtualizada.getIdHospedagem() + " foi atualizada com sucesso!");
    }

    private static void deletarHospedagem(Scanner scanner, HospedagemRepositorio hospedagemRepositorio) {
        System.out.print("Digite o ID da hospedagem que pretende deletar: ");
        Integer id = scanner.nextInt();
        hospedagemRepositorio.deletar(id);
        System.out.println("Hospedagem deletada com sucesso!");
    }

    private static void listarPacotes(PacoteRepositorio pacoteRepositorio) {
        System.out.println(pacoteRepositorio.listar());
    }

    private static void cadastrarNovoPacote(Scanner scanner, PacoteRepositorio pacoteRepositorio) {
        System.out.println("Digite o valor do pacote:");
        Double valorPreco = scanner.nextDouble();
        scanner.nextLine();  // Consume newline

        System.out.println("Digite o ID da hospedagem ou deixe em branco se não tiver:");
        String idHospedagemStr = scanner.nextLine();
        Integer idHospedagem = null;
        if (!idHospedagemStr.isBlank()) {
            idHospedagem = Integer.parseInt(idHospedagemStr);
        }

        System.out.println("Digite o ID do voo ou deixe em branco se não tiver:");
        String idVooStr = scanner.nextLine();
        Integer idVoo = null;
        if (!idVooStr.isBlank()) {
            idVoo = Integer.parseInt(idVooStr);
        }

        Pacote pacote = new Pacote();
        pacote.setIdPacote(new Random().nextInt(1000 - 1 + 1)); // Gerando um ID aleatório entre 1 e 1000
        pacote.setValorPreco(valorPreco);
        pacote.setIdHospedagem(idHospedagem);
        pacote.setIdVoo(idVoo);

        pacoteRepositorio.criar(pacote);

        System.out.println("Pacote cadastrado com sucesso! ID: " + pacote.getIdPacote());
        System.out.println();
    }

    private static void atualizarPacote(Scanner scanner, PacoteRepositorio pacoteRepositorio) {
        System.out.println("Digite o ID do pacote que pretende atualizar:");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o novo valor do pacote ou tecle Enter para usar o mesmo:");
        String valorStr = scanner.nextLine();
        if (!valorStr.isBlank()) {
            Double novoValor = Double.parseDouble(valorStr);
            Pacote pacoteEncontrado = pacoteRepositorio.buscarPorId(id);
            if(pacoteEncontrado == null) {
                System.out.println("Pacote não encontrado.");
                return;
            }
            pacoteEncontrado.setValorPreco(novoValor);
            Pacote pacoteAtualizado = pacoteRepositorio.atualizar(pacoteEncontrado);
            System.out.println("Pacote de ID "+ pacoteAtualizado.getIdPacote() + " foi atualizado com sucesso!");
        }
    }

    private static void deletarPacote(Scanner scanner, PacoteRepositorio pacoteRepositorio) {
        System.out.print("Digite o ID do pacote que pretende deletar: ");
        Integer id = scanner.nextInt();
        pacoteRepositorio.deletar(id);
        System.out.println("Pacote deletado com sucesso!");
    }

    private static void listarReservasDeCliente(Scanner scanner, ReservaRepositorio reservaRepositorio) {
        System.out.println("Digite o ID do cliente para listar suas reservas:");
        Integer idCliente = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        List<Reserva> reservas = reservaRepositorio.listar(idCliente);
        if(reservas.isEmpty()) {
            System.out.println("Nenhuma reserva encontrada para o cliente de ID: " + idCliente);
        } else {
            System.out.println(reservas);
        }
    }

    private static void criarReserva(Scanner scanner, ReservaRepositorio reservaRepositorio, ClienteRepositorio clienteRepositorio, PacoteRepositorio pacoteRepositorio, HospedagemRepositorio hospedagemRepositorio, VooRepositorio vooRepositorio) {
        System.out.println("Digite o ID do cliente:");
        Integer idCliente = scanner.nextInt();
        scanner.nextLine();

        if(clienteRepositorio.buscarPorId(idCliente) == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        System.out.println("Digite o ID do pacote ou deixe em branco se não tiver:");
        String idPacoteStr = scanner.nextLine();
        Integer idPacote = null;
        if (!idPacoteStr.isBlank()) {
            idPacote = Integer.parseInt(idPacoteStr);
            if(pacoteRepositorio.buscarPorId(idPacote) == null) {
                System.out.println("Pacote não encontrado.");
                return;
            }
        }

        System.out.println("Digite o ID da hospedagem ou deixe em branco se não tiver:");
        String idHospedagemStr = scanner.nextLine();
        Integer idHospedagem = null;
        if (!idHospedagemStr.isBlank()) {
            idHospedagem = Integer.parseInt(idHospedagemStr);
            if(hospedagemRepositorio.buscarPorId(idHospedagem) == null) {
                System.out.println("Hospedagem não encontrada.");
                return;
            }
        }

        System.out.println("Digite o ID do voo ou deixe em branco se não tiver:");
        String idVooStr = scanner.nextLine();
        Integer idVoo = null;
        if (!idVooStr.isBlank()) {
            idVoo = Integer.parseInt(idVooStr);
            if(vooRepositorio.buscarPorId(idVoo) == null) {
                System.out.println("Voo não encontrado.");
                return;
            }
        }

        Reserva reserva = new Reserva();
        reserva.setIdReserva(new Random().nextInt(1000 - 1 + 1));
        reserva.setIdCliente(idCliente);
        reserva.setIdPacote(idPacote);
        reserva.setIdHospedagem(idHospedagem);
        reserva.setIdVoo(idVoo);
        reserva.setDataReserva(new Date());
        reserva.setStatusReserva(StatusReserva.RESERVADO);

        reservaRepositorio.criar(reserva);

        System.out.println("Reserva criada com sucesso! ID: " + reserva.getIdReserva());
    }

    private static void atualizarReserva(Scanner scanner, ReservaRepositorio reservaRepositorio) {
        System.out.println("Digite o ID da reserva que pretende atualizar:");
        Integer idReserva = scanner.nextInt();
        scanner.nextLine();

        Reserva reservaEncontrada = reservaRepositorio.buscarPorId(idReserva);
        if(reservaEncontrada == null) {
            System.out.println("Reserva não encontrada.");
            return;
        }

        System.out.println("Deseja atualizar o ID do pacote? (s/n)");
        char resposta = scanner.nextLine().charAt(0);
        if(resposta == 's' || resposta == 'S') {
            System.out.println("Digite o novo ID do pacote:");
            Integer novoIdPacote = scanner.nextInt();
            scanner.nextLine();
            reservaEncontrada.setIdPacote(novoIdPacote);
        }

        System.out.println("Deseja atualizar o ID da hospedagem? (s/n)");
        resposta = scanner.nextLine().charAt(0);
        if(resposta == 's' || resposta == 'S') {
            System.out.println("Digite o novo ID da hospedagem:");
            Integer novoIdHospedagem = scanner.nextInt();
            scanner.nextLine();
            reservaEncontrada.setIdHospedagem(novoIdHospedagem);
        }

        System.out.println("Deseja atualizar o ID do voo? (s/n)");
        resposta = scanner.nextLine().charAt(0);
        if(resposta == 's' || resposta == 'S') {
            System.out.println("Digite o novo ID do voo:");
            Integer novoIdVoo = scanner.nextInt();
            scanner.nextLine();
            reservaEncontrada.setIdVoo(novoIdVoo);
        }

        Reserva reservaAtualizada = reservaRepositorio.atualizar(reservaEncontrada);
        System.out.println("Reserva de ID "+ reservaAtualizada.getIdReserva() + " foi atualizada com sucesso!");
    }

    private static void deletarReserva(Scanner scanner, ReservaRepositorio reservaRepositorio) {
        System.out.print("Digite o ID da reserva que pretende cancelar: ");
        Integer idReserva = scanner.nextInt();

        Reserva reserva = reservaRepositorio.buscarPorId(idReserva);
        if (reserva != null) {
            reserva.setStatusReserva(StatusReserva.CANCELADO);
            reservaRepositorio.atualizar(reserva);
            System.out.println("Reserva cancelada com sucesso!");
        } else {
            System.out.println("Reserva não encontrada.");
        }
    }

}
