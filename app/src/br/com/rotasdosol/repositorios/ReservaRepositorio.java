package br.com.rotasdosol.repositorios;

import br.com.rotasdosol.bancodedados.ConexaoBancoDeDados;
import br.com.rotasdosol.entidades.Reserva;
import br.com.rotasdosol.entidades.StatusReserva;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepositorio implements Repositorio<Reserva> {

    @Override
    public void criar(Reserva reserva) {
        String sql = "INSERT INTO reserva (idReserva, idCliente, idPacote, idHospedagem, idVoo, dataReserva, statusReserva) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reserva.getIdReserva());
            pstmt.setInt(2, reserva.getIdCliente());
            pstmt.setInt(3, reserva.getIdPacote());
            pstmt.setInt(4, reserva.getIdHospedagem());
            pstmt.setInt(5, reserva.getIdVoo());
            pstmt.setDate(6, new java.sql.Date(reserva.getDataReserva().getTime()));
            pstmt.setString(7, reserva.getStatusReserva().name());

            pstmt.executeUpdate();
            System.out.println("Reserva criada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar reserva. " + e.getMessage());
        }
    }


    @Override
    public List<Reserva> listar() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Reserva reservaEntidade = new Reserva();
                reservaEntidade.setIdReserva(resultSet.getInt("idReserva"));
                reservaEntidade.setIdCliente(resultSet.getInt("idCliente"));
                reservaEntidade.setIdPacote(resultSet.getInt("idPacote"));
                reservaEntidade.setIdHospedagem(resultSet.getInt("idHospedagem"));
                reservaEntidade.setIdVoo(resultSet.getInt("idVoo"));
                reservaEntidade.setDataReserva(resultSet.getDate("dataReserva"));
                reservaEntidade.setStatusReserva(StatusReserva.valueOf(resultSet.getString("statusReserva")));

                reservas.add(reservaEntidade);
            }
            System.out.println("Reservas consultadas com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao consultar reservas. " + e.getMessage());
        }
        return reservas;
    }

    public List<Reserva> listar(Integer clienteId) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva WHERE idCliente = ? AND statusReserva = 'RESERVADO';";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, clienteId);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                Reserva reservaEntidade = new Reserva();
                reservaEntidade.setIdReserva(resultSet.getInt("idReserva"));
                reservaEntidade.setIdCliente(resultSet.getInt("idCliente"));
                reservaEntidade.setIdPacote(resultSet.getInt("idPacote"));
                reservaEntidade.setIdHospedagem(resultSet.getInt("idHospedagem"));
                reservaEntidade.setIdVoo(resultSet.getInt("idVoo"));
                reservaEntidade.setDataReserva(resultSet.getDate("dataReserva"));
                reservaEntidade.setStatusReserva(StatusReserva.valueOf(resultSet.getString("statusReserva")));

                reservas.add(reservaEntidade);
            }
            System.out.println("Reservas do cliente consultadas com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao consultar reservas do cliente. " + e.getMessage());
        }
        return reservas;
    }

    @Override
    public Reserva atualizar(Reserva reserva) {
        String sql = "UPDATE reserva SET idCliente = ?, idPacote = ?, idHospedagem = ?, idVoo = ?, dataReserva = ?, statusReserva = ? WHERE idReserva = ?;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, reserva.getIdCliente());
            pstmt.setInt(2, reserva.getIdPacote());
            pstmt.setInt(3, reserva.getIdHospedagem());
            pstmt.setInt(4, reserva.getIdVoo());
            pstmt.setDate(5, new java.sql.Date(reserva.getDataReserva().getTime()));
            pstmt.setString(6, reserva.getStatusReserva().name());
            pstmt.setInt(7, reserva.getIdReserva());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Reserva atualizada com sucesso.");
                return reserva;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar reserva. " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deletar(Integer idReserva) {
        String sql = "UPDATE reserva SET statusReserva = 'CANCELADO' WHERE idReserva = ?;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idReserva);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Reserva cancelada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao cancelar reserva. " + e.getMessage());
        }
    }

    public Reserva buscarPorId(Integer idReserva) {
        String sql = "SELECT * FROM reserva WHERE idReserva = ?;";
        Reserva reserva = null;

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idReserva);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                reserva = new Reserva();
                reserva.setIdReserva(resultSet.getInt("idReserva"));
                reserva.setIdCliente(resultSet.getInt("idCliente"));
                reserva.setIdPacote(resultSet.getInt("idPacote"));
                reserva.setIdHospedagem(resultSet.getInt("idHospedagem"));
                reserva.setIdVoo(resultSet.getInt("idVoo"));
                reserva.setDataReserva(resultSet.getDate("dataReserva"));
                reserva.setStatusReserva(StatusReserva.valueOf(resultSet.getString("statusReserva")));

                System.out.println("Reserva encontrada com sucesso.");
            } else {
                System.out.println("Reserva não encontrada para o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar reserva por ID. " + e.getMessage());
        }

        return reserva;
    }


}
