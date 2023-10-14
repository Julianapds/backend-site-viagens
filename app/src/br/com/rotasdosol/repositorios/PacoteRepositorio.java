package br.com.rotasdosol.repositorios;

import br.com.rotasdosol.bancodedados.ConexaoBancoDeDados;
import br.com.rotasdosol.entidades.Pacote;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacoteRepositorio implements Repositorio<Pacote> {

    @Override
    public void criar(Pacote pacote) {
        String sql = "INSERT INTO pacote (id_pacote, valor_preco, id_hospedagem, id_voo) VALUES (?, ?, ?, ?);";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, pacote.getIdPacote());
            pstmt.setDouble(2, pacote.getValorPreco());
            if (pacote.getIdHospedagem() != null) {
                pstmt.setInt(3, pacote.getIdHospedagem());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            if (pacote.getIdVoo() != null) {
                pstmt.setInt(4, pacote.getIdVoo());
            } else {
                pstmt.setNull(4, Types.INTEGER);
            }

            pstmt.executeUpdate();
            System.out.println("Pacote criado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar pacote. " + e.getMessage());
        }
    }

    @Override
    public List<Pacote> listar() {
        List<Pacote> pacotes = new ArrayList<>();
        String sql = "SELECT * FROM pacote;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Pacote pacote = new Pacote();
                pacote.setIdPacote(resultSet.getInt("id_pacote"));
                pacote.setValorPreco(resultSet.getDouble("valor_preco"));
                pacote.setIdHospedagem((Integer) resultSet.getObject("id_hospedagem"));
                pacote.setIdVoo((Integer) resultSet.getObject("id_voo"));
                pacotes.add(pacote);
            }

            System.out.println("Pacotes consultados com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao consultar pacotes. " + e.getMessage());
        }

        return pacotes;
    }

    @Override
    public Pacote atualizar(Pacote pacote) {
        String sql = "UPDATE pacote SET valor_preco = ?, id_hospedagem = ?, id_voo = ? WHERE id_pacote = ?;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, pacote.getValorPreco());
            if (pacote.getIdHospedagem() != null) {
                pstmt.setInt(2, pacote.getIdHospedagem());
            } else {
                pstmt.setNull(2, Types.INTEGER);
            }
            if (pacote.getIdVoo() != null) {
                pstmt.setInt(3, pacote.getIdVoo());
            } else {
                pstmt.setNull(3, Types.INTEGER);
            }
            pstmt.setInt(4, pacote.getIdPacote());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Pacote atualizado com sucesso.");
                return pacote;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar pacote. " + e.getMessage());
        }

        return null;
    }

    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM pacote WHERE id_pacote = ?;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Pacote deletado com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar pacote. " + e.getMessage());
        }
    }

    public Pacote buscarPorId(Integer id) {
        String sql = "SELECT * FROM pacote WHERE idPacote = ?;";
        Pacote pacote = null;

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                pacote = new Pacote();
                pacote.setIdPacote(resultSet.getInt("idPacote"));
                pacote.setValorPreco(resultSet.getDouble("valorPreco"));
                pacote.setIdHospedagem(resultSet.getInt("idHospedagem"));
                pacote.setIdVoo(resultSet.getInt("idVoo"));
                System.out.println("Pacote encontrado com sucesso.");
            } else {
                System.out.println("Pacote não encontrado para o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar pacote por ID. " + e.getMessage());
        }

        return pacote;
    }

}
