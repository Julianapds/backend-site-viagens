package br.com.rotasdosol.repositorios;

import br.com.rotasdosol.bancodedados.ConexaoBancoDeDados;
import br.com.rotasdosol.entidades.Destino;
import br.com.rotasdosol.entidades.Voo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VooRepositorio implements Repositorio<Voo> {

    @Override
    public void criar(Voo voo) {
        String sql = "INSERT INTO voo (idVoo, companhiaAerea, dataPartida, dataChegada, valorPreco, idDestino) VALUES (?, ?, ?, ?, ?, ?);";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, voo.getIdVoo());
            pstmt.setString(2, voo.getCompanhiaAerea());
            pstmt.setDate(3, new java.sql.Date(voo.getDataPartida().getTime()));
            pstmt.setDate(4, new java.sql.Date(voo.getDataChegada().getTime()));
            pstmt.setDouble(5, voo.getValorPreco());
            pstmt.setInt(6, voo.getIdDestino());

            pstmt.executeUpdate();
            System.out.println("Voo criado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar voo." + e.getMessage());
        }
    }

    @Override
    public List<Voo> listar() {
        List<Voo> voos = new ArrayList<>();
        String sql = "SELECT * FROM voo;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Voo voo = new Voo();
                voo.setIdVoo(resultSet.getInt("idVoo"));
                voo.setCompanhiaAerea(resultSet.getString("companhiaAerea"));
                voo.setDataPartida(resultSet.getDate("dataPartida"));
                voo.setDataChegada(resultSet.getDate("dataChegada"));
                voo.setValorPreco(resultSet.getDouble("valorPreco"));
                voo.setIdDestino(resultSet.getInt("idDestino"));
                voos.add(voo);
            }

            System.out.println("Voos consultados com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao consultar voos." + e.getMessage());
        }
        return voos;
    }

    @Override
    public Voo atualizar(Voo voo) {
        String sql = "UPDATE voo SET companhiaAerea = ?, dataPartida = ?, dataChegada = ?, valorPreco = ?, idDestino = ? WHERE idVoo = ?;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, voo.getCompanhiaAerea());
            pstmt.setDate(2, new java.sql.Date(voo.getDataPartida().getTime()));
            pstmt.setDate(3, new java.sql.Date(voo.getDataChegada().getTime()));
            pstmt.setDouble(4, voo.getValorPreco());
            pstmt.setInt(5, voo.getIdDestino());
            pstmt.setInt(6, voo.getIdVoo());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Voo atualizado com sucesso.");
                return voo;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar voo. " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM voo WHERE idVoo = ?;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Voo deletado com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar voo. " + e.getMessage());
        }
    }

    public Voo buscarPorId(Integer idVoo) {
        String sql = "SELECT * FROM voo WHERE idVoo = ?;";
        Voo voo = null;

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idVoo);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                voo = new Voo();
                voo.setIdVoo(resultSet.getInt("idVoo"));
                voo.setCompanhiaAerea(resultSet.getString("companhiaAerea"));
                voo.setDataPartida(resultSet.getDate("dataPartida"));
                voo.setDataChegada(resultSet.getDate("dataChegada"));
                voo.setValorPreco(resultSet.getDouble("valorPreco"));
                voo.setIdDestino(resultSet.getInt("idDestino"));
                System.out.println("Voo encontrado com sucesso.");
            } else {
                System.out.println("Voo não encontrado para o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar voo por ID. " + e.getMessage());
        }

        return voo;
    }
}