package br.com.rotasdosol.repositorios;

import br.com.rotasdosol.bancodedados.ConexaoBancoDeDados;
import br.com.rotasdosol.entidades.Hospedagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HospedagemRepositorio implements Repositorio<Hospedagem> {

    @Override
    public void criar(Hospedagem hospedagem) {
        String sql = "INSERT INTO hospedagem (idHospedagem, nomeHotel, tipoQuarto, dataCheckin, dataCheckout, valorPernoite, idDestino) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, hospedagem.getIdHospedagem());
            pstmt.setString(2, hospedagem.getNomeHotel());
            pstmt.setString(3, hospedagem.getTipoQuarto());
            pstmt.setDate(4, new java.sql.Date(hospedagem.getDataCheckin().getTime()));
            pstmt.setDate(5, new java.sql.Date(hospedagem.getDataCheckout().getTime()));
            pstmt.setDouble(6, hospedagem.getValorPernoite());
            pstmt.setInt(7, hospedagem.getIdDestino());

            pstmt.executeUpdate();
            System.out.println("Hospedagem criada com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar hospedagem." + e.getMessage());
        }
    }

    @Override
    public List<Hospedagem> listar() {
        List<Hospedagem> hospedagens = new ArrayList<>();
        String sql = "SELECT * FROM hospedagem;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                Hospedagem hospedagem = new Hospedagem();
                hospedagem.setIdHospedagem(resultSet.getInt("idHospedagem"));
                hospedagem.setNomeHotel(resultSet.getString("nomeHotel"));
                hospedagem.setTipoQuarto(resultSet.getString("tipoQuarto"));
                hospedagem.setDataCheckin(resultSet.getDate("dataCheckin"));
                hospedagem.setDataCheckout(resultSet.getDate("dataCheckout"));
                hospedagem.setValorPernoite(resultSet.getDouble("valorPernoite"));
                hospedagem.setIdDestino(resultSet.getInt("idDestino"));
                hospedagens.add(hospedagem);
            }

            System.out.println("Hospedagens consultadas com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao consultar hospedagens." + e.getMessage());
        }
        return hospedagens;
    }

    @Override
    public Hospedagem atualizar(Hospedagem hospedagem) {
        String sql = "UPDATE hospedagem SET nomeHotel = ?, tipoQuarto = ?, dataCheckin = ?, dataCheckout = ?, valorPernoite = ?, idDestino = ? WHERE idHospedagem = ?;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, hospedagem.getNomeHotel());
            pstmt.setString(2, hospedagem.getTipoQuarto());
            pstmt.setDate(3, new java.sql.Date(hospedagem.getDataCheckin().getTime()));
            pstmt.setDate(4, new java.sql.Date(hospedagem.getDataCheckout().getTime()));
            pstmt.setDouble(5, hospedagem.getValorPernoite());
            pstmt.setInt(6, hospedagem.getIdDestino());
            pstmt.setInt(7, hospedagem.getIdHospedagem());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Hospedagem atualizada com sucesso.");
                return hospedagem;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar hospedagem. " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM hospedagem WHERE idHospedagem = ?;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Hospedagem deletada com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar hospedagem. " + e.getMessage());
        }
    }

    public Hospedagem buscarPorId(Integer idHospedagem) {
        String sql = "SELECT * FROM hospedagem WHERE idHospedagem = ?;";
        Hospedagem hospedagem = null;

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idHospedagem);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                hospedagem = new Hospedagem();
                hospedagem.setIdHospedagem(resultSet.getInt("idHospedagem"));
                hospedagem.setNomeHotel(resultSet.getString("nomeHotel"));
                hospedagem.setTipoQuarto(resultSet.getString("tipoQuarto"));
                hospedagem.setDataCheckin(resultSet.getDate("dataCheckin"));
                hospedagem.setDataCheckout(resultSet.getDate("dataCheckout"));
                hospedagem.setValorPernoite(resultSet.getDouble("valorPernoite"));
                hospedagem.setIdDestino(resultSet.getInt("idDestino"));
                System.out.println("Hospedagem encontrada com sucesso.");
            } else {
                System.out.println("Hospedagem não encontrada para o ID fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar hospedagem por ID. " + e.getMessage());
        }

        return hospedagem;
    }
}

