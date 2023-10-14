package br.com.rotasdosol.repositorios;

import br.com.rotasdosol.bancodedados.ConexaoBancoDeDados;
import br.com.rotasdosol.entidades.Cliente;
import br.com.rotasdosol.entidades.Destino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DestinoRepositorio implements Repositorio<Destino> {

    @Override
    public void criar(Destino destino) {
        String sql = "INSERT INTO destino (idDestino, nome, pais, cidade) VALUES (?, ?, ?, ?);";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, destino.getIdDestino());
            pstmt.setString(2, destino.getNome());
            pstmt.setString(3, destino.getPais());
            pstmt.setString(4, destino.getCidade());

            pstmt.executeUpdate();
            System.out.println("Destino criado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar destino." + e.getMessage());
        }
    }

    @Override
    public List<Destino> listar() {
        List<Destino> destinos = new ArrayList<>();
        String sql = "SELECT * FROM destino;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Destino destino = new Destino();
                destino.setIdDestino(resultSet.getInt("idDestino"));
                destino.setNome(resultSet.getString("nome"));
                destino.setPais(resultSet.getString("pais"));
                destino.setCidade(resultSet.getString("cidade"));
                destinos.add(destino);
            }

            System.out.println("Destinos consultados com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao consultar destinos." + e.getMessage());
        }
        return destinos;
    }

    @Override
    public Destino atualizar(Destino destino) {
        String sql = "UPDATE destino SET nome = ?, pais = ?, cidade = ? WHERE idDestino = ?;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, destino.getNome());
            pstmt.setString(2, destino.getPais());
            pstmt.setString(3, destino.getCidade());
            pstmt.setInt(4, destino.getIdDestino());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Destino atualizado com sucesso.");
                return destino;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar destino. " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM destino WHERE idDestino = ?;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Destino deletado com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar destino. " + e.getMessage());
        }
    }

    public Destino buscarPorId(Integer idDestino) {
        String sql = "SELECT * FROM destino WHERE idDestino = ?;";
        Destino destino = null;

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idDestino);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                destino = new Destino();
                destino.setIdDestino(resultSet.getInt("idDestino"));
                destino.setNome(resultSet.getString("nome"));
                destino.setPais(resultSet.getString("pais"));
                destino.setCidade(resultSet.getString("cidade"));
                System.out.println("Destino encontrado com sucesso.");
            } else {
                System.out.println("Destino não encontrado para o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar destino por ID. " + e.getMessage());
        }

        return destino;
    }
}