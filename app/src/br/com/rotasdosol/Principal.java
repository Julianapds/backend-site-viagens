package br.com.rotasdosol;

import br.com.rotasdosol.bancodedados.ConexaoBancoDeDados;
import br.com.rotasdosol.entidades.Cliente;
import br.com.rotasdosol.repositorios.ClienteRepositorio;

import java.util.List;

public class Principal {
    public static void main(String[] args) {
        ClienteRepositorio clienteRepositorio=new ClienteRepositorio();
//        Cliente cliente=new Cliente();
//        cliente.setIdCliente(2);
//        cliente.setEmail("jps@gmail.com");
//        cliente.setCpf("21656597985");
//        cliente.setTelefone("2198645763");
//        cliente.setEndereco("rua01");
//
//        clienteRepositorio.criar(cliente);
        List<Cliente> clientes=clienteRepositorio.listar();
        System.out.println(clientes);


    }
}
