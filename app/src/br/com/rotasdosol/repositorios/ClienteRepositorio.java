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
    public Cliente atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET email = ?, cpf = ?, telefone = ?, endereco = ? WHERE id_cliente = ?;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cliente.getEmail());
            pstmt.setString(2, cliente.getCpf());
            pstmt.setString(3, cliente.getTelefone());
            pstmt.setString(4, cliente.getEndereco());
            pstmt.setInt(5, cliente.getIdCliente());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Cliente atualizado com sucesso.");
                return cliente;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar cliente. " + e.getMessage());
        }
        return null;
    }

    @Override
    public void deletar(Integer id) {
        String sql = "DELETE FROM cliente WHERE id_cliente = ?;";

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Cliente deletado com sucesso.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao deletar cliente. " + e.getMessage());
        }
    }


    public Cliente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM cliente WHERE cpf = ?;";
        Cliente cliente = null;

        try (Connection conn = ConexaoBancoDeDados.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, cpf);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(resultSet.getInt("id_cliente"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setEndereco(resultSet.getString("endereco"));
                cliente.setTelefone(resultSet.getString("telefone"));
                System.out.println("Cliente encontrado com sucesso.");
            } else {
                System.out.println("Cliente não encontrado para o CPF fornecido.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar cliente por CPF. " + e.getMessage());
        }

        return cliente;
    }
}
