package br.com.rotasdosol.repositorios;

import br.com.rotasdosol.bancodedados.ConexaoBancoDeDados;
import br.com.rotasdosol.entidades.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ClienteRepositorio implements Repositorio<Cliente> {


    @Override
    public void criar(Cliente cliente) {
        String sql = "INSERT INTO cliente (id_cliente, email, cpf, telefone, endereco) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, cliente.getIdCliente());
            pstmt.setString(2, cliente.getEmail());
            pstmt.setString(3, cliente.getCpf());
            pstmt.setString(4, cliente.getTelefone());
            pstmt.setString(5, cliente.getEndereco());

            pstmt.executeUpdate();
            System.out.println("Cliente criado com sucesso.");
        } catch (SQLException e) {
            System.out.println("Erro ao criar cliente." + e.getMessage());
        }
    }

        @Override
    public List<Cliente> listar() {
        return null;
    }

    @Override
    public Object atualizar(Integer id, Cliente object) {
        return null;
    }

    @Override
    public void deletar(Integer id) {

    }
}
