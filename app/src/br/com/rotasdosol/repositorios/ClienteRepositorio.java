package br.com.rotasdosol.repositorios;

import br.com.rotasdosol.bancodedados.ConexaoBancoDeDados;
import br.com.rotasdosol.entidades.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        List<Cliente>clientes=new ArrayList<>();
            String sql ="SELECT * FROM cliente;";
            try (Connection conn = ConexaoBancoDeDados.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                ResultSet resultSet =pstmt. executeQuery();
                while (resultSet.next()) {
                    Cliente cliente=new Cliente();
                    cliente.setIdCliente(resultSet.getInt("id_cliente"));

                    cliente.setCpf(resultSet.getString("cpf"));
                    cliente.setEmail(resultSet.getString("email"));
                    cliente.setEndereco(resultSet.getString("endereco"));
                    cliente.setTelefone(resultSet.getString("telefone"));
                    clientes.add(cliente);
                }



                System.out.println("Clientes consultados com sucesso.");
            } catch (SQLException e) {
                System.out.println("Erro ao consultar clientes." + e.getMessage());
            }
            return clientes;

    }

    @Override
    public Object atualizar(Integer id, Cliente object) {
        return null;
    }

    @Override
    public void deletar(Integer id) {

    }
}
